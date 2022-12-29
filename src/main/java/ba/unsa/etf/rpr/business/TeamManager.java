package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
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
}
