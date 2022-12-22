package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Team;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class TeamDaoSQLImpl extends AbstractDao<Team> implements  TeamDao{
    public TeamDaoSQLImpl(){
        super("Team");
    }
    @Override
    public Team row2object(ResultSet rs)throws RuntimeException{
        try{
            Team team = new Team();
            team.setId(rs.getInt("idTeam"));
            team.setCountry(rs.getString("Country"));
            team.setName(rs.getString("Name"));
            team.setDriver1(DaoFactory.driverDao().getByID(rs.getInt("id_driver1")));
            team.setDriver2(DaoFactory.driverDao().getByID(rs.getInt("id_driver2")));
            return team;

        }catch(Exception e){
            throw new RuntimeException("Ne postoji kolona idTeam");
        }
    }
    @Override
    public Map<String,Object> object2row(Team team){
        Map<String,Object> row = new TreeMap<String, Object>();
        row.put("idTeam", team.getId());
        row.put("Country",team.getCountry());
        row.put("Name",team.getName());
        row.put("id_driver1",team.getDriver1().getId());
        row.put("id_driver2",team.getDriver2().getId());
        return row;
    }

}
