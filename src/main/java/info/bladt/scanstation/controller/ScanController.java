package info.bladt.scanstation.controller;

import info.bladt.scanstation.image.export.PdfExporter;
import info.bladt.scanstation.image.scan.ScanModule;
import info.bladt.scanstation.image.scan.ScannerFactory;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static info.bladt.scanstation.model.Composition.COMPOSITIONS;
import static info.bladt.scanstation.model.Instrument.INSTRUMENTS;


public class ScanController {

    private static final Logger LOGGER = LogManager.getLogger(ScanController.class);

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
    private ChoiceBox<Composition> compositionChoiceBox;

    @FXML
    private ChoiceBox<Instrument> instrumentChoiceBox;

    @FXML
    private ChoiceBox<String> scannerChoiceBox;

    @FXML
    private Button exportButton;

    private ScanModule scanModule;

    @FXML
    private void initialize() {
        scanModule = new ScanModule();

        scanButton.setOnAction(new ScanEventHandler());
        nextPageButton.setOnAction(new NextPageEventHandler());
        retryButton.setOnAction(new RetryEventHandler());
        finishButton.setOnAction(new FinishEventHandler());
        exportButton.setOnAction(new ExportEventHandler());

        compositionChoiceBox.setItems(FXCollections.observableArrayList(COMPOSITIONS));
        compositionChoiceBox.setValue(COMPOSITIONS.get(0));

        instrumentChoiceBox.setItems(FXCollections.observableArrayList(INSTRUMENTS));
        instrumentChoiceBox.setValue(INSTRUMENTS.get(0));

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
                    scanModule.setComposition(compositionChoiceBox.getValue());
                    scanModule.setInstrument(instrumentChoiceBox.getValue());
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

            scanImage.setOnFailed(e -> {
                LOGGER.error("Failed to scan image ({})", e.toString());
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

            scanImage.setOnFailed(e -> {
                LOGGER.error("Failed to scan next page ({})", e.toString());
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
                scanModule.reset();

                proceed.setVisible(false);
                imageView.setImage(null);
                scanButton.setDisable(false);
            });

            scanImage.setOnFailed(e -> {
                LOGGER.error("Failed to finish ({})", e.toString());
            });

            new Thread(scanImage).start();
        }
    }

    private class ExportEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Task<Void> export = new Task<>() {
                @Override
                protected Void call() {
                    exportButton.setDisable(true);
                    PdfExporter pdfExporter = new PdfExporter();
                    pdfExporter.savePdf(compositionChoiceBox.getValue(), instrumentChoiceBox.getValue());
                    return null;
                }
            };

            export.setOnSucceeded(e -> {
                LOGGER.info("Successfully saved PDF file");
                exportButton.setDisable(false);
            });

            export.setOnFailed(e -> {
                LOGGER.error("Failed to save PDF file ({})", e.toString());
                exportButton.setDisable(false);
            });

            new Thread(export).start();
        }
    }
}
