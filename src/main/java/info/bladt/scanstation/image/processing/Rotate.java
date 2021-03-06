package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rotate {

    private final  Crop crop;

    public Rotate() {
        crop = new Crop();
    }

    public BufferedImage process(BufferedImage input, double angle, boolean retainDimensions) {

        double sin = Math.abs(Math.sin(Math.toRadians(angle)));
        double cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = input.getWidth();
        int h = input.getHeight();
        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newW, newH, input.getType());

        Graphics2D g = rotated.createGraphics();

        g.setPaint(new Color(255, 255, 255));
        g.fillRect(0, 0, newW, newH);

        g.translate((newW - w) / 2, (newH - h) / 2);
        g.rotate(Math.toRadians(angle), w / 2d, h / 2d);
        g.drawRenderedImage(input, null);
        g.dispose();

        if (retainDimensions) {
            rotated = crop.process(rotated, new Crop.Rectangle(h, w));
        }

        return rotated;
    }
}
