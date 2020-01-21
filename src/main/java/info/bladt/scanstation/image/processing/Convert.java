package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Convert {

    public BufferedImage process(BufferedImage input, ImageType imageType) {

        BufferedImage binary = new BufferedImage(input.getWidth(), input.getHeight(), imageType.getBufferedImageType());

        Graphics g = binary.getGraphics();
        g.drawImage(input, 0, 0, null);
        g.dispose();

        return binary;
    }

    public enum ImageType {
        BINARY(BufferedImage.TYPE_BYTE_BINARY),
        GRAY(BufferedImage.TYPE_BYTE_GRAY);

        private final int bufferedImageType;

        ImageType(int bufferedImageType) {
            this.bufferedImageType = bufferedImageType;
        }

        public int getBufferedImageType() {
            return bufferedImageType;
        }
    }
}
