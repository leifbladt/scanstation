package info.bladt.scanstation.image.export;

import info.bladt.scanstation.image.file.TiffReader;
import info.bladt.scanstation.image.processing.Convert;
import info.bladt.scanstation.image.processing.Convert.ImageType;
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
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;
import static org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode.APPEND;

@Service
public class ExportService {

    private static final Logger LOGGER = LogManager.getLogger(ExportService.class);

    private final Convert convert = new Convert();

    public void savePdf(Composition composition, Instrument instrument, ExportConfiguration configuration) {

        try (PDDocument doc = new PDDocument()) {

            Path outputPath = Path.of(getScanStationDirectory(), composition.getName(), "Export");
            Files.createDirectories(outputPath);
            Path outputPath2 = Path.of(outputPath.toString(), instrument.getFilenamePart() + ".pdf");

            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Work", composition, instrument);
            for (TiffReader.Page inputImage : inputImages) {
                PDPage page = new PDPage(convert(configuration.getPageSize(instrument), configuration.getPageOrientation(instrument)));

                BufferedImage bufferedImage = ImageIO.read(inputImage.getPath().toFile());
                BufferedImage binaryImage = convert.process(bufferedImage, ImageType.BINARY);
                PDImageXObject image = LosslessFactory.createFromImage(doc, binaryImage);

                try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, APPEND, true, true)) {
                    PDRectangle mediaBox = page.getMediaBox();

                    float scale;

                    if (configuration.isScaleToFit(instrument)) {
                        scale = calculateScale(mediaBox, image);
                    } else {
                        scale = configuration.getScale(instrument);
                    }
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

    private PDRectangle convert(PageSize pageSize, PageOrientation pageOrientation) {

        PDRectangle rectangle;

        switch (pageSize) {
            case DIN_A5:
                rectangle = PDRectangle.A5;
                break;
            case DIN_A4:
            default:
                rectangle = PDRectangle.A4;
        }

        if (pageOrientation == PageOrientation.LANDSCAPE) {
            rectangle = new PDRectangle(rectangle.getHeight(), rectangle.getWidth());
        }

        return rectangle;
    }

    private float calculateScale(PDRectangle mediaBox, PDImageXObject image) {
        float scale = Math.min(mediaBox.getWidth() / image.getWidth(), mediaBox.getHeight() / image.getHeight());
        return Math.min(scale, 1f); // Don't upscale image
    }
}
