package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AppFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("OK"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home-layout.fxml"));
        HomeController homeController = new HomeController();
        fxmlLoader.setController(homeController);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Home");
        stage.show();
    }
}
