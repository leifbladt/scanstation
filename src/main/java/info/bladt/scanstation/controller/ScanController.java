package info.bladt.scanstation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class ScanController {

    @FXML
    private Button scanButton;

    @FXML
    private ImageView imageView;

    @FXML
    private HBox proceed;

    public ScanController() {
    }

    @FXML
    private void initialize() {
        scanButton.setOnAction(new ActionEventEventHandler(imageView, proceed));
    }
}
