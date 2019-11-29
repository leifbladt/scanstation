package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScanModule {

    private int page = 0;

    private Composition composition;
    private Instrument instrument;
    private Scanner scanner;

    private BufferedImage image;

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void scanPage() {
        scanPage(true);
    }

    public void scanPageAgain() {
        scanPage(false);
    }

    public void scanNextPage() {
        saveImage();
        scanPage(true);
    }

    public void finish() {
        saveImage();
    }

    public void reset() {
        page = 0;
    }

    public int getPage() {
        return page;
    }

    public BufferedImage getPreview() {
        return image;
    }

    private void scanPage(boolean nextPage) {
        image = scanner.acquireImage();

        if (nextPage) {
            page++;
        }
    }

    private void saveImage() {
        try {
            Path path = Path.of("ScanStation", "Scan", composition.getName());
            Files.createDirectories(path);

            Path path2 = Path.of(path.toString(), String.format("%s_%02d.tif", instrument.getName(), page));
            if (!ImageIO.write(image, "TIFF", path2.toFile())) {
                System.out.println("Error while writing file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
