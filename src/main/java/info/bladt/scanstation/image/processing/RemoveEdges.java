package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RemoveEdges {

    public BufferedImage process(BufferedImage input, Width width) {

        Graphics2D g = input.createGraphics();

        g.setPaint(new Color(255, 255, 255));

        g.fillRect(0, 0, input.getWidth(), width.getTop());
        g.fillRect(0, input.getHeight() - width.getBottom(), input.getWidth(), width.getBottom());
        g.fillRect(0, 0, width.getLeft(), input.getHeight());
        g.fillRect(input.getWidth() - width.getRight(), 0, input.getWidth(), input.getHeight());

        g.dispose();

        return input;
    }

    public static class Width {
        private final int left;
        private final int top;
        private final int right;
        private final int bottom;

        public Width(int width) {
            this(width, width, width, width);
        }

        public Width(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        public int getLeft() {
            return left;
        }

        public int getTop() {
            return top;
        }

        public int getRight() {
            return right;
        }

        public int getBottom() {
            return bottom;
        }
    }
}
