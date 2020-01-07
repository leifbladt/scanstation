package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rotate {

    public BufferedImage process(BufferedImage input, double angle) {

        int w = input.getWidth();
        int h = input.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, input.getType());

        Graphics2D g = rotated.createGraphics();

        g.setPaint(new Color(255, 255, 255));
        g.fillRect(0, 0, w, h);

        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawImage(input, null, 0, 0);
        g.dispose();

        return rotated;
    }
}
