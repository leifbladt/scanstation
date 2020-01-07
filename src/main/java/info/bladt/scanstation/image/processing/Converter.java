package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.image.file.TiffReader;
import info.bladt.scanstation.image.file.TiffWriter;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Converter {

    private static final Logger LOGGER = LogManager.getLogger(Converter.class);

    private final BinaryStep binaryStep;
    private final GrayscaleStep grayscaleStep;
    private final CropStep cropStep;
    private final DeskewStep deskewStep;
    private final RemoveEdgeStep removeEdgeStep;
    private final RotateStep rotateStep;

    public Converter() {
        binaryStep = new BinaryStep();
        grayscaleStep = new GrayscaleStep();
        cropStep = new CropStep();
        deskewStep = new DeskewStep();
        removeEdgeStep = new RemoveEdgeStep();
        rotateStep = new RotateStep();
    }

    public void process(Composition composition, Instrument instrument) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition, instrument);
            for (TiffReader.Page inputImage : inputImages) {
                BufferedImage bufferedImage = ImageIO.read(inputImage.getPath().toFile());

                bufferedImage = grayscaleStep.process(bufferedImage, null);
                bufferedImage = cropStep.process(bufferedImage, null);
//                bufferedImage = rotateStep.process(bufferedImage, new RotateStep.Configuration(180));
                bufferedImage = deskewStep.process(bufferedImage, null);
                bufferedImage = removeEdgeStep.process(bufferedImage, new RemoveEdgeStep.Configuration(70));
//                bufferedImage = binaryStep.process(bufferedImage, null);

                TiffWriter.saveImage(bufferedImage, "Work", inputImage.getNumber(), composition, instrument);
            }
        } catch (IOException e) {
            LOGGER.error("Error creating PDF file", e);
        }
    }
}
