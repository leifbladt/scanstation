package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.image.file.TiffReader;
import info.bladt.scanstation.image.file.TiffWriter;
import info.bladt.scanstation.image.processing.Crop.Rectangle;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static info.bladt.scanstation.image.processing.Convert.ImageType;

public class Converter {

    private static final Logger LOGGER = LogManager.getLogger(Converter.class);

    private final Convert convert;
    private final Crop crop;
    private final Deskew deskew;
    private final RemoveEdges removeEdges;
    private final Rotate rotate;

    public Converter() {
        convert = new Convert();
        crop = new Crop();
        deskew = new Deskew();
        removeEdges = new RemoveEdges();
        rotate = new Rotate();
    }

    public void process(Composition composition, Instrument instrument, Configuration configuration) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition, instrument);
            for (TiffReader.Page inputImage : inputImages) {
                BufferedImage bufferedImage = ImageIO.read(inputImage.getPath().toFile());

                bufferedImage = convert.process(bufferedImage, ImageType.GRAY);

                if (configuration.isCrop()) {
                    bufferedImage = crop.process(bufferedImage, new Rectangle(configuration.getPageHeight(), configuration.getPageWidth()));
                }

                if (configuration.isRotate()) {
                    bufferedImage = rotate.process(bufferedImage, configuration.getRotationAngle());
                }

                if (configuration.isDeskew()) {
                    bufferedImage = deskew.process(bufferedImage);
                }

                if (configuration.isRemoveEdges()) {
                    bufferedImage = removeEdges.process(bufferedImage, configuration.getPaperEdgeWidth());
                }

                TiffWriter.saveImage(bufferedImage, "Work", inputImage.getNumber(), composition, instrument);
            }
        } catch (IOException e) {
            LOGGER.error("Error creating TIFF file", e);
        }
    }

    public static class Configuration {
        private boolean crop;
        private boolean rotate;
        private boolean deskew;
        private boolean removeEdges;

        private int pageWidth;
        private int pageHeight;

        private double rotationAngle;

        private int paperEdgeWidth;

        public boolean isCrop() {
            return crop;
        }

        public void setCrop(boolean crop) {
            this.crop = crop;
        }

        public boolean isRotate() {
            return rotate;
        }

        public void setRotate(boolean rotate) {
            this.rotate = rotate;
        }

        public boolean isDeskew() {
            return deskew;
        }

        public void setDeskew(boolean deskew) {
            this.deskew = deskew;
        }

        public boolean isRemoveEdges() {
            return removeEdges;
        }

        public void setRemoveEdges(boolean removeEdges) {
            this.removeEdges = removeEdges;
        }

        public int getPageWidth() {
            return pageWidth;
        }

        public void setPageWidth(int pageWidth) {
            this.pageWidth = pageWidth;
        }

        public int getPageHeight() {
            return pageHeight;
        }

        public void setPageHeight(int pageHeight) {
            this.pageHeight = pageHeight;
        }

        public double getRotationAngle() {
            return rotationAngle;
        }

        public void setRotationAngle(double rotationAngle) {
            this.rotationAngle = rotationAngle;
        }

        public int getPaperEdgeWidth() {
            return paperEdgeWidth;
        }

        public void setPaperEdgeWidth(int paperEdgeWidth) {
            this.paperEdgeWidth = paperEdgeWidth;
        }
    }

    public static class TestConfiguration extends Configuration {
        public TestConfiguration() {
            setCrop(true);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);

            setPageHeight(4250);
            setPageWidth(3140);
            setPaperEdgeWidth(70);
        }
    }
}
