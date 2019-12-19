package info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static info.bladt.scanstation.model.Key.*;

public class Instrument {

    public static final List<Instrument> INSTRUMENTS = Collections.unmodifiableList(Arrays.asList(
            new Instrument("Melodie", C),
            new Instrument("Flöte", C),
            new Instrument("Klarinette", E_FLAT),
            new Instrument("1. Klarinette", B_FLAT),
            new Instrument("2. Klarinette", B_FLAT),
            new Instrument("3. Klarinette", B_FLAT),
            new Instrument("1. Alt-Saxophon", E_FLAT),
            new Instrument("2. Alt-Saxophon", E_FLAT),
            new Instrument("1. Tenor-Saxophon", B_FLAT),
            new Instrument("2. Tenor-Saxophon", B_FLAT),
            new Instrument("Bariton-Saxophon", E_FLAT),
            new Instrument("1. Flügelhorn", B_FLAT),
            new Instrument("2. Flügelhorn", B_FLAT),
            new Instrument("1. Trompete", B_FLAT),
            new Instrument("2. Trompete", B_FLAT),
            new Instrument("3. Trompete", B_FLAT),
            new Instrument("1. Horn", E_FLAT),
            new Instrument("2. Horn", E_FLAT),
            new Instrument("3. Horn", E_FLAT),
            new Instrument("4. Horn", E_FLAT),

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

    private final Key key;

    public Instrument(String name) {
        this(name, null);
    }

    public Instrument(String name, Key key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public Key getKey() {
        return key;
    }

    public String getFilenamePart() {
        if (getKey() == null) {
            return getName();
        } else {
            return String.format("%s_%s", getName(), getKey().toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return Objects.equals(name, that.name) &&
                key == that.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key);
    }

    @Override
    public String toString() {
        if (getKey() == null) {
            return name;
        } else {
            return String.format("%s (%s)", getName(), getKey().toString());
        }
    }
}
