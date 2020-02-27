package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.file.TiffReader;
import info.bladt.scanstation.file.TiffWriter;
import info.bladt.scanstation.image.processing.Crop.Rectangle;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static info.bladt.scanstation.image.processing.Convert.ImageType;

@Service
public class ProcessingService {

    private static final Logger LOGGER = LogManager.getLogger(ProcessingService.class);

    private final Convert convert;
    private final Crop crop;
    private final Deskew deskew;
    private final RemoveEdges removeEdges;
    private final Rotate rotate;
    private final Contrast contrast;

    public ProcessingService() {
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

                bufferedImage = convert.process(bufferedImage, ImageType.GRAY);

                if (configuration.isCrop(instrument, pageNumber)) {
                    bufferedImage = crop.process(bufferedImage, new Rectangle(
                            configuration.getImageX(instrument, pageNumber),
                            configuration.getImageY(instrument, pageNumber),
                            configuration.getImageHeight(instrument, pageNumber),
                            configuration.getImageWidth(instrument, pageNumber)));
                }

                if (configuration.isRemoveEdges(instrument, pageNumber)) {
                    bufferedImage = removeEdges.process(bufferedImage, new RemoveEdges.Width(
                            configuration.getPaperEdgeWidthLeft(instrument, pageNumber),
                            configuration.getPaperEdgeWidthTop(instrument, pageNumber),
                            configuration.getPaperEdgeWidthRight(instrument, pageNumber),
                            configuration.getPaperEdgeWidthBottom(instrument, pageNumber)
                    ));
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
