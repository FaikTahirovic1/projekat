package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Driver;

import java.sql.ResultSet;

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

}
