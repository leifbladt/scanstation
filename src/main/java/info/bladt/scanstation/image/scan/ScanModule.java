package info.bladt.scanstation.image.scan;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScanModule {

    private int page = 0;

    private Scanner scanner;

    private BufferedImage image;

    public ScanModule(Scanner scanner) {
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
            if (!ImageIO.write(image, "TIFF", new File(String.format("scan_%02d.tif", page)))) {
                System.out.println("Error while writing file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
