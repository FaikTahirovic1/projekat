package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.TeamDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
