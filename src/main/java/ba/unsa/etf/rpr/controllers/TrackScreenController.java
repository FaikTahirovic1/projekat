package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Track;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    public void searchTrack(ActionEvent event){

    }
    public void deleteTrack(ActionEvent event){
        try {
            Track track = trackList.getSelectionModel().getSelectedItem();
            manager.delete(track.getId());
            //refreshCategories();
            trackList.getItems().remove(track); // perf optimization
        }catch (F1Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void updateTrack(ActionEvent event){}
}
