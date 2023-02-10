package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TeamManager;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.ArrayList;

public class CreateDriverController {
    private DriverManager drivermanager = new DriverManager();
    private TrackManager trackmanager = new TrackManager();
    private TeamManager  teammanager = new TeamManager();

    @FXML
    public ChoiceBox<String> teamcb;
    @FXML
    public ChoiceBox<String> trackcb;
    @FXML
    public DialogPane dialogp;
    @FXML
    public TextField namefield;
    @FXML
    public TextField idfield;

    @FXML
    public void initialize() throws F1Exception {
        ArrayList<Team> timovi = (ArrayList<Team>) teammanager.getAll();
        String teamString[] = new String[timovi.size()];
        for(int i = 0; i<timovi.size(); i++){
            teamString[i] = timovi.get(i).toString();
        }
        teamcb.getItems().addAll(teamString);
        refreshTeams();

        ArrayList<Track> tracks = (ArrayList<Track>) trackmanager.getAll();
        String trackString[] = new String[tracks.size()];
        for(int i = 0; i<tracks.size(); i++){
            trackString[i] = tracks.get(i).toString();
        }
        trackcb.getItems().addAll(trackString);
    }
    private void refreshTeams() throws F1Exception{
        try {
            ArrayList<String> teamNames = new ArrayList<>();
            ArrayList<Team> timovi = (ArrayList<Team>) teammanager.getAll();
            for(int i = 0; i < timovi.size(); i++) {
                teamNames.add(timovi.get(i).getName());
            }
            //teamcb.setItems(FXCollections.observableArrayList(teamNames));
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void refreshTracks(){
        try{
            ArrayList<String> trackNames = new ArrayList<>();
            ArrayList<Track>tracks = (ArrayList<Track>) trackmanager.getAll();
            for(int i = 0; i < tracks.size(); i++){
                trackNames.add(tracks.get(i).getName());
            }
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void create(ActionEvent actionEvent){

    }


}
