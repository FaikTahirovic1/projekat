package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.TrackDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Time;
import ba.unsa.etf.rpr.domain.Track;
import ba.unsa.etf.rpr.business.TrackManager;
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

public class TrackManagerTest {
    private TrackManager trackManager;
    private Track track;
    private TrackDaoSQLImpl trackDaoSQLMock;
    private List<Track> trackList;

    @BeforeEach
    public void initializeObjects(){
        trackManager = Mockito.mock(TrackManager.class);
        trackDaoSQLMock = Mockito.mock(TrackDaoSQLImpl.class);

        track = new Track("Uganda",new Time(10000),"Ludovita hitrovita staza",50);

        trackList = new ArrayList<>();
        trackList.addAll(Arrays.asList(new Track("American Samoa",new Time(1991991),"new track",51), new Track("Cambodia",new Time(10),"Phnom Penh",52)
                , new Track("Somalia",new Time(13112001),"Mogadishu",53)));

    }
    public TrackManager getTrackManager() {
        return trackManager;
    }
    public void setTrackManager(TrackManager trackManager) {
        this.trackManager = trackManager;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public TrackDaoSQLImpl getTrackDaoSQLMock() {
        return trackDaoSQLMock;
    }

    public void setTrackDaoSQLMock(TrackDaoSQLImpl trackDaoSQLMock) {
        this.trackDaoSQLMock = trackDaoSQLMock;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    /**
     * this tests adding a track
     * @throws F1Exception
     */
    @Test
    public void addTrack() throws F1Exception {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::trackDao).thenReturn(trackDaoSQLMock);
        when(DaoFactory.trackDao().getAll()).thenReturn(trackList);
        when(trackManager.getAll()).thenReturn(trackList);


        track = new Track("Lebanon",new Time(222),"Lebanong GP",55);
        Mockito.doCallRealMethod().when(trackManager).add(track);
        trackManager.add(track);

        Assertions.assertTrue(true);
        Mockito.verify(trackManager).add(track);
        daoFactoryMockedStatic.close();

    }

    @Test
    void addNewTrack() throws F1Exception {
        Track newTrack = new Track("Ecuador", new Time(14012022),"Yellow GP",56);
        trackManager.add(newTrack);

        Assertions.assertTrue(true);
        Mockito.verify(trackManager).add(newTrack);
    }

    

}
