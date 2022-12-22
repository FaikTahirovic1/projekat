package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final TeamDao teamDao =  new TeamDaoSQLImpl();
    private static final TrackDao trackDao = new TrackDaoSQLImpl();
    private static final DriverDao driverDao = new DriverDaoSQLImpl();
    private DaoFactory(){

    }
    public static TeamDao teamDao(){
        return teamDao;
    }
    public static DriverDao driverDao(){
        return driverDao;
    }
    public static TrackDao trackDao(){
        return trackDao;
    }
}
