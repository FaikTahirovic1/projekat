package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Track;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class TrackScreenController {
    private final TrackManager manager = new TrackManager();
    public ListView<Track> trackList;
    public TextField trackName;
    @FXML
    public void initialize() {
        try {
            refreshTracks();
            trackList.getSelectionModel().selectedItemProperty().addListener((obs, o, n)->{
                if (n != null){
                    trackName.setText(n.getName());
                }
            });
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    private void refreshTracks() throws F1Exception{
        try {
            trackList.setItems(FXCollections.observableList(manager.getAll()));
            trackName.setText("");
        } catch (F1Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void createTrack(ActionEvent event){
        openDialog("Track","/fxml/AddTrackPage.fxml", new CreateTrackController());
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
    public void deleteTrack(ActionEvent event){
        try {
            Track track = trackList.getSelectionModel().getSelectedItem();
            manager.delete(track.getId());
            //refreshCategories();
            trackList.getItems().remove(track); // perf optimization
        }catch (F1Exception e){
            new Alert(Alert.AlertType.NONE, "Cannot delete this track as drivers consider it its favourite", ButtonType.OK).show();
        }
    }
    public void updateTrack(ActionEvent event) throws F1Exception {
        refreshTracks();
    }
    public void searchFans(ActionEvent event){

        openDialog("Track fans","/fxml/TrackFansPage.fxml", new TrackFansController());

    }
}
