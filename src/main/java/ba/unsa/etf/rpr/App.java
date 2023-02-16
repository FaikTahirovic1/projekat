package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TeamManager;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Time;
import ba.unsa.etf.rpr.domain.Track;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws F1Exception {
        DriverManager driverManager = new DriverManager();
        driverManager.delete(5);
        for(int i = 0; i < driverManager.getAll().size(); i++)System.out.println(driverManager.getAll().get(i));
        TeamManager teamManager = new TeamManager();
        TrackManager trackManager = new TrackManager();
        Driver driver = new Driver("Yuki Tsunoda",teamManager.getAll().get(3), trackManager.getAll().get(2),8,19);
        driverManager.add(driver);
        for(int i = 0; i < driverManager.getAll().size(); i++)System.out.println(driverManager.getAll().get(i));

    }
}
