package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RotateStep implements ProcessingStep {

    @Override
    public BufferedImage process(BufferedImage input, ProcessingStep.Configuration configuration) {

        int w = input.getWidth();
        int h = input.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, input.getType());

        Graphics2D g = rotated.createGraphics();

        g.setPaint(new Color(255, 255, 255));
        g.fillRect(0, 0, w, h);

        g.rotate(Math.toRadians(((Configuration)configuration).getAngle()), w / 2, h / 2);
        g.drawImage(input, null, 0, 0);
        g.dispose();

        return rotated;
    }

    public static class Configuration extends ProcessingStep.Configuration {
        private final double angle;

        public Configuration(double angle) {
            this.angle = angle;
        }

        public double getAngle() {
            return angle;
        }
    }
}
