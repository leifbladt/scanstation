package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.image.file.TiffWriter;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.BufferedImage;

public class ScanModule {

    private static final Logger LOGGER = LogManager.getLogger(ScanModule.class);

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
        TiffWriter.saveImage(image, "Scan", page, composition, instrument);
        scanPage(true);
    }

    public void finish() {
        TiffWriter.saveImage(image, "Scan", page, composition, instrument);
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
}
