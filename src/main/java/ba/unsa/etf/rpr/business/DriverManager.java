package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Driver;


import java.util.List;

public class DriverManager {
    public static List<Driver> getAll() throws F1Exception {
        return DaoFactory.driverDao().getAll();
    }
    public static List<Driver> searchDrivers(String text) throws F1Exception {
        return DaoFactory.driverDao().searchByText(text);
    }

    public void delete(int id) throws F1Exception{
        try{
            DaoFactory.driverDao().delete(id);
        }catch (F1Exception e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new F1Exception("Cannot delete category which is related to quotes. First delete related quotes before deleting category.");
            }
            throw e;
        }
    }
    public Driver add(Driver driver) throws F1Exception {
        try{
            return DaoFactory.driverDao().add(driver);
        }catch(F1Exception e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new F1Exception("Driver with same name exists");
            }
            throw e;
        }
    }
    public Driver update(Driver driver) throws F1Exception {
        Driver d = new Driver(driver.getName(),driver.getTeam(),driver.getFavouriteTrack(),driver.getId(),driver.getAge());
        delete(driver.getId());
        add(d);
        return d;
    }
}
