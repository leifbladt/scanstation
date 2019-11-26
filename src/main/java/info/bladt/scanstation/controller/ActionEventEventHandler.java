package info.bladt.scanstation.controller;

import info.bladt.scanstation.image.acquisition.Acquisition;
import info.bladt.scanstation.image.acquisition.AcquisitionFactory;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ActionEventEventHandler implements EventHandler<ActionEvent> {
    private final ImageView imageView;
    private final HBox proceed;

    public ActionEventEventHandler(ImageView imageView, HBox proceed) {
        this.imageView = imageView;
        this.proceed = proceed;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Clicked!");

        Task<Image> scanImage = new Task<>() {
            @Override
            protected Image call() {

                Acquisition acquisition = AcquisitionFactory.getAcquisition();
                Image image = acquisition.acquireImage();

                imageView.setImage(image);
                proceed.setVisible(true);
                return image;
            }
        };
        new Thread(scanImage).start();
    }
}
