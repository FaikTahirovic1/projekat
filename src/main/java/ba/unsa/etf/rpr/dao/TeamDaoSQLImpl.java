package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            team.setDriver1(DaoFactory.driverDao().getById(rs.getInt("id_driver1")));
            team.setDriver2(DaoFactory.driverDao().getById(rs.getInt("id_driver2")));
            return team;

        }catch(Exception e){
            throw new RuntimeException("Ne postoji kolona");
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
    @Override
    public List<Team> searchByText(String text) throws F1Exception {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM Team WHERE Name LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Team> teamLista = new ArrayList<>();
            while (rs.next()) {
                teamLista.add(row2object(rs));
            }
            return teamLista;
        } catch (SQLException e) {
            throw new F1Exception(e.getMessage(), e);
        }
    }
    @Override
    public Team searchByDriver(ba.unsa.etf.rpr.domain.Driver d) throws F1Exception{
         String query = "Select * FROM Team WHERE id_driver1 = ?";
         try{
             PreparedStatement stmt = getConnection().prepareStatement(query);
             stmt.setInt(1,d.getId());
             ResultSet rs = stmt.executeQuery();
             return row2object(rs);
         }catch (SQLException e){
             throw new F1Exception(e.getMessage(),e);
         }
    }

}
