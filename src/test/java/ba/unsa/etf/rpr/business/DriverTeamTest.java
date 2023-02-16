package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DriverDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverDriverTest {
    private DriverManager driverManager;
    private Driver driver;
    private DriverDaoSQLImpl driverDaoSQLMock;
    private List<Driver> drivers;
    private TeamManagerTest teamManagerTest;

    @BeforeEach
    public void initializeObjects() throws F1Exception {
        teamManagerTest = new TeamManagerTest();
        teamManagerTest.initializeObjects();
        driverManager = Mockito.mock(DriverManager.class);
        driverDaoSQLMock = Mockito.mock(DriverDaoSQLImpl.class);

        Driver firstDriver = new Driver();
        Driver secondDriver = new Driver();

        drivers = new ArrayList<>();
        drivers.addAll(Arrays.asList(firstDriver, secondDriver));

    }
    public DriverManager getDriverManager() {
        return driverManager;
    }
    public void setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverDaoSQLImpl getDriverDaoSQLMock() {
        return driverDaoSQLMock;
    }

    public void setDriverDaoSQLMock(DriverDaoSQLImpl driverDaoSQLMock) {
        this.driverDaoSQLMock = driverDaoSQLMock;
    }

    public List<Driver> getDriverList() {
        return drivers;
    }

    public void setDriverList(List<Driver> driverList) {
        this.drivers = driverList;
    }
}
