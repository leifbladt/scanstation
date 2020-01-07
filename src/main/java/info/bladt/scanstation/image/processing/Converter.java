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

    public void process(Composition composition, Instrument instrument) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition, instrument);
            for (TiffReader.Page inputImage : inputImages) {
                BufferedImage bufferedImage = ImageIO.read(inputImage.getPath().toFile());

                bufferedImage = convert.process(bufferedImage, ImageType.GRAY);
                bufferedImage = crop.process(bufferedImage, new Rectangle(4250, 3140));
                bufferedImage = rotate.process(bufferedImage, 0);
                bufferedImage = deskew.process(bufferedImage);
                bufferedImage = removeEdges.process(bufferedImage, 70);
//                bufferedImage = convert.process(bufferedImage, ImageType.BINARY);

                TiffWriter.saveImage(bufferedImage, "Work", inputImage.getNumber(), composition, instrument);
            }
        } catch (IOException e) {
            LOGGER.error("Error creating PDF file", e);
        }
    }
}
