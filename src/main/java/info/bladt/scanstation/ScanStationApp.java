package info.bladt.scanstation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScanStationApp extends Application {

    private final static Logger LOGGER = LogManager.getLogger(ScanStationApp.class);

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(ScanStationApp.class.getResource("Main.fxml"));

            // Load scene
            VBox pane = loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ScanStation");

            primaryStage.show();
        } catch (Exception e) {
            LOGGER.error("Could not start application", e);
        }
    }

    public static void main(String[] unused) {
        Application.launch();
    }
}
