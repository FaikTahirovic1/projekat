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

}
