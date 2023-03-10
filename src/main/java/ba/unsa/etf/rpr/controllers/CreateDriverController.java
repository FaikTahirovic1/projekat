package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TeamManager;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for creating new driver management
 *
 * @author FaikTahirovic1
 */

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
    public TextField agefield;

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
    public void create(ActionEvent actionEvent) throws F1Exception {
        Driver d = new Driver();
        d.setName(namefield.getText());
        d.setTeam(findTeamByName(teamcb.getValue()));
        d.setFavouriteTrack(findTrackByName(trackcb.getValue()));
        d.setAge(Integer.parseInt(agefield.getText()));
        drivermanager.add(d);

    }
    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene((Parent) loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
public void back(ActionEvent actionEvent){
    openDialog("Drivers","/fxml/DriverScreen.fxml", new DriverScreenController());
}
    private Team findTeamByName(String name) throws F1Exception {
        for(int i = 0; i < teammanager.getAll().size(); i++){
            if(teammanager.getAll().get(i).getName().equals(name))return teammanager.getAll().get(i);
        }
        return null;
    }
    private Track findTrackByName(String name) throws F1Exception {
        for(int i = 0; i < trackmanager.getAll().size(); i++){
            if(trackmanager.getAll().get(i).getName().equals(name))return trackmanager.getAll().get(i);
        }
        return null;
    }


}
