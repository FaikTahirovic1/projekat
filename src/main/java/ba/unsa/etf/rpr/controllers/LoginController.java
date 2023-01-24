package ba.unsa.etf.rpr.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    @FXML
    public TextField loginField;
    @FXML
    private SimpleStringProperty username;

    public LoginController(){
        username = new SimpleStringProperty("");
    }
    public String getusername(){
        return String.valueOf(username);
    }

    @FXML
    public void initialize() {

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

        System.out.println(loginField.getText());
        String s = getusername();

        openDialog("Home","/fxml/HomePage.fxml",new HomeController(s));
    }

}
