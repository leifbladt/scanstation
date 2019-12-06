package info.bladt.scanstation.image.export;

import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode.APPEND;

public class PdfExporter {

    private static final Logger LOGGER = LogManager.getLogger(PdfExporter.class);

    public void savePdf(Composition composition, Instrument instrument) {

        try (PDDocument doc = new PDDocument()) {

            Path outputPath = Path.of("ScanStation", "Export", composition.getName());
            Files.createDirectories(outputPath);
            Path outputPath2 = Path.of(outputPath.toString(), instrument.getName() + ".pdf");

            List<Path> inputImages = getInputImages(composition, instrument);
            for (Path inputImage : inputImages) {
                PDPage page = new PDPage(PDRectangle.A4);

                BufferedImage bufferedImage = ImageIO.read(inputImage.toFile());
                PDImageXObject image = LosslessFactory.createFromImage(doc, bufferedImage);

                try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, APPEND, true, true)) {
                    PDRectangle mediaBox = page.getMediaBox();

                    float scale = calculateScale(mediaBox, image);
                    float newWidth = image.getWidth() * scale;
                    float newHeight = image.getHeight() * scale;
                    float offsetX = (mediaBox.getWidth() - newWidth) / 2;
                    float offsetY = (mediaBox.getHeight() - newHeight) / 2;
                    contentStream.drawImage(image, offsetX, offsetY, newWidth, newHeight);
                }

                doc.addPage(page);
            }

            doc.save(outputPath2.toFile());
        } catch (IOException e) {
            LOGGER.error("Error creating PDF file", e);
        }
    }

    private List<Path> getInputImages(Composition composition, Instrument instrument) throws IOException {
        Path inputPath = Path.of("ScanStation", "Scan", composition.getName());

        try (Stream<Path> pathStream = Files.find(inputPath, 1,
                (path, basicFileAttributes) -> path.toFile().getName().matches(instrument.getName() + " [0-9][0-9].tif"))) {

            return pathStream.collect(Collectors.toList());
        }
    }

    private float calculateScale(PDRectangle mediaBox, PDImageXObject image) {
        float scale = Math.min(mediaBox.getWidth() / image.getWidth(), mediaBox.getHeight() / image.getHeight());
        return Math.min(scale, 1f); // Don't upscale image
    }
}
