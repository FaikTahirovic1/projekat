package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Track;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class TrackFansController {
    private final DriverManager driver= new DriverManager();
    private final TrackManager track = new TrackManager();
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    public void initialize() throws F1Exception {
        ArrayList<Track> staze = (ArrayList<Track>) track.getAll();
        String stazeString[] = new String[staze.size()];
        for(int i = 0; i<staze.size(); i++){
            stazeString[i] = staze.get(i).toString();
        }
        choiceBox.getItems().addAll(stazeString);
    }
    public void searchDrivers(ActionEvent actionEvent) throws F1Exception {
        String ime = choiceBox.getValue();
        Integer id = 0;
        ArrayList<Track> staze = (ArrayList<Track>) track.getAll();
        for(int i = 0; i<staze.size(); i++){
            if(staze.get(i).getName().equals(ime))id = staze.get(i).getId();
        }
        ArrayList<Driver> vozaci = (ArrayList<Driver>) driver.getAll();
        ArrayList<String>imena;
        for(int i = 0; i < vozaci.size(); i++){
            if(vozaci.get(i).getFavouriteTrack().getId() == id)System.out.println(vozaci.get(i));
        }
    }
}
