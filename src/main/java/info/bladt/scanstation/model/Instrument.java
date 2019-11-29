package info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Instrument {

    public static final List<Instrument> INSTRUMENTS = Collections.unmodifiableList(Arrays.asList(
            new Instrument("Soprano Cornet"), new Instrument("Solo Cornet")
    ));

    private final String name;

    public Instrument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
