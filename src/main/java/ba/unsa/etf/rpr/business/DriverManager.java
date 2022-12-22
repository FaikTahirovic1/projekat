package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.dao.DaoFactory;

import java.util.List;

public class DriverManager {
    public List<Driver> getAll() throws F1Exception {
        return DaoFactory.driverDao().getAll();
    }

    public List<Driver> searchDrivers(String text) throws F1Exception {
        return DaoFactory.driverDao().searchByText(text);
    }

    public void delete(int id) throws F1Exception{
        DaoFactory.driverDao().delete(id);
    }
}
