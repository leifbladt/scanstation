package info.bladt.scanstation.image.scan;

import info.bladt.scanstation.image.file.TiffReader;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScanResults {

    public Set<Instrument> getInstruments(Composition composition) {

        try {
            List<TiffReader.Page> inputImages = TiffReader.getInputImages("Scan", composition);
            return inputImages.stream().map(i -> i.getInstrument()).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
