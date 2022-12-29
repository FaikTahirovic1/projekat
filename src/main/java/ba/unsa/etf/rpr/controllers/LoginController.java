package ba.unsa.etf.rpr.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML

    void initialize() {

    }
    public void closeApp(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
}
