package info.bladt.scanstation.controller;

import info.bladt.scanstation.image.Configuration;
import info.bladt.scanstation.image.ConfigurationService;
import info.bladt.scanstation.image.export.ExportService;
import info.bladt.scanstation.image.processing.ProcessingService;
import info.bladt.scanstation.image.scan.ScanModule;
import info.bladt.scanstation.image.scan.ScanResults;
import info.bladt.scanstation.image.scan.ScannerFactory;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.CompositionModule;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static info.bladt.scanstation.model.Instrument.INSTRUMENTS;


@Controller
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
    private Button refreshCompositionButton;

    @FXML
    private ChoiceBox<Instrument> scanInstrumentChoiceBox;

    @FXML
    private ChoiceBox<String> scannerChoiceBox;

    @FXML
    private Button refreshInstrumentsButton;

    @FXML
    private ChoiceBox<Instrument> editInstrumentChoiceBox;

    @FXML
    private Button editAndExportButton;

    @FXML
    private Button editAndExportAllButton;

    private ScanModule scanModule;

    private CompositionModule compositionModule;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private ProcessingService processingService;

    @Autowired
    private ExportService exportService;

    @FXML
    private void initialize() {

        scanModule = new ScanModule();
        compositionModule = new CompositionModule();

        scanButton.setOnAction(new ScanEventHandler());
        nextPageButton.setOnAction(new NextPageEventHandler());
        retryButton.setOnAction(new RetryEventHandler());
        finishButton.setOnAction(new FinishEventHandler());
        editAndExportButton.setOnAction(new EditAndExportEventHandler());
        editAndExportAllButton.setOnAction(new EditAndExportAllEventHandler());
        refreshCompositionButton.setOnAction(new RefreshCompositionHandler());
        refreshInstrumentsButton.setOnAction(new RefreshInstrumentsHandler());

        List<Composition> compositions = compositionModule.getCompositions();
        compositionChoiceBox.setItems(FXCollections.observableArrayList(compositions));
        compositionChoiceBox.setValue(compositions.get(0));

        scanInstrumentChoiceBox.setItems(FXCollections.observableArrayList(INSTRUMENTS));
        scanInstrumentChoiceBox.setValue(INSTRUMENTS.get(0));

        editInstrumentChoiceBox.setItems(FXCollections.observableArrayList(INSTRUMENTS));
        editInstrumentChoiceBox.setValue(INSTRUMENTS.get(0));

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
                    scanModule.setInstrument(scanInstrumentChoiceBox.getValue());
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

            scanImage.setOnFailed(e -> LOGGER.error("Failed to scan image ({})", e));

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

            scanImage.setOnFailed(e -> LOGGER.error("Failed to scan next page ({})", e));

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

            scanImage.setOnFailed(e -> LOGGER.error("Failed to finish ({})", e));

            new Thread(scanImage).start();
        }
    }

    private class EditAndExportEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Task<Void> export = new Task<>() {
                @Override
                protected Void call() {
                    editAndExportButton.setDisable(true);
                    editAndExportAllButton.setDefaultButton(true);

                    Composition composition = compositionChoiceBox.getValue();
                    Configuration configuration = configurationService.getConfiguration(composition);

                    processingService.process(composition, editInstrumentChoiceBox.getValue(), configuration.getProcessingConfiguration());

                    exportService.savePdf(composition, editInstrumentChoiceBox.getValue(), configuration.getExportConfiguration());

                    return null;
                }
            };

            export.setOnSucceeded(e -> {
                LOGGER.info("Successfully saved PDF file");
                editAndExportButton.setDisable(false);
                editAndExportAllButton.setDefaultButton(false);
            });

            export.setOnFailed(e -> {
                LOGGER.error("Failed to save PDF file ({})", e);
                editAndExportButton.setDisable(false);
                editAndExportAllButton.setDisable(false);
            });

            new Thread(export).start();
        }
    }

    private class EditAndExportAllEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Task<Void> export = new Task<>() {
                @Override
                protected Void call() {
                    editAndExportButton.setDisable(true);
                    editAndExportAllButton.setDisable(true);

                    Composition composition = compositionChoiceBox.getValue();
                    Configuration configuration = configurationService.getConfiguration(composition);

                    for (Instrument instrument : editInstrumentChoiceBox.getItems()) {
                        LOGGER.debug("Process {}", instrument);
                        processingService.process(composition, instrument, configuration.getProcessingConfiguration());
                        exportService.savePdf(composition, instrument, configuration.getExportConfiguration());
                    }

                    return null;
                }
            };

            export.setOnSucceeded(e -> {
                LOGGER.info("Successfully saved PDF file");
                editAndExportButton.setDisable(false);
                editAndExportAllButton.setDisable(false);
            });

            export.setOnFailed(e -> {
                LOGGER.error("Failed to save PDF file ({})", e);
                editAndExportButton.setDisable(false);
                editAndExportAllButton.setDisable(false);
            });

            new Thread(export).start();
        }
    }

    private class RefreshInstrumentsHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            ScanResults scanResults = new ScanResults();
            editInstrumentChoiceBox.setItems(FXCollections.observableArrayList(scanResults.getInstruments(compositionChoiceBox.getValue())));
            editInstrumentChoiceBox.setValue(editInstrumentChoiceBox.getItems().get(0));
        }
    }

    private class RefreshCompositionHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            LOGGER.debug("Refresh compositions");
            Composition value = compositionChoiceBox.getValue();
            List<Composition> compositions = compositionModule.getCompositions();
            compositionChoiceBox.setItems(FXCollections.observableArrayList(compositions));
            compositionChoiceBox.setValue(value);
        }
    }
}
