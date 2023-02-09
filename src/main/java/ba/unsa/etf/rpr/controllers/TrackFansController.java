package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Track;
import javafx.collections.FXCollections;
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
            //System.out.println(stazeString[i]);
        }
        //choiceBox.setItems(FXCollections.observableArrayList(stazeString));
        choiceBox.getItems().addAll(stazeString);
        //System.out.println(choiceBox.getItems().get(0));


    }
}
