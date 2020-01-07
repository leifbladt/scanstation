package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BinaryStep implements ProcessingStep {

    @Override
    public BufferedImage process(BufferedImage input, Configuration configuration) {

        BufferedImage binary = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        Graphics g = binary.getGraphics();
        g.drawImage(input, 0, 0, null);
        g.dispose();

        return binary;
    }
}
