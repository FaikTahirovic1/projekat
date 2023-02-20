package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.domain.Driver;
import ba.unsa.etf.rpr.domain.Team;
import ba.unsa.etf.rpr.domain.Track;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for driver management
 *
 * @author FaikTahirovic1
 */
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
            driversTable.setItems(FXCollections.observableList(manager.getAll()));
            driversTable.refresh();
        } catch (F1Exception  e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void sortByAge() throws F1Exception {
        ArrayList<Driver> vozaci = (ArrayList<Driver>) manager.getAll();
        Collections.sort(vozaci,new Comparator<Driver>(){
            public int compare(Driver o1, Driver o2)
            {
                if(o1.getAge()<o2.getAge())return -1;
                if(o1.getAge()>o2.getAge())return 1;
                return 0;

            }
        });
        for(int i = 0; i<vozaci.size();i++)
        System.out.println(vozaci.get(i));
        driversTable.setItems(FXCollections.observableList(vozaci));
        driversTable.refresh();
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
    public void goHome(ActionEvent actionEvent){
        openDialog("Home","/fxml/HomePage.fxml",new HomeController(""));
    }
    public void closeAll(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
    public void deleteDriver(ActionEvent actionEvent) throws F1Exception {
        Driver d = new Driver();
        d = findByName(searchDrivers.getText());
        manager.delete(d.getId());

    }
    public void editDriver(ActionEvent actionEvent){

    }
    public void createDriver(ActionEvent actionEvent){
        openDialog("Create driver","/fxml/createDriverScreen.fxml",new CreateDriverController());


    }
    private Driver findByName(String name) throws F1Exception {
        for(int i = 0; i < manager.getAll().size(); i++){
            if(manager.getAll().get(i).getName().equals(name))return manager.getAll().get(i);
        }
        return null;
    }

}
