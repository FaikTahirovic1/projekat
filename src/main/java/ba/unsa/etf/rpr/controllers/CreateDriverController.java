package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TeamManager;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Team;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;

import java.awt.*;
import java.util.ArrayList;

public class CreateDriverController {
    private DriverManager drivermanager = new DriverManager();
    private TrackManager trackmanager = new TrackManager();
    private TeamManager  teammanager = new TeamManager();
    @FXML
    private TextField drivernamefield;
    @FXML
    private TextField driveridfield;
    @FXML
    private ChoiceBox<String> teamcb;
    @FXML
    private ChoiceBox<String> trackcb;

    /*@FXML
    public void initialize() throws F1Exception {
        ArrayList<Team> timovi = (ArrayList<Team>) teammanager.getAll();
        String teamString[] = new String[timovi.size()];
        for(int i = 0; i<timovi.size(); i++){
            teamString[i] = timovi.get(i).toString();
        }
        teamcb.getItems().addAll(teamString);
        refreshTeams();
    }*/
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
    public void create(ActionEvent actionEvent){

    }


}
