package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;

import java.util.List;
/**
 * Dao interface for Team domain bean
 *
 * @author Faik Tahirovic
 */
public interface TeamDao extends Dao<Team>{
    List<Team> searchByText(String text) throws F1Exception;
}
