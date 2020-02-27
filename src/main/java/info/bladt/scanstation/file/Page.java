package info.bladt.scanstation.file;

import info.bladt.scanstation.model.Instrument;

import java.nio.file.Path;

public class Page {
    private final Instrument instrument;
    private final int number;
    private final Path path;

    public Page(Instrument instrument, int number, Path path) {
        this.instrument = instrument;
        this.number = number;
        this.path = path;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public int getNumber() {
        return number;
    }

    public Path getPath() {
        return path;
    }
}
