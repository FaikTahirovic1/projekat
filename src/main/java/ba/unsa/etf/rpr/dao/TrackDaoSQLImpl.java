package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Time;
import ba.unsa.etf.rpr.domain.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrackDaoSQLImpl extends AbstractDao<Track> implements TrackDao{
    private static  TrackDaoSQLImpl instance = null;
    public static TrackDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new TrackDaoSQLImpl();
        return instance;
    }
    public void removeInstance(){
        if(instance != null)instance = null;
    }
    public TrackDaoSQLImpl(){
        super("Track");
    }
    @Override
    public Track row2object(ResultSet rs) throws RuntimeException{
        try{
            Track t = new Track();
            t.setId(rs.getInt("idTrack"));
            t.setCountry(rs.getString("Country"));
            t.setName(rs.getString("Name"));
            int a = rs.getInt("Time");
            Time time = new Time(a);
            t.setBestTime(time);
            return t;

        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public Map<String,Object> object2row(Track t){
        Map<String,Object> objekat = new TreeMap<String,Object>();
        objekat.put("idTrack",t.getId());
        objekat.put("Country",t.getCountry());
        objekat.put("Name",t.getName());
        objekat.put("Time",t.getBestTime().timeToInt());
        return objekat;
    }
    public Track getById(int id) throws F1Exception {
        return executeQueryUnique("SELECT * FROM "+"Track"+" WHERE idTrack = ?", new Object[]{id});
    }
    @Override
    public void delete(int id) throws F1Exception {
        String sql = "DELETE FROM "+"Track"+" WHERE idTrack = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new F1Exception(e.getMessage(), e);
        }
    }
    @Override
    public Track add(Track item) throws F1Exception{
        Map<String, Object> row = object2row(item);
        //System.out.println("proslo");
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append("Track");
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
            throw new F1Exception("vec postoji");
        }
    }
    @Override
    public List<Track> getAll() throws F1Exception {

        return executeQuery("SELECT * FROM "+ "Track", null);
    }

}
