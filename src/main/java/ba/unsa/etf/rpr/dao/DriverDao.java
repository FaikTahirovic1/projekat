package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;

import java.util.List;
/**
 * Dao interface for Driver domain bean
 *
 * @author Faik Tahirovic
 */

public interface DriverDao extends  Dao<Driver> {
    List<Driver> searchByText(String text) throws F1Exception;
}
