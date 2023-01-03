package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ba.unsa.etf.rpr.business.*;

public class DriverScreenController {
    public Pane driversScreen;
    public TextField searchDrivers;
    public TableView driversTable;
    public TableColumn<Driver, String> driversColumn;
    public TableColumn<Driver, String> teamsColumn;

    void initialize() {
        driversColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        teamsColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("team"));

    }
    public void searchDrivers(ActionEvent event){
        try {
            driversTable.setItems(FXCollections.observableList(DriverManager.searchDrivers(searchDrivers.getText())));
            driversTable.refresh();
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
