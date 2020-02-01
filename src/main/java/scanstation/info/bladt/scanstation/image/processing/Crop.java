package scanstation.info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crop {

    public BufferedImage process(BufferedImage input, Rectangle rectangle) {

        BufferedImage cropped = input.getSubimage(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        BufferedImage copyOfImage = new BufferedImage(cropped.getWidth(), cropped.getHeight(), input.getType());
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(cropped, 0, 0, null);
        g.dispose();

        return copyOfImage;
    }

    public static class Rectangle {
        private final int x;
        private final int y;
        private final int height;
        private final int width;

        public Rectangle(int height, int width) {
            this(0, 0, height, width);
        }

        public Rectangle(int x, int y, int height, int width) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
