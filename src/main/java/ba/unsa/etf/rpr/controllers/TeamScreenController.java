package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TeamManager;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class TeamScreenController {
    private final TeamManager tim = new TeamManager();
    private final DriverManager dm = new DriverManager();
    @FXML
    public TableView teamTable;
    @FXML
    public TextField searchTeam;
    @FXML
    public TableColumn<Team, String> teamName;
    @FXML
    public TableColumn<Team, String> teamCountry;
    /*@FXML
    public TableColumn<Team, String> driver1;
    @FXML
    public TableColumn<Team, String> driver2;*/
    public void goHome(ActionEvent actionEvent){
        openDialog("Home","/fxml/HomePage.fxml",new HomeController(""));

    }
    public void closeApp(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);

    }
    public void openAbout(ActionEvent actionEvent){
        openDialog("About", "/fxml/about.fxml", null);

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
    public void initialize() {
        teamName.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        teamCountry.setCellValueFactory(new PropertyValueFactory<Team, String>("country"));
        //trackColumn.setCellValueFactory(new PropertyValueFactory<Driver, Track>("favouriteTrack"));
        //teamColumn.setCellValueFactory(new PropertyValueFactory<Driver, Team>("team"));
        refreshTeams();
    }
    private void refreshTeams(){
        try {
            teamTable.setItems(FXCollections.observableList(tim.getAll()));
            teamTable.refresh();
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

}
