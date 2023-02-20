package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;

import java.util.List;
/**
 * Business Logic Layer for management of Tracks
 *
 * @author FaikTahirovic1
 */
public class TrackManager {
    public List<Track> getAll() throws F1Exception {
        return DaoFactory.trackDao().getAll();
    }
    /*public List<Team> searchTracks(String text) throws F1Exception {
        return DaoFactory.trackDao().searchByText(text);
    }*/
    public void delete(int id) throws F1Exception{
        DaoFactory.trackDao().delete(id);
    }
    public Track add(Track track) throws F1Exception {
        try{
            return DaoFactory.trackDao().add(track);
        }catch(F1Exception e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new F1Exception("Track with same name exists");
            }
            throw e;
        }
    }
}
