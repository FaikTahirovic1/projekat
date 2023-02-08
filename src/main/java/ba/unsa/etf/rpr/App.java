package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TeamManager;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;

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
        System.out.println(trm.getAll().size());
        ArrayList<Track> staze = (ArrayList<Track>) trm.getAll();




    }
}
