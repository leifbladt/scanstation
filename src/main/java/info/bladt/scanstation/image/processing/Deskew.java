package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.image.processing.Convert.ImageType;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

public class Deskew {

    private final Convert convert;
    private final Rotate rotate;

    public Deskew() {
        convert = new Convert();
        rotate = new Rotate();
    }

    public BufferedImage process(BufferedImage input) {
        double skewRadians = findSkew(convert.process(input, ImageType.BINARY));

        return rotate.process(input, Math.toDegrees(skewRadians));
    }

    static int getByteWidth(final int width) {
        return (width + 7) / 8;
    }

    static int nextPow2(final int n) {
        int retVal = 1;
        while (retVal < n) {
            retVal <<= 1;
        }
        return retVal;
    }

    static double findSkew(final BufferedImage img) {
        final DataBuffer buffer = img.getRaster().getDataBuffer();
        final int byteWidth = getByteWidth(img.getWidth());
        final int padMask = 0xFF << ((img.getWidth() + 7) % 8);
        int elementIndex = 0;
        for (int row = 0; row < img.getHeight(); row++) {
            for (int col = 0; col < byteWidth; col++) {
                int elem = buffer.getElem(elementIndex);
                elem ^= 0xff;// invert colors
                elem = BitUtils.invbits_[elem]; // Change the bit order
                buffer.setElem(elementIndex, elem);
                elementIndex++;
            }
            final int lastElement = buffer.getElem(elementIndex - 1) & padMask;
            buffer.setElem(elementIndex - 1, lastElement); // Zero trailing bits
        }
        final int w2 = nextPow2(byteWidth);
        final int sSize = 2 * w2 - 1; // Size of sharpness table
        final int sharpness[] = new int[sSize];
        radon(img.getWidth(), img.getHeight(), buffer, 1, sharpness);
        radon(img.getWidth(), img.getHeight(), buffer, -1, sharpness);
        int i;
        int iMax = 0;
        int vMax = 0;
        double sum = 0.;
        for (i = 0; i < sSize; i++) {
            final int s = sharpness[i];
            if (s > vMax) {
                iMax = i;
                vMax = s;
            }
            sum += s;
        }
        final int h = img.getHeight();
        if (vMax <= 3 * sum / h) { // Heuristics !!!
            return 0;
        }
        final double iskew = iMax - w2 + 1;
        return Math.atan(iskew / (8 * w2));
    }

    static void radon(final int width, final int height, final DataBuffer buffer, final int sign,
                      final int sharpness[]) {

        int[] p1_, p2_; // Stored columnwise

        final int w2 = nextPow2(getByteWidth(width));
        final int w = getByteWidth(width);
        final int h = height;

        final int s = h * w2;
        p1_ = new int[s];
        p2_ = new int[s];
        // Fill in the first table
        int row, column;
        int scanlinePosition = 0;
        for (row = 0; row < h; row++) {
            scanlinePosition = row * w;
            for (column = 0; column < w; column++) {
                if (sign > 0) {
                    final int b = buffer.getElem(0, scanlinePosition + w - 1 - column);
                    p1_[h * column + row] = BitUtils.bitcount_[b];
                } else {
                    final int b = buffer.getElem(0, scanlinePosition + column);
                    p1_[h * column + row] = BitUtils.bitcount_[b];
                }
            }
        }

        int[] x1 = p1_;
        int[] x2 = p2_;
        // Iterate
        int step = 1;
        for (; ; ) {
            int i;
            for (i = 0; i < w2; i += 2 * step) {
                int j;
                for (j = 0; j < step; j++) {
                    // Columns-sources:
                    final int s1 = h * (i + j);// x1 pointer
                    final int s2 = h * (i + j + step); // x1 pointer

                    // Columns-targets:
                    final int t1 = h * (i + 2 * j); // x2 pointer
                    final int t2 = h * (i + 2 * j + 1); // x2 pointer
                    int m;
                    for (m = 0; m < h; m++) {
                        x2[t1 + m] = x1[s1 + m];
                        x2[t2 + m] = x1[s1 + m];
                        if (m + j < h) {
                            x2[t1 + m] += x1[s2 + m + j];
                        }
                        if (m + j + 1 < h) {
                            x2[t2 + m] += x1[s2 + m + j + 1];
                        }
                    }
                }
            }

            // Swap the tables:
            final int[] aux = x1;
            x1 = x2;
            x2 = aux;
            // Increase the step:
            step *= 2;
            if (step >= w2) {
                break;
            }
        }
        // Now, compute the sum of squared finite differences:
        for (column = 0; column < w2; column++) {
            int acc = 0;
            final int col = h * column;
            for (row = 0; row + 1 < h; row++) {
                final int diff = x1[col + row] - x1[col + row + 1];
                acc += diff * diff;
            }
            sharpness[w2 - 1 + sign * column] = acc;
        }
    }

    static class BitUtils {
        static int[] bitcount_ = new int[256];
        static int[] invbits_ = new int[256];

        static {
            for (int i = 0; i < 256; i++) {
                int j = i, cnt = 0;
                do {
                    cnt += j & 1;
                } while ((j >>= 1) != 0);
                int x = (i << 4) | (i >> 4);
                x = ((x & 0xCC) >> 2) | ((x & 0x33) << 2);
                x = ((x & 0xAA) >> 1) | ((x & 0x55) << 1);
                bitcount_[i] = cnt;
                invbits_[i] = x;
            }
        }

        private BitUtils() {}
    }

}
