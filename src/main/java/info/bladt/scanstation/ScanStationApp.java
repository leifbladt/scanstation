package info.bladt.scanstation;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class ScanStationApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        ImageView scanResult = new ImageView();
        scanResult.setFitHeight(640d);
        scanResult.setFitWidth(480d);

        Button proceed = new Button("Weiter?");
        proceed.setVisible(false);

        TextField textField = new TextField("Horn");
        Button button = new Button("Scan");
        button.setOnAction(new ActionEventEventHandler(scanResult, proceed));

        HBox hBox = new HBox(textField, button);


        VBox scanPane = new VBox(hBox, scanResult, proceed);

        Tab tab1 = new Tab("Scan", scanPane);
        Tab tab2 = new Tab("Bearbeitung", new Label("bearbeiten"));

        TabPane tabPane = new TabPane(tab1, tab2);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setHeight(768d);
        primaryStage.setWidth(1024d);
        primaryStage.setTitle("ScanStation");

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static class ActionEventEventHandler implements EventHandler<ActionEvent> {
        private final ImageView imageView;
        private final Button proceed;

        public ActionEventEventHandler(ImageView imageView, Button proceed) {
            this.imageView = imageView;
            this.proceed = proceed;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println("Clicked!");

            Task<Image> scanImage = new Task<>() {
                @Override
                protected Image call() throws Exception {
                    Thread.sleep(500);
                    FileInputStream inputStream = new FileInputStream("kitten.jpg");
                    imageView.setImage(new Image(inputStream));
                    proceed.setVisible(true);
                    return new Image(inputStream);
                }
            };
            new Thread(scanImage).start();
        }
    }
}
