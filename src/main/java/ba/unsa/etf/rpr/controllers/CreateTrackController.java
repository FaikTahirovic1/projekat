package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Exception.F1Exception;
import ba.unsa.etf.rpr.business.TrackManager;
import ba.unsa.etf.rpr.domain.Time;
import ba.unsa.etf.rpr.domain.Track;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static java.lang.Integer.parseInt;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for creating new track management
 *
 * @author FaikTahirovic1
 */
public class CreateTrackController {

    private final TrackManager manager = new TrackManager();
    public TextField trackName;
    public TextField trackCountry;
    public TextField trackTime;
    public TextField trackId;
    public DialogPane dialogtext;
    private boolean isLetter(char x){
        if(x >= 'a' && x <= 'z') return true;
        if(x >= 'A' && x <= 'Z')return  true;
        return false;
    }
    private boolean checkNameFormat(String name){
        if(name.length() == 0)return false;
        for(int i = 0; i < name.length() ; i++){
            if(name.charAt(i) != ' ' && !isLetter(name.charAt(i)))return false;
        }
        return true;
    }
    private boolean isDigit(char x){
        if(x >= '0' && x <= '9') return true;
        return false;
    }

    private boolean checkNumberFormat(String n){
        if(n.length() == 0) return false;
        for(int i = 0; i < n.length(); i++)if(!isDigit(n.charAt(i)))return false;
        return true;
    }

    public void createTrack(ActionEvent event) throws F1Exception {
        Track staza = new Track();
        if(!checkNumberFormat(trackId.getText()) || trackId.getText()==null){
            dialogtext.setContentText("Incorrect id!");
            return;
        }
        if(!checkNumberFormat(trackTime.getText()) || trackTime.getText()==null){
            dialogtext.setContentText("Incorrect time!");
            return;
        }
        Time vr = new Time(parseInt(trackTime.getText()));
        staza.setBestTime(vr);
        staza.setId(parseInt(trackId.getText()));
        staza.setCountry(trackCountry.getText());
        staza.setName(trackName.getText());
        if(!checkNameFormat(trackName.getText())){
            dialogtext.setContentText("Incorrect name!");
            return;
        }
        if(!checkNameFormat(trackCountry.getText())){
            dialogtext.setContentText("Incorrect country!");
            return;
        }

        manager.add(staza);
        dialogtext.setContentText("Track created!");

    }
    public void discard(ActionEvent event){
        openDialog("Tracks","/fxml/TrackScreen.fxml", new TrackScreenController());
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
    public void goHome(ActionEvent event){
        openDialog("Home","/fxml/HomePage.fxml", new HomeController(""));
    }
}
