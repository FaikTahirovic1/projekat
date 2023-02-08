package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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

import java.net.URL;
import java.util.ResourceBundle;

public class DriverScreenController {
    private final DriverManager manager = new DriverManager();
    @FXML
    public TextField searchDrivers;
    @FXML
    public TableView driversTable;
    @FXML
    public TableColumn<Driver, String> driversColumn;
    @FXML
    public TableColumn<Driver, Integer> ageColumn;
    @FXML
    public TableColumn<Driver, Track> trackColumn;
    @FXML
    public TableColumn<Driver, Team>teamColumn;


    public void initialize() {
        driversColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("age"));
        trackColumn.setCellValueFactory(new PropertyValueFactory<Driver, Track>("favouriteTrack"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<Driver, Team>("team"));
        refreshDrivers();

    }
    public void searchDrivers(ActionEvent event){
        try {
            driversTable.setItems(FXCollections.observableList(DriverManager.searchDrivers(searchDrivers.getText())));
            driversTable.refresh();
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void showAllDrivers(ActionEvent event) throws F1Exception {
        for(int i = 0; i< DriverManager.getAll().size();i++){
            System.out.println(DriverManager.getAll().get(i).getName());
            //driversTable.setItems();
            ObservableList<Driver> lista = FXCollections.observableList(DriverManager.getAll());
            driversTable.setItems(lista);

        }

    }
    private void refreshDrivers(){
        try {
            System.out.println(manager.getAll().get(0).getTeam());
            driversTable.setItems(FXCollections.observableList(manager.getAll()));

            driversTable.refresh();
        } catch (F1Exception  e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

}
