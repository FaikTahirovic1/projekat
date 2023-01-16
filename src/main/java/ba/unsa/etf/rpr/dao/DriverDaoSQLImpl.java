package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DriverDaoSQLImpl extends AbstractDao<Driver> implements DriverDao {
    private static  DriverDaoSQLImpl instance = null;
    public static DriverDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new DriverDaoSQLImpl();
        return instance;
    }
    public DriverDaoSQLImpl() {
        super("Drivers");

    }

    @Override
    public Driver row2object(ResultSet rs) throws F1Exception {
        try {
            Driver d = new Driver();
            d.setId(rs.getInt("idDrivers"));
            d.setAge(rs.getInt("Age"));
            d.setName(rs.getString("Name"));
            d.setFavouriteTrack(DaoFactory.trackDao().getById(rs.getInt("favouritetrackid")));
            d.setTeam(DaoFactory.teamDao().getById(rs.getInt("teamid")));
            return d;
        } catch (Exception e) {
            throw new F1Exception(e.getMessage(), e);
        }
    }

    public Map<String, Object> object2row(Driver d) {
        Map<String, Object> objekat = new TreeMap<String, Object>();
        objekat.put("idDrivers", d.getId());
        objekat.put("Age", d.getAge());
        objekat.put("Name", d.getName());
        objekat.put("favouritetrackid", d.getFavouriteTrack().getId());
        objekat.put("teamid", d.getTeam().getId());
        return objekat;

    }
    @Override
    public List<Driver> searchByText(String text) throws F1Exception {
        return executeQuery("SELECT * FROM Drivers WHERE Name LIKE concat('%', ?, '%')", new Object[]{text});
    }
}
