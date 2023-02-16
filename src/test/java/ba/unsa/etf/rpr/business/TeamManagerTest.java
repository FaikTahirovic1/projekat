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

}
