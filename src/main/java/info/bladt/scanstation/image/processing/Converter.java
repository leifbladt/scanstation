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

    public void process(Composition composition, Instrument instrument, ProcessingConfiguration configuration) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition, instrument);
            for (TiffReader.Page page : inputImages) {
                BufferedImage bufferedImage = ImageIO.read(page.getPath().toFile());

                bufferedImage = convert.process(bufferedImage, ImageType.GRAY);

                if (configuration.isCrop(instrument)) {
                    bufferedImage = crop.process(bufferedImage, new Rectangle(configuration.getPageHeight(instrument), configuration.getPageWidth(instrument)));
                }

                if (configuration.isRotate(instrument)) {
                    bufferedImage = rotate.process(bufferedImage, configuration.getRotationAngle(instrument));
                }

                if (configuration.isDeskew(instrument)) {
                    bufferedImage = deskew.process(bufferedImage);
                }

                if (configuration.isRemoveEdges(instrument)) {
                    bufferedImage = removeEdges.process(bufferedImage, configuration.getPaperEdgeWidth(instrument));
                }

                TiffWriter.saveImage(bufferedImage, "Work", page.getNumber(), composition, instrument);
            }
        } catch (IOException e) {
            LOGGER.error("Error creating TIFF file", e);
        }
    }

    public static class TestConfiguration extends ProcessingConfiguration {
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
