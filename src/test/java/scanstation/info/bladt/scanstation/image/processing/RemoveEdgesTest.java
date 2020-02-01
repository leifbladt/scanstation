package scanstation.info.bladt.scanstation.image.processing;

import org.junit.jupiter.api.Test;
import scanstation.info.bladt.scanstation.image.processing.RemoveEdges;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveEdgesTest {

    @Test
    public void process() {

        RemoveEdges removeEdges = new RemoveEdges();

        int imageWidth = 500;
        int imageHeight = 500;
        int edgeWidth = 50;

        RemoveEdges.Width width = new RemoveEdges.Width(edgeWidth);
        BufferedImage bufferedImage = removeEdges.process(createImage(imageWidth, imageHeight, 0), width);

        BufferedImage subimage1 = bufferedImage.getSubimage(0, 0, imageHeight, edgeWidth);
        BufferedImage subimage2 = bufferedImage.getSubimage(0, imageHeight - edgeWidth, imageWidth, edgeWidth);
        BufferedImage subimage3 = bufferedImage.getSubimage(0, 0, edgeWidth, imageHeight);
        BufferedImage subimage4 = bufferedImage.getSubimage(imageWidth - edgeWidth, 0, edgeWidth, imageHeight);
        BufferedImage subimage5 = bufferedImage.getSubimage(edgeWidth, edgeWidth, imageWidth - (2 * edgeWidth), imageHeight - (2 * edgeWidth));

        assertTrue(compareImages(subimage1, createImage(imageWidth, edgeWidth, 255)));
        assertTrue(compareImages(subimage2, createImage(imageWidth, edgeWidth, 255)));
        assertTrue(compareImages(subimage3, createImage(edgeWidth, imageHeight, 255)));
        assertTrue(compareImages(subimage4, createImage(edgeWidth, imageHeight, 255)));
        assertTrue(compareImages(subimage5, createImage(imageWidth - (2 * edgeWidth), imageHeight - (2 * edgeWidth), 0)));
    }

    private BufferedImage createImage(int width, int height, int value) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g = bufferedImage.createGraphics();
        g.setPaint(new Color(value, value, value));
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g.dispose();

        return bufferedImage;
    }

    private boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        // The images must be the same size.
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        int width = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }
}