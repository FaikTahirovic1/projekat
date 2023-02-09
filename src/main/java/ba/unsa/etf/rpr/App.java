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
        TeamManager Team = new TeamManager();
        System.out.println(Team.getAll().size());
        ArrayList<Team> timovi = (ArrayList<ba.unsa.etf.rpr.domain.Team>) Team.getAll();
        System.out.println(timovi.get(1).getName());

        TrackManager trm = new TrackManager();
        ArrayList<Track>staze = (ArrayList<Track>) trm.getAll();
        System.out.println(trm.getAll().size());
        DriverManager driver = new DriverManager();
        ArrayList<Driver> vozaci = (ArrayList<Driver>) driver.getAll();
        Driver vozac = new Driver("Lance Stroll", timovi.get(timovi.size()-1), staze.get(staze.size()-1),9,22);
        driver.add(vozac);
        System.out.println(vozaci.get(4));

        Team Tim = new Team(4, "Red Bull","Austria",vozaci.get(4),vozaci.get(vozaci.size()-1));
        //Team.add(Tim);
        System.out.println(vozaci.get(vozaci.size()-1).getId());
        //System.out.println(timovi.get(timovi.size()-2));

    }
}
