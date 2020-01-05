package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.image.file.TiffReader;
import info.bladt.scanstation.image.file.TiffWriter;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.util.List;

public class Converter {

    private static final Logger LOGGER = LogManager.getLogger(Converter.class);

    private Converter() {
    }

    public static void process(Composition composition, Instrument instrument) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition, instrument);
            for (TiffReader.Page inputImage : inputImages) {
                BufferedImage bufferedImage = ImageIO.read(inputImage.getPath().toFile());

                BufferedImage croppedImage = crop(bufferedImage);
//                BufferedImage rotatedImage = rotate(croppedImage, 180);
                BufferedImage deskewedImage = deskew(croppedImage);
                BufferedImage cleanedImage = removeEdge(deskewedImage, 70);
//                BufferedImage binaryImage = toBinary(bufferedImage);

                TiffWriter.saveImage(cleanedImage, "Work", inputImage.getPage(), composition, instrument);
            }
        } catch (IOException e) {
            LOGGER.error("Error creating PDF file", e);
        }
    }

    public static BufferedImage crop(BufferedImage input) {

        BufferedImage cropped = input.getSubimage(0, 0, input.getWidth(), input.getHeight()); //fill in the corners of the desired crop location here
//        BufferedImage cropped = input.getSubimage(0, 0, 4110, 5762); //fill in the corners of the desired crop location here

        BufferedImage copyOfImage = new BufferedImage(cropped.getWidth(), cropped.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(cropped, 0, 0, null);
        g.dispose();

        return copyOfImage; //or use it however you want
    }

    public static BufferedImage toBinary(BufferedImage input) {

        BufferedImage binary = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        Graphics g = binary.getGraphics();
        g.drawImage(input, 0, 0, null);
        g.dispose();

        return binary;
    }

    public static BufferedImage rotate(BufferedImage input, double angle) {

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

    public static BufferedImage removeEdge(BufferedImage input, int width) {

        Graphics2D g = input.createGraphics();

        g.setPaint(new Color(255, 255, 255));

        g.fillRect(0, 0, input.getWidth(), width);
        g.fillRect(0, input.getHeight() - width, input.getWidth(), width);
        g.fillRect(0, 0, width, input.getHeight());
        g.fillRect(input.getWidth() - width, 0, input.getWidth(), input.getHeight());

        g.dispose();

        return input;
    }


    public static BufferedImage deskew(BufferedImage input) {

        double skewRadians = findSkew(toBinary(input));
        System.out.println(Math.toDegrees(skewRadians));

        return rotate(input, Math.toDegrees(skewRadians));
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
    }

    static int getByteWidth(final int width) {
        return (width + 7) / 8;
    }

    static int next_pow2(final int n) {
        int retval = 1;
        while (retval < n) {
            retval <<= 1;
        }
        return retval;
    }

    static double findSkew(final BufferedImage img) {
        final DataBuffer buffer = img.getRaster().getDataBuffer();
        final int byteWidth = getByteWidth(img.getWidth());
        final int padmask = 0xFF << ((img.getWidth() + 7) % 8);
        int elementIndex = 0;
        for (int row = 0; row < img.getHeight(); row++) {
            for (int col = 0; col < byteWidth; col++) {
                int elem = buffer.getElem(elementIndex);
                elem ^= 0xff;// invert colors
                elem = BitUtils.invbits_[elem]; // Change the bit order
                buffer.setElem(elementIndex, elem);
                elementIndex++;
            }
            final int lastElement = buffer.getElem(elementIndex - 1) & padmask;
            buffer.setElem(elementIndex - 1, lastElement); // Zero trailing bits
        }
        final int w2 = next_pow2(byteWidth);
        final int ssize = 2 * w2 - 1; // Size of sharpness table
        final int sharpness[] = new int[ssize];
        radon(img.getWidth(), img.getHeight(), buffer, 1, sharpness);
        radon(img.getWidth(), img.getHeight(), buffer, -1, sharpness);
        int i, imax = 0;
        int vmax = 0;
        double sum = 0.;
        for (i = 0; i < ssize; i++) {
            final int s = sharpness[i];
            if (s > vmax) {
                imax = i;
                vmax = s;
            }
            sum += s;
        }
        final int h = img.getHeight();
        if (vmax <= 3 * sum / h) { // Heuristics !!!
            return 0;
        }
        final double iskew = imax - w2 + 1;
        return Math.atan(iskew / (8 * w2));
    }

    static void radon(final int width, final int height, final DataBuffer buffer, final int sign,
                      final int sharpness[]) {

        int[] p1_, p2_; // Stored columnwise

        final int w2 = next_pow2(getByteWidth(width));
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
}
