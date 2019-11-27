package info.bladt.scanstation.controller;

import info.bladt.scanstation.image.scan.ScanModule;
import info.bladt.scanstation.image.scan.ScannerFactory;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class ScanController {

    @FXML
    private Label pageLabel;

    @FXML
    private Button scanButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button retryButton;

    @FXML
    private Button finishButton;

    @FXML
    private ImageView imageView;

    @FXML
    private HBox proceed;

    @FXML
    private ChoiceBox<String> scannerChoiceBox;

    private ScanModule scanModule;

    public ScanController() {
    }

    @FXML
    private void initialize() {
        scanModule = new ScanModule();

        scanButton.setOnAction(new ScanEventHandler());
        nextPageButton.setOnAction(new NextPageEventHandler());
        retryButton.setOnAction(new RetryEventHandler());
        finishButton.setOnAction(new FinishEventHandler());

        scannerChoiceBox.setItems(FXCollections.observableArrayList("Demo", "Canon LiDE 210"));
        scannerChoiceBox.setValue("Demo");
    }

    private class ScanEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            scanButton.setDisable(true);

            Task<Void> scanImage = new Task<>() {
                @Override
                protected Void call() {
                    scanModule.setScanner(ScannerFactory.getScanner(scannerChoiceBox.getValue()));
                    scanModule.scanPage();
                    return null;
                }
            };

            scanImage.setOnSucceeded(e -> {
                Image image = SwingFXUtils.toFXImage(scanModule.getPreview(), null);

                pageLabel.setText(scanModule.getPage() + "");
                imageView.setImage(image);
                proceed.setVisible(true);
            });

            new Thread(scanImage).start();
        }
    }

    private class NextPageEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Task<Void> scanImage = new Task<>() {
                @Override
                protected Void call() {
                    scanModule.scanNextPage();
                    return null;
                }
            };

            scanImage.setOnSucceeded(e -> {
                Image image = SwingFXUtils.toFXImage(scanModule.getPreview(), null);

                pageLabel.setText(scanModule.getPage() + "");
                imageView.setImage(image);
            });

            new Thread(scanImage).start();
        }
    }

    private class RetryEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Task<Void> scanImage = new Task<>() {
                @Override
                protected Void call() {
                    scanModule.scanPageAgain();
                    return null;
                }
            };

            scanImage.setOnSucceeded(e -> {
                Image image = SwingFXUtils.toFXImage(scanModule.getPreview(), null);

                pageLabel.setText(scanModule.getPage() + "");
                imageView.setImage(image);
            });

            new Thread(scanImage).start();
        }
    }

    private class FinishEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Task<Void> scanImage = new Task<>() {
                @Override
                protected Void call() {
                    scanModule.finish();
                    return null;
                }
            };

            scanImage.setOnSucceeded(e -> {
                proceed.setVisible(false);
                imageView.setImage(null);
                scanButton.setDisable(false);
            });

            new Thread(scanImage).start();
        }
    }
}
