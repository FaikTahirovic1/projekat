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
               throw new RuntimeException("Onbjekat nije nadjen")
           }

       }catch (SQLException e){
           throw new RuntimeException(e.getMessage(),e);
       }
    }

}
