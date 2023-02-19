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
    private static final Option addDriver = new Option("d","add-driver",false, "Adding new driver to f1 database");
    private static final Option addTeam = new Option("t","add-team",false, "Adding new team to f1 database");
    private static final Option addTrack = new Option("s","add-track",false, "Adding new track to f1 database");
    private static final Option getDrivers = new Option("getD", "get-drivers",false, "Printing all drivers from f1 database");
    private static final Option getTeams = new Option("getT", "get-teams",false, "Printing all teams from f1 database");
    private static final Option getTracks = new Option("getS", "get-tracks",false, "Printing all tracks from f1 database");
    //private static final Option categoryDefinition = new Option(null, "category",false, "Defining category for next added quote");

    public static void main(String[] args) throws F1Exception {

    }
}
