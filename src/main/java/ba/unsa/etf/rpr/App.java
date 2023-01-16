package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.domain.Driver;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        List<Driver> lista = new ArrayList<Driver>();
        DriverManager a = new DriverManager();
        try {
            lista = a.getAll();
            System.out.println("Velicina je " + lista.size());
        } catch (Exception e) {
            System.out.println("Ne radi");
        }
    }
}
