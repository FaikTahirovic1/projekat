package ba.unsa.etf.rpr.dao;
/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Faik Tahirovic
 */
public class DaoFactory {
    private static final TeamDao teamDao =  TeamDaoSQLImpl.getInstance();
    private static final TrackDao trackDao = TrackDaoSQLImpl.getInstance();
    private static final DriverDao driverDao = DriverDaoSQLImpl.getInstance();
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
