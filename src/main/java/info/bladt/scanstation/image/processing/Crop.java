package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crop {

    public BufferedImage process(BufferedImage input, Rectangle rectangle) {

//        BufferedImage cropped = input.getSubimage(0, 0, input.getWidth(), input.getHeight()); //fill in the corners of the desired crop location here
//        BufferedImage cropped = input.getSubimage(0, 0, 3970, 3400); //fill in the corners of the desired crop location here
//        BufferedImage cropped = input.getSubimage(0, 0, 3140, 4250); //fill in the corners of the desired crop location here
        BufferedImage cropped = input.getSubimage(0, 0, rectangle.getWidth(), rectangle.getHeight()); //fill in the corners of the desired crop location here

        BufferedImage copyOfImage = new BufferedImage(cropped.getWidth(), cropped.getHeight(), input.getType());
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(cropped, 0, 0, null);
        g.dispose();

        return copyOfImage;
    }

    public static class Rectangle {
        private final int height;
        private final int width;

        public Rectangle(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getWidth() {
            return 0;
        }

        public int getHeight() {
            return 0;
        }
    }
}
