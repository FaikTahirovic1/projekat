package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Idable;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao <T extends Idable> implements Dao<T>{
private static Connection connection=null;
private String tableName;

public AbstractDao(String tableName) {
    this.tableName = tableName;
    createConnection();
}
    private static void createConnection(){
        if(AbstractDao.connection==null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = p.getProperty("db.connection_string");

                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");

                AbstractDao.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_rprbaza123", "freedb_ftahirovic123", "&WyMMXeq$w7aTwD");

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try{
                            connection.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public static Connection getConnection(){
        return AbstractDao.connection;
    }
    public static void closeConnection() {
        System.out.println("pozvana metoda za zatvaranje konekcije");
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close connection on database");
            }
        }
    }

    public abstract  T row2object (ResultSet rs) throws F1Exception;

    public abstract Map<String,Object> object2row(T object);
    public T getById(int id) throws F1Exception {
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE idTeam = ?", new Object[]{id});
    }
    public T getByDriver(ba.unsa.etf.rpr.domain.Driver d)throws F1Exception{
        return getById(d.getId());

    }

    public List<T> getAll() throws F1Exception {

        return executeQuery("SELECT * FROM "+ tableName, null);
    }

    public List<T> executeQuery(String query, Object[] params) throws F1Exception{
        try {
            //System.out.println("Izvrsi");
            PreparedStatement stmt = getConnection().prepareStatement(query);

            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }

            ResultSet rs = stmt.executeQuery();
            //System.out.println(rs);
            ArrayList<T> resultList = new ArrayList<>();


            while (rs.next()) {

                resultList.add(row2object(rs));


            }

            return resultList;
        } catch (SQLException e) {

            throw new F1Exception(e.getMessage(), e);
        }
    }

    public void delete(int id) throws F1Exception {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new F1Exception(e.getMessage(), e);
        }
    }
    public T executeQueryUnique(String query, Object[] params) throws F1Exception{

        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new F1Exception("Object not found");
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
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }
    public T add(T item) throws F1Exception{
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
            //throw new F1Exception(e.getMessage(), e);
            System.out.println("nes");
            return null;
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
