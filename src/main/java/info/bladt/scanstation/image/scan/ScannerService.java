package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.image.scan.demo.DemoScanner;
import info.bladt.scanstation.image.scan.sane.SaneScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScannerService {

    // TODO Refactor class to include all scanner implementation

    @Autowired
    private DemoScanner demoScanner;

    @Autowired
    private SaneScanner saneScanner;

    public Scanner getScanner(String scannerName) {
        if (scannerName.equals("Demo")) {
            return demoScanner;
        } else {
            return saneScanner;
        }
    }
}
