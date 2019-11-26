package info.bladt.scanstation.image.acquisition.demo;

import info.bladt.scanstation.image.acquisition.Acquisition;
import javafx.scene.image.Image;

public class DemoAcquisition implements Acquisition {

    @Override
    public Image acquireImage() {
        try {
            Thread.sleep(500);
            return new Image(DemoAcquisition.class.getResourceAsStream("/demo/kitten.jpg"));
        } catch (InterruptedException e) {
            return null;
        }
    }
}
