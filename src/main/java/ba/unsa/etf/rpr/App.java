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
import net.bytebuddy.asm.Advice;
import org.apache.commons.cli.*;
import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Faik
 * CLI (Command Line Interface) implementation in following class
 * Even though this type of presentation layer (called CLI) is becoming past tense for GUI apps
 * it's good to see how you can manipulate data through command line and database also
 *
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final DriverManager driverManager = new DriverManager();
    private static final TeamManager teamManager = new TeamManager();
    private static final TrackManager trackManager = new TrackManager();
    private static final Option addDriver = new Option("d","add-driver",false, "Adding new driver to f1 database");
    private static final Option addTeam = new Option("t","add-team",false, "Adding new team to f1 database");
    private static final Option addTrack = new Option("s","add-track",false, "Adding new track to f1 database");
    private static final Option getDrivers = new Option("getD", "get-drivers",false, "Printing all drivers from f1 database");
    private static final Option getTeams = new Option("getT", "get-teams",false, "Printing all teams from f1 database");
    private static final Option getTracks = new Option("getS", "get-tracks",false, "Printing all tracks from f1 database");

    private static final Option deleteDriver = new Option("deleteD","delete-driver",false,"Deletes a driver from f1 database");
    private static final Option deleteTeam = new Option("deleteT","delete-team",false,"Deletes a team from f1 database");
    private static final Option deleteTrack = new Option("deleteS","delete-track",false,"Deletes a track from f1 database");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addDriver);
        options.addOption(addTeam);
        options.addOption(addTrack);
        options.addOption(getDrivers);
        options.addOption(getTeams);
        options.addOption(getTracks);
        options.addOption(deleteDriver);
        options.addOption(deleteTeam);
        options.addOption(deleteTrack);
        return options;
    }
    public static Team searchThroughTeams(List<Team> listOfTeams, String teamName) {

        Team team = null;
        team = listOfTeams.stream().filter(tim -> tim.getName().toLowerCase().equals(teamName.toLowerCase())).findAny().get();
        return team;

    }
    public static Track searchThroughTracks(List<Track> listOfTracks, String trackName) {

        Track track = null;
        track = listOfTracks.stream().filter(tr -> tr.getName().toLowerCase().equals(trackName.toLowerCase())).findAny().get();
        return track;

    }


    public static void main(String[] args) throws F1Exception, ParseException {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);
        if((cl.hasOption(addDriver.getOpt()) || cl.hasOption(addDriver.getLongOpt()))){
            Team team = null;
            Track track = null;
            try {
                team = searchThroughTeams(teamManager.getAll(), cl.getArgList().get(1));
                track = searchThroughTracks(trackManager.getAll(),cl.getArgList().get(2));
            }catch(Exception e) {
                System.out.println("There is no team/track in the list! Try again.");
                System.exit(1);
            }

            Driver driver = new Driver();
            driver.setTeam(team);
            driver.setFavouriteTrack(track);
            driver.setName(cl.getArgList().get(0));
            driver.setAge(Integer.valueOf(cl.getArgList().get(3)));
            driverManager.add(driver);
            System.out.println("You successfully added driver to database!");
//                break;
        } else if(cl.hasOption(getDrivers.getOpt()) || cl.hasOption(getDrivers.getLongOpt())){
            DriverManager driverManager = new DriverManager();
            driverManager.getAll().forEach(d -> System.out.println(d.getName()));
//                break;
        } else if(cl.hasOption(addTeam.getOpt()) || cl.hasOption(addTeam.getLongOpt())){
            try {
                TeamManager teamManager = new TeamManager();
                Team tim = new Team();
                tim.setName(cl.getArgList().get(1));
                tim.setId(Integer.valueOf(cl.getArgList().get(0)));
                tim.setCountry(cl.getArgList().get(2));
                teamManager.add(tim);
                System.out.println("Team has been added successfully");
            }catch(Exception e) {
                System.out.println("There is already team with same name in database! Try again");
                System.exit(1);
            }

        } else if(cl.hasOption(getTeams.getOpt()) || cl.hasOption(getTeams.getLongOpt())){
            TeamManager teamManager = new TeamManager();
            teamManager.getAll().forEach(t -> System.out.println(t.getName()));
        } else if(cl.hasOption(addTrack.getOpt()) || cl.hasOption(addTrack.getLongOpt())){
            try {
                TrackManager trackManager = new TrackManager();
                Track track = new Track();
                track.setId(Integer.valueOf(cl.getArgList().get(0)));
                track.setName(cl.getArgList().get(1));
                Integer timeInMilis = Integer.valueOf(cl.getArgList().get(2));
                Time bestTime = new Time(timeInMilis);
                track.setBestTime(bestTime);
                track.setCountry(cl.getArgList().get(3));
            }catch (Exception e){
                System.out.println("Unable to add this track");
                System.exit(1);
            }

        }else {
            printFormattedOptions(options);
            System.exit(-1);
//                break;
        }
//        }

    }
}
