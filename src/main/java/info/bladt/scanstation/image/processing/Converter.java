package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Converter {

    private Converter() {
    }

    public static BufferedImage toBinary(BufferedImage input) {

        BufferedImage binary = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        Graphics biG = binary.getGraphics();
        biG.drawImage(input, 0, 0, null);
        biG.dispose();

        return binary;
    }
}
