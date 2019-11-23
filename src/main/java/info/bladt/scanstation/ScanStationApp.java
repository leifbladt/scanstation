package info.bladt.scanstation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScanStationApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button proceed = new Button("Weiter?");
        proceed.setVisible(false);

        TextField textField = new TextField("Horn");
        Button button = new Button("Scan");
        button.setOnAction(actionEvent -> {
            System.out.println("Clicked!");
            proceed.setVisible(true);
        });

        HBox hBox = new HBox(textField, button);

        Label scanResult = new Label();

        VBox scanPane = new VBox(hBox, scanResult, proceed);

        Tab tab1 = new Tab("Scan", scanPane);
        Tab tab2 = new Tab("Bearbeitung"  , new Label("bearbeiten"));

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
}
