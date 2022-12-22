package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Driver;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class DriverDaoSQLImpl extends AbstractDao<Driver> implements DriverDao{
    public DriverDaoSQLImpl(){
        super("Drivers");

    }
    @Override
    public Driver row2object(ResultSet rs)throws RuntimeException{
        try{
            Driver d = new Driver();
            d.setId(rs.getInt("idDrivers"));
            d.setAge(rs.getInt("Age"));
            d.setName(rs.getString("Name"));
            d.setFavouriteTrack(DaoFactory.trackDao().getById(rs.getInt("favouritetrackid")));
            d.setTeam(DaoFactory.teamDao().getById(rs.getInt("teamid")));
            return d;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public Map<String,Object> object2row(Driver d){
        Map<String,Object> objekat= new TreeMap<String,Object>();
        objekat.put("idDrivers",d.getId());
        objekat.put("Age",d.getAge());
        objekat.put("Name",d.getName());
        objekat.put("favouritetrackid",d.getFavouriteTrack().getId());
        objekat.put("teamid",d.getTeam().getId());
        return objekat;

    }
}