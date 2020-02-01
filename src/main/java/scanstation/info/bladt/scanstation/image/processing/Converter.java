package scanstation.info.bladt.scanstation.image.processing;

import scanstation.info.bladt.scanstation.image.file.TiffReader;
import scanstation.info.bladt.scanstation.image.file.TiffWriter;
import scanstation.info.bladt.scanstation.model.Composition;
import scanstation.info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Converter {

    private static final Logger LOGGER = LogManager.getLogger(Converter.class);

    private final Convert convert;
    private final Crop crop;
    private final Deskew deskew;
    private final RemoveEdges removeEdges;
    private final Rotate rotate;
    private final Contrast contrast;

    public Converter() {
        convert = new Convert();
        crop = new Crop();
        deskew = new Deskew();
        removeEdges = new RemoveEdges();
        rotate = new Rotate();
        contrast = new Contrast();
    }

    public void process(Composition composition, Instrument instrument, ProcessingConfiguration configuration) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition, instrument);
            for (TiffReader.Page page : inputImages) {
                int pageNumber = page.getNumber();
                BufferedImage bufferedImage = ImageIO.read(page.getPath().toFile());

                bufferedImage = convert.process(bufferedImage, Convert.ImageType.GRAY);

                if (configuration.isCrop(instrument, pageNumber)) {
                    bufferedImage = crop.process(bufferedImage, new Crop.Rectangle(
                            configuration.getImageX(instrument, pageNumber),
                            configuration.getImageY(instrument, pageNumber),
                            configuration.getImageHeight(instrument, pageNumber),
                            configuration.getImageWidth(instrument, pageNumber)));
                }

                if (configuration.isRemoveEdges(instrument, pageNumber)) {
                    bufferedImage = removeEdges.process(bufferedImage, configuration.getPaperEdgeWidth(instrument, pageNumber));
                }

                if (configuration.isRotate(instrument, pageNumber)) {
                    bufferedImage = rotate.process(bufferedImage, configuration.getRotationAngle(instrument, pageNumber), false);
                }

                if (configuration.isAdjustContrast(instrument, pageNumber)) {
                    bufferedImage = contrast.process(bufferedImage);
                }

                if (configuration.isDeskew(instrument, pageNumber)) {
                    bufferedImage = deskew.process(bufferedImage);
                }

                TiffWriter.saveImage(bufferedImage, "Work", pageNumber, composition, instrument);
            }
        } catch (IOException e) {
            LOGGER.error("Error creating TIFF file", e);
        }
    }
}
