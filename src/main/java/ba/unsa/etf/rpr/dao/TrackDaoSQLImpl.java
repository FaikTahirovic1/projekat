package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Time;
import ba.unsa.etf.rpr.domain.Track;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class TrackDaoSQLImpl extends AbstractDao<Track> implements TrackDao{
    private static  TrackDaoSQLImpl instance = null;
    public static TrackDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new TrackDaoSQLImpl();
        return instance;
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

}
