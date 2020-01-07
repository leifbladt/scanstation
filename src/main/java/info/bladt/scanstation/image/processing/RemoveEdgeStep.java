package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RemoveEdgeStep implements ProcessingStep {

    @Override
    public BufferedImage process(BufferedImage input, ProcessingStep.Configuration configuration) {

        int width = ((Configuration)configuration).getWidth();

        Graphics2D g = input.createGraphics();

        g.setPaint(new Color(255, 255, 255));

        g.fillRect(0, 0, input.getWidth(), width);
        g.fillRect(0, input.getHeight() - width, input.getWidth(), width);
        g.fillRect(0, 0, width, input.getHeight());
        g.fillRect(input.getWidth() - width, 0, input.getWidth(), input.getHeight());

        g.dispose();

        return input;
    }

    public static class Configuration extends ProcessingStep.Configuration {
        private final int width;

        public Configuration(int width) {
            this.width = width;
        }

        public int getWidth() {
            return width;
        }
    }
}
