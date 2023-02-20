package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.DriverManager;
import ba.unsa.etf.rpr.business.TeamManager;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for team management
 *
 * @author FaikTahirovic1
 */
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
    @FXML
    public DialogPane dialogP;
    /*@FXML
    public TableColumn<SpecialKindOfTeam, Driver> driver1;
    @FXML
    public TableColumn<SpecialKindOfTeam, Driver> driver2;*/
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
    public void addNewTeam(ActionEvent actionEvent){
        openDialog("Add new team", "/fxml/addNewTeam.fxml", new AddNewTeamController());

    }
    public void findHisTeam(ActionEvent actionEvent) throws F1Exception {
        String driverName = searchTeam.getText();
        ArrayList<Team>hisTeam = new ArrayList<>();
        ArrayList<Driver> drivers = (ArrayList<Driver>) dm.getAll();
        for(int i = 0; i < drivers.size(); i++){
            if(driverName.equals(drivers.get(i).getName()))hisTeam.add(drivers.get(i).getTeam());
        }
        teamTable.setItems(FXCollections.observableArrayList(hisTeam));
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
    public void initialize() throws F1Exception {
        teamName.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        teamCountry.setCellValueFactory(new PropertyValueFactory<Team, String>("country"));
        //driver1.setCellValueFactory(new PropertyValueFactory<SpecialKindOfTeam, Driver>("d1"));
        //driver2.setCellValueFactory(new PropertyValueFactory<SpecialKindOfTeam, Driver>("d2"));
        refreshTeams();
        String s = "Number of Teams: " + tim.getAll().size();
        dialogP.setContentText(s);
    }
    private class SpecialKindOfTeam{
        private String name;
        private String country;
        private Driver d1;
        private Driver d2;

        public void setName(String name) {
            this.name = name;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setD1(Driver d1) {
            this.d1 = d1;
        }

        public void setD2(Driver d2) {
            this.d2 = d2;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public Driver getD1() {
            return d1;
        }

        public Driver getD2() {
            return d2;
        }

        public SpecialKindOfTeam(Team team) throws F1Exception {
            this.name=team.getName();
            this.country=team.getCountry();

            ArrayList<Driver> vozaci = (ArrayList<Driver>)dm.getAll();
            int x = 0;
            for(int i = 0; i< dm.getAll().size(); i++){
                if(vozaci.get(i).getTeam().getId() == team.getId()){
                    if( x == 1) d2 = vozaci.get(i);
                    if(x == 0){
                        d1 = vozaci.get(i);
                        x = x + 1;
                    }
                }
            }

        }
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
