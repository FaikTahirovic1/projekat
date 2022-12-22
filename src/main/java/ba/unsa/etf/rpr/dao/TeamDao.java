package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Team;

import java.util.List;

public interface TeamDao extends Dao<Team>{
    List<Team> searchByText(String text) throws F1Exception;
}
