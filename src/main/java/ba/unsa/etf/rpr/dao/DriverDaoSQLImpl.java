package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * MySQL Implementation of DAO
 * @author Faik Tahirovic
 */
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
            d.setId(rs.getInt("id"));
            d.setAge(rs.getInt("Age"));
            d.setName(rs.getString("Name"));
            d.setFavouriteTrack(DaoFactory.trackDao().getById(rs.getInt("favouritetrackid")));
            d.setTeam(DaoFactory.teamDao().getById(rs.getInt("teamid")));
            return d;
        } catch (Exception e) {
            throw new F1Exception(e.getMessage(), e);
        }
    }

    @Override
    public Driver getById(int id) throws F1Exception {

        return executeQueryUnique("SELECT * FROM "+"Drivers"+" WHERE id = ?", new Object[]{id});

    }
    /**
     * @param d - object to be mapped
     * @return map representation of object
     */

    public Map<String, Object> object2row(Driver d) {
        Map<String, Object> objekat = new TreeMap<String, Object>();
        objekat.put("id", d.getId());
        objekat.put("Age", d.getAge());
        objekat.put("Name", d.getName());
        objekat.put("favouritetrackid", d.getFavouriteTrack().getId());
        objekat.put("teamid", d.getTeam().getId());
        return objekat;

    }
    /**
     * @param text search string for Driver
     * @return list of drivers
     * @author Faik Tahirovic
     */
    @Override
    public List<Driver> searchByText(String text) throws F1Exception {
        return executeQuery("SELECT * FROM Drivers WHERE Name LIKE concat('%', ?, '%')", new Object[]{text});
    }
    @Override
    public Driver add(Driver item) throws F1Exception{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append("Drivers");
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{

            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key

            return item;
        }catch (SQLException e){
           // throw new F1Exception("vec postoji");
            return null;
        }
    }
}
