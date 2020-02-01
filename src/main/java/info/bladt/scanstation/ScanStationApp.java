package info.bladt.scanstation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"info.bladt.scanstation"})
public class ScanStationApp extends Application {

    private static final Logger LOGGER = LogManager.getLogger(ScanStationApp.class);
    private ConfigurableApplicationContext springContext;
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage primaryStage) {

        try {
            fxmlLoader = new FXMLLoader(ScanStationApp.class.getResource("Main.fxml"));

            // Load scene
            VBox pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ScanStation");

            primaryStage.show();
        } catch (Exception e) {
            LOGGER.error("Could not start application", e);
        }
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(ScanStationApp.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    public static void main(String[] unused) {
        Application.launch();
    }
}
