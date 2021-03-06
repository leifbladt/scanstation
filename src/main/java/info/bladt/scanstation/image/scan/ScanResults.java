package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.file.Page;
import info.bladt.scanstation.file.TiffReader;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ScanResults {

    public List<Instrument> getInstruments(Composition composition) {

        try {
            List<Page> inputImages = TiffReader.getInputImages("Scan", composition);
            return inputImages.stream()
                    .map(Page::getInstrument)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
