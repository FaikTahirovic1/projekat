package ba.unsa.etf.rpr.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    public String username;
    @FXML
    public Text mainmenu;
    public HomeController(String s){
        username=new String(s);
        mainmenu=new Text();
        mainmenu.setText(s);

    }
    @FXML

    void initialize() {


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
    public void openHome(ActionEvent actionevent){
        openDialog("Home","/fxml/HomePage.fxml",null);
    }
    public void openDriverScreen(ActionEvent actionEvent){
        openDialog("Drivers","/fxml/DriverScreen.fxml", new DriverScreenController());
    }
    public void openTrackScreen(ActionEvent actionEvent){
        openDialog("Tracks","/fxml/TrackScreen.fxml", new TrackScreenController());
    }
}
