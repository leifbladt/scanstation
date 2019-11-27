package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.image.scan.demo.DemoScanner;
import info.bladt.scanstation.image.scan.sane.SaneScanner;

public class ScannerFactory {

    private static Scanner scanner;

    private final static boolean USE_DEMO = true;

    // TODO Synchronized
    public static Scanner getScanner() {

        if (scanner == null) {
            if (USE_DEMO) {
                scanner = new DemoScanner();
            } else {
                scanner = new SaneScanner();
            }
        }

        return scanner;
    }
}
