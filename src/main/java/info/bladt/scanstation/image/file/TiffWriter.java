package info.bladt.scanstation.image.file;

import info.bladt.scanstation.image.scan.ScanModule;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;

import static javax.imageio.ImageWriteParam.MODE_EXPLICIT;

public class TiffWriter {

    private static final Logger LOGGER = LogManager.getLogger(TiffWriter.class);

    public static void saveImage(BufferedImage image, String folder, int page, Composition composition, Instrument instrument) {
        try {
            Path path = Path.of("ScanStation", folder, composition.getName());
            Files.createDirectories(path);

            Path path2 = Path.of(path.toString(), String.format("%s %02d.tif", instrument.getFilenamePart(), page));

            ImageWriter tiffWriter = ImageIO.getImageWritersByFormatName("tiff").next();
            ImageWriteParam writeParam = tiffWriter.getDefaultWriteParam();
            writeParam.setCompressionMode(MODE_EXPLICIT);
            writeParam.setCompressionType("LZW");

            tiffWriter.setOutput(ImageIO.createImageOutputStream(path2.toFile()));

            tiffWriter.write(null, new IIOImage(image, null, null), writeParam);
            tiffWriter.dispose();
        } catch (Exception e) {
            LOGGER.error("Could not write file", e);
        }
    }
}