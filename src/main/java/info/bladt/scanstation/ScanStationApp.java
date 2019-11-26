package info.bladt.scanstation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScanStationApp extends Application {

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
            e.printStackTrace();
            System.out.println("Wrong");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
