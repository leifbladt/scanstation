package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.image.scan.demo.DemoScanner;
import info.bladt.scanstation.image.scan.sane.SaneScanner;

public class ScannerFactory {

    private static final boolean USE_DEMO = true;

    private ScannerFactory() {
    }

    public static synchronized Scanner getScanner(String scannerName) {
        if (scannerName.equals("Demo")) {
            return new DemoScanner();
        } else {
            return new SaneScanner();
        }
    }
}
