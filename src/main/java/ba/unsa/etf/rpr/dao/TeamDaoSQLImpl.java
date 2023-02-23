package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * MySQL implementation of the DAO
 * @author Faik Tahirovic
 */
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
    public Team add(Team item) throws F1Exception{
        Map<String, Object> row = object2row(item);
        //System.out.println("proslo");
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append("Team");
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
    @Override
    public Team update(Team item) throws RuntimeException {

        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append("Team")
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE idTeam = ?");

        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                //if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            //System.out.println();
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
