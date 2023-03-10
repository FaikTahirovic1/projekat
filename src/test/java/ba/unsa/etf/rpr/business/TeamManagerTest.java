package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.TeamDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TeamManagerTest {
    private TeamManager teamManager;
    private Team team;
    private TeamDaoSQLImpl teamDaoSQLMock;
    private List<Team> teamList;

    @BeforeEach
    public void initializeObjects(){
        teamManager = Mockito.mock(TeamManager.class);
        teamDaoSQLMock = Mockito.mock(TeamDaoSQLImpl.class);

        team = new Team(10,"Fake team","Fake country");

        teamList = new ArrayList<>();
        teamList.addAll(Arrays.asList(new Team(11,"Faik's team", "Bosnia"), new Team(12, "Lotus","UK")
                , new Team(13, "Benetton","Tungzija")));

    }
    public TeamManager getTeamManager() {
        return teamManager;
    }
    public void setTeamManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TeamDaoSQLImpl getTeamDaoSQLMock() {
        return teamDaoSQLMock;
    }

    public void setTeamDaoSQLMock(TeamDaoSQLImpl teamDaoSQLMock) {
        this.teamDaoSQLMock = teamDaoSQLMock;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    /**
     * this tests adding a team
     * @throws F1Exception
     */
    @Test
    public void addTeam() throws F1Exception {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::teamDao).thenReturn(teamDaoSQLMock);
        when(DaoFactory.teamDao().getAll()).thenReturn(teamList);
        when(teamManager.getAll()).thenReturn(teamList);


        team = new Team(50, "Some team","Some country");
        Mockito.doCallRealMethod().when(teamManager).add(team);
        teamManager.add(team);

        Assertions.assertTrue(true);
        Mockito.verify(teamManager).add(team);
        daoFactoryMockedStatic.close();

    }

    @Test
    void addNewTeam() throws F1Exception {
        Team newTeam = new Team(22,"Pofalicki team F1","Jugoslavija");
        teamManager.add(newTeam);

        Assertions.assertTrue(true);
        Mockito.verify(teamManager).add(newTeam);
    }


    /**
     * Tests adding a team that already exists
     * @throws F1Exception
     */
    @Test
    void addAlreadyExisting() throws F1Exception {
        when(teamManager.getAll()).thenReturn(teamList);

        team = new Team(11,"Faik's team", "Bosnia");
        Mockito.doCallRealMethod().when(teamManager).add(team);

        F1Exception exception = Assertions.assertThrows(F1Exception.class, () -> {teamManager.add(team);});

        Assertions.assertEquals( "vec postoji", exception.getMessage());

        Mockito.verify(teamManager).add(team);
    }

    /**
     * This test searchTeams method
     * @throws F1Exception
     */
    @Test
    void searchTeamsTest() throws F1Exception {
        when(teamManager.getAll()).thenReturn(teamList);
        String string = "Faik's team";
        Mockito.doCallRealMethod().when(teamManager).searchTeams(string);
        List<Team>someTeams = teamManager.searchTeams(string);
        assertAll(
                "Searching team",
                () -> assertEquals(1 , someTeams.size()),
                () -> assertEquals("Bosnia" , someTeams.get(0).getCountry()),
                () -> assertEquals("Faik's team",someTeams.get(0).getName()),
                () -> assertEquals(11,someTeams.get(0).getId())
        );

    }
    /**
     * this tests deleting a team
     * @throws F1Exception
     * @autor FaikTahirovic1
     */
    @Test
    public void deleteTeam() throws F1Exception {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::teamDao).thenReturn(teamDaoSQLMock);
        when(DaoFactory.teamDao().getAll()).thenReturn(teamList);
        when(teamManager.getAll()).thenReturn(teamList);
        int i = 0;
        System.out.println(teamManager.getAll().size());
        i = teamManager.getAll().get(0).getId();
        Mockito.doCallRealMethod().when(teamManager).delete(i);
        teamManager.delete(i);
        Assertions.assertTrue(true);
        Mockito.verify(teamManager).delete(i);
        daoFactoryMockedStatic.close();


    }

    /**
     * Tests updating a team
     * @throws F1Exception
     * @author FaikTahirovic1
     */
    @Test
    public  void updateTeam() throws F1Exception {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::teamDao).thenReturn(teamDaoSQLMock);
        when(DaoFactory.teamDao().getAll()).thenReturn(teamList);
        when(teamManager.getAll()).thenReturn(teamList);
        Team tim = new Team();
        tim.setId(teamManager.getAll().get(0).getId());
        tim.setCountry(teamManager.getAll().get(0).getCountry());
        tim.setName("Changed name");
        Mockito.doCallRealMethod().when(teamManager).update(tim);
        teamManager.update(tim);
        Assertions.assertTrue(true);
        Mockito.verify(teamManager).update(tim);
        daoFactoryMockedStatic.close();

    }


}
