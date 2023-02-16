package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TeamDaoSQLImpl extends AbstractDao<Team> implements  TeamDao{
    private static  TeamDaoSQLImpl instance = null;
    public static TeamDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new TeamDaoSQLImpl();
        return instance;
    }
    public TeamDaoSQLImpl(){
        super("Team");
    }
    @Override
    public Team row2object(ResultSet rs) throws F1Exception {
        try{


            Team team = new Team();
            team.setId(rs.getInt("idTeam"));
            //System.out.println("vrti li vrti");
            team.setName(rs.getString("Name"));
            //System.out.println("Ime je " + team.getName());
            team.setCountry(rs.getString("Country"));
            return team;

        }catch(Exception e){
            throw new F1Exception("izuzetak");
        }
    }

    @Override
    public Map<String,Object> object2row(Team team){
        Map<String,Object> row = new TreeMap<String, Object>();
        row.put("idTeam", team.getId());
        row.put("Country",team.getCountry());
        row.put("Name",team.getName());
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
    public Team getById(int id) throws F1Exception {
        //System.out.println("ovo");
        return executeQueryUnique("SELECT * FROM "+"Team"+" WHERE idTeam = ?", new Object[]{id});

    }
    @Override
    public List<Team> getAll() throws F1Exception {

        return executeQuery("SELECT * FROM "+ "Team", null);
    }
    @Override
    public void delete(int id) throws F1Exception {
        String sql = "DELETE FROM "+"Team"+" WHERE idTeam = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new F1Exception(e.getMessage(), e);
        }
    }


}
