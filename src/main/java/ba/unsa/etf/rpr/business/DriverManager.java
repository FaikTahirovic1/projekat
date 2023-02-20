package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Driver;


import java.util.List;
/**
 * Business Logic Layer for management of Drivers
 *
 * @author FaikTahirovic1
 */
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
    public void update(Driver d) throws F1Exception{
        DaoFactory.driverDao().update(d);
    }

}
