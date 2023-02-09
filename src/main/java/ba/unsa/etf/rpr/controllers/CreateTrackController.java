package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Time;
import ba.unsa.etf.rpr.domain.Track;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import static java.lang.Integer.parseInt;

public class CreateTrackController {
    private final TrackManager manager = new TrackManager();
    public TextField trackName;
    public TextField trackCountry;
    public TextField trackTime;
    public TextField trackId;

    public void createTrack(ActionEvent event) throws F1Exception {
        Track staza = new Track();
        Time vr = new Time(parseInt(trackTime.getText()));
        staza.setBestTime(vr);
        staza.setId(parseInt(trackId.getText()));
        staza.setCountry(trackCountry.getText());
        staza.setName(trackName.getText());
        manager.add(staza);
    }
}
