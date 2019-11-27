package info.bladt.scanstation.image.acquisition.demo;

import info.bladt.scanstation.image.acquisition.Acquisition;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DemoAcquisition implements Acquisition {

    private int page = 1;

    @Override
    public BufferedImage acquireImage() {
        try {
            Thread.sleep(250);

            BufferedImage image = ImageIO.read(DemoAcquisition.class.getResourceAsStream(String.format("page_%02d.jpg", page)));
            page++;
            return image;
        } catch (InterruptedException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
