package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Idable;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao <T extends Idable> implements Dao<T>{
private Connection connection;
private String tableName;

public AbstractDao(String tableName) {
    try{
        this.tableName = tableName;
        Properties p = new Properties();
        p.load(ClassLoader.getSystemResource("application.properties").openStream());
        String url = p.getProperty("db.connection_string");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");
        this.connection = DriverManager.getConnection(url,username,password);
    }catch(Exception e){
        System.out.println("Greska pri stupanju konekcije na bazu");
        e.printStackTrace();
    }
}

    public Connection getConnection() {
        return connection;
    }
    public abstract  T row2object (ResultSet rs) throws RuntimeException;
    public abstract Map<String,Object> object2row(T object);
    public T getById(int id)throws RuntimeException{
       String query = "SELECT * FROM " + this.tableName+" WHERE id = ?";
       try{
           PreparedStatement stmt = this.connection.prepareStatement(query);
           stmt.setInt(1,id);
           ResultSet rs = stmt.executeQuery();
           if(rs.next()){
               T result = row2object(rs);
               rs.close();
               return result;

           } else{
               throw new RuntimeException("Onbjekat nije nadjen");
           }

       }catch (SQLException e){
           throw new RuntimeException(e.getMessage(),e);
       }

    }
    public List<T> getAll() throws RuntimeException {
        String query = "SELECT * FROM "+ tableName;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                T object = row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    public void delete(int id) throws RuntimeException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }
    public T add(T item) throws RuntimeException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    public T update(T item) throws RuntimeException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }





}
