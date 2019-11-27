package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.image.scan.demo.DemoScanner;
import info.bladt.scanstation.image.scan.sane.SaneScanner;

public class ScannerFactory {

    private final static boolean USE_DEMO = true;

    // TODO Synchronized
    public static Scanner getScanner(String scannerName) {
        if (scannerName.equals("Demo")) {
            return new DemoScanner();
        } else {
            return new SaneScanner();
        }
    }

    public static Scanner getScanner() {
        if (USE_DEMO) {
            return getScanner("Demo");
        } else {
            return getScanner("Canon LiDE 210");
        }
    }
}
