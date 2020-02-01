package scanstation.info.bladt.scanstation.image.scan.demo;

import scanstation.info.bladt.scanstation.image.scan.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DemoScanner implements Scanner {

    private static final Logger LOGGER = LogManager.getLogger(DemoScanner.class);

    private int page = 1;

    @Override
    public BufferedImage acquireImage() {
        try {
            BufferedImage image = ImageIO.read(DemoScanner.class.getResourceAsStream(String.format("page_%02d.jpg", page)));
            page++;
            return image;
        } catch (IOException e) {
            LOGGER.error("Could acquire image", e);
            return null;
        }
    }
}
