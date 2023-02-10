package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TeamManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddNewTeamController {
    TeamManager tm = new TeamManager();

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
}
