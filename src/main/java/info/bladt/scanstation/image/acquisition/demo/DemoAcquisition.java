package info.bladt.scanstation.image.acquisition.demo;

import info.bladt.scanstation.image.acquisition.Acquisition;
import javafx.scene.image.Image;

public class DemoAcquisition implements Acquisition {

    private int page = 1;

    @Override
    public Image acquireImage() {
        try {
            Thread.sleep(250);

            Image image = new Image(DemoAcquisition.class.getResourceAsStream(String.format("page_%02d.jpg", page)));
            page++;
            return image;
        } catch (InterruptedException e) {
            return null;
        }
    }
}
