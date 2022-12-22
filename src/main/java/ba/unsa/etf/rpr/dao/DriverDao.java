package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;

import java.util.List;

public interface DriverDao extends  Dao<Driver> {
    List<Driver> searchByText(String text) throws F1Exception;
}
