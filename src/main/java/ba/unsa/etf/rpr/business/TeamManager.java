package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;

import java.util.List;

public class TeamManager {
    public List<Team> getAll() throws F1Exception {
        return DaoFactory.teamDao().getAll();
    }
    public List<Team> searchTeams(String text) throws F1Exception {
        return DaoFactory.teamDao().searchByText(text);
    }
    public void delete(int id) throws F1Exception{
        DaoFactory.teamDao().delete(id);
    }
    public Team add(Team team) throws F1Exception {
        try{
            return DaoFactory.teamDao().add(team);
        }catch(F1Exception e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new F1Exception("Team with same name exists");
            }
            throw e;
        }
    }
    public Team update(Team team) throws F1Exception {
        //updates team with same id, parameter is the new version of team
        Team t = new Team();
        //find same id
        for(int i = 0; i < getAll().size(); i++){
            if(getAll().get(i).getId() == team.getId())t = getAll().get(i);
        }
        //t drzi taj tim;
        delete(t.getId());
        add(team);
        return team;
    }
}
