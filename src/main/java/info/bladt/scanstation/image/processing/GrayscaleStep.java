package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayscaleStep implements ProcessingStep {

    @Override
    public BufferedImage process(BufferedImage input, Configuration configuration) {

        BufferedImage gray = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = gray.getGraphics();
        g.drawImage(input, 0, 0, null);
        g.dispose();

        return gray;
    }
}
