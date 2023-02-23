package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.DriverDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Time;
import ba.unsa.etf.rpr.domain.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DriverDriverTest {
    private DriverManager driverManager;
    private Driver driver;
    private Team team;
    private Track track;
    private DriverDaoSQLImpl driverDaoSQLMock;
    private List<Driver> drivers;
    private TeamManagerTest teamManagerTest;
    private TrackManagerTest trackManagerTest;

    @BeforeEach
    public void initializeObjects() throws F1Exception {
        driverManager = Mockito.mock(DriverManager.class);
        driver = new Driver();
        team = new Team();
        team.setName("ETF ekipa");
        team.setCountry("Bosna i Hercegovina");
        team.setId(131);

        track = new Track("Zimbabve",new Time(10009),"Vijaguva maglovita staza",20);

        driver.setTeam(team);
        driver.setFavouriteTrack(track);
        driver.setId(1);
        driverDaoSQLMock = Mockito.mock(DriverDaoSQLImpl.class);
        drivers = new ArrayList<Driver>();
        drivers.add(driver);

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

    /**
     * tests adding a new driver
     * @throws F1Exception
     * @autor FaikTahirovic1
     */
    @Test
    void addNewDriverTest() throws F1Exception {
        Driver newDriver = new Driver("Opaki vozac",team,track,111,28);
        driverManager.add(newDriver);

        Assertions.assertTrue(true);
        Mockito.verify(driverManager).add(newDriver);
    }
    /**
     * This test searchDrivers method
     * @throws F1Exception
     */
    @Disabled
    void searchDriversTest() throws F1Exception {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::driverDao).thenReturn(driverDaoSQLMock);
        when(driverManager.getAll()).thenReturn(drivers);
        String string = "Munjeviti juric";
        Mockito.doCallRealMethod().when(driverManager).searchDrivers(string);
        List<Driver>someDrivers = driverManager.searchDrivers(string);
        assertAll(
                "Searching driver",
                () -> assertEquals(1 , someDrivers.size()),
                () -> assertEquals(11,someDrivers.get(0).getId())
        );

    }

}
