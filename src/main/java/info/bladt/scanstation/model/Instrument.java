package info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Instrument {

    public static final List<Instrument> INSTRUMENTS = Collections.unmodifiableList(Arrays.asList(
            new Instrument("Soprano Cornet"),
            new Instrument("Solo Cornet"),
            new Instrument("Repiano Cornet"),
            new Instrument("2nd Cornet"),
            new Instrument("3rd Cornet"),
            new Instrument("Flugel Horn"),
            new Instrument("Solo Tenor Horn"),
            new Instrument("1st Tenor Horn"),
            new Instrument("2nd Tenor Horn"),
            new Instrument("1st Baritone"),
            new Instrument("2nd Baritone"),
            new Instrument("1st Trombone"),
            new Instrument("2nd Trombone"),
            new Instrument("Bass Trombone"),
            new Instrument("Euphonium"),
            new Instrument("Eb Tuba"),
            new Instrument("Bb Tuba"),
            new Instrument("Drums"),
            new Instrument("Timpani"),
            new Instrument("Mallets")
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
