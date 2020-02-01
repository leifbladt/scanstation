package scanstation.info.bladt.scanstation.image.scan;

import scanstation.info.bladt.scanstation.image.file.TiffReader;
import scanstation.info.bladt.scanstation.model.Composition;
import scanstation.info.bladt.scanstation.model.Instrument;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ScanResults {

    public List<Instrument> getInstruments(Composition composition) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition);
            return inputImages.stream()
                    .map(TiffReader.Page::getInstrument)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
