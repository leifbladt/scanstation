package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RemoveEdges {

    public BufferedImage process(BufferedImage input, int width) {

        Graphics2D g = input.createGraphics();

        g.setPaint(new Color(255, 255, 255));

        g.fillRect(0, 0, input.getWidth(), width);
        g.fillRect(0, input.getHeight() - width, input.getWidth(), width);
        g.fillRect(0, 0, width, input.getHeight());
        g.fillRect(input.getWidth() - width, 0, input.getWidth(), input.getHeight());

        g.dispose();

        return input;
    }
}
