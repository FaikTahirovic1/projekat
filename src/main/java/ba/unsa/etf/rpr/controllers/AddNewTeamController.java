package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddNewTeamController {
    TeamManager tm = new TeamManager();
    @FXML
    private TextField teamid;
    @FXML
    private TextField teamname;
    @FXML
    private TextField teamcountry;
    @FXML
    private ChoiceBox<String> cb;
    @FXML
    public void initialize() throws F1Exception {
        ArrayList<Team> timovi = (ArrayList<Team>) tm.getAll();
        String teamString[] = new String[timovi.size()];
        for(int i = 0; i<timovi.size(); i++){
            teamString[i] = timovi.get(i).toString();
        }
        //System.out.println(teamString[1]);
        cb.getItems().addAll(teamString);
        //System.out.println("rad");
        cb.getSelectionModel().selectedItemProperty().addListener((obs, o, n)-> {
                    if (n != null) {
                        //teamid.setText(n.getid());
                        Team ekipa = new Team();
                        try {
                            ekipa = findThisTeam(n);
                        } catch (F1Exception e) {
                            throw new RuntimeException(e);
                        }
                        teamid.setText(String.valueOf(ekipa.getId()));
                        teamcountry.setText(ekipa.getCountry());
                        teamname.setText(ekipa.getName());


                    }
                });
        refreshTeams();
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
    public void closeApp(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
    public  void goHome(ActionEvent actionEvent){
        openDialog("Home","/fxml/HomePage.fxml",new HomeController(""));
    }
    public void goToTeamScreen(ActionEvent actionEvent){
        openDialog("Teams","/fxml/TeamScreen.fxml", new TeamScreenController());
    }
    public void editThisTeam(ActionEvent actionEvent){
        try {
            tm.delete(Integer.parseInt(teamid.getText()));
            Integer i = Integer.parseInt(teamid.getText());
            String n = teamname.getText();
            String c = teamcountry.getText();
            Team tim = new Team(i, n, c);
            tm.add(tim);
        }catch(F1Exception e){
            new Alert(Alert.AlertType.NONE, "Cannot edit this team as drivers drive for it", ButtonType.OK).show();
        }

    }
    public void addThisTeam(ActionEvent actionEvent) throws F1Exception {
        Integer i = Integer.parseInt(teamid.getText());
        String n = teamname.getText();
        String c = teamcountry.getText();
        Team tim = new Team(i,n,c);
        tm.add(tim);
    }
    private void refreshTeams() throws F1Exception{
        try {
            ArrayList<String> teamNames = new ArrayList<>();
            ArrayList<Team> timovi = (ArrayList<Team>) tm.getAll();
            for(int i = 0; i < timovi.size(); i++) {
                teamNames.add(timovi.get(i).getName());
            }
            cb.setItems(FXCollections.observableArrayList(teamNames));
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    private Team findThisTeam(String name) throws F1Exception {
        for(int i = 0; i < tm.getAll().size(); i++){
            if(name.equals(tm.getAll().get(i).getName()))return tm.getAll().get(i);
        }
        return null;
    }
}
