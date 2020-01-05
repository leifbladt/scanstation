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
            new Instrument("1. Tenorhorn", B_FLAT),
            new Instrument("2. Tenorhorn", B_FLAT),
            new Instrument("3. Tenorhorn", B_FLAT),
            new Instrument("Bariton", C),
            new Instrument("Bariton", B_FLAT),
            new Instrument("1. Posaune", C),
            new Instrument("2. Posaune", C),
            new Instrument("3. Posaune", C),
            new Instrument("1. Posaune", B_FLAT),
            new Instrument("2. Posaune", B_FLAT),
            new Instrument("3. Posaune", B_FLAT),
            new Instrument("1. Bass", C),
            new Instrument("Bass", E_FLAT),
            new Instrument("2. Bass", C),
            new Instrument("Bass", B_FLAT),
            new Instrument("Schlagzeug"),
            new Instrument("Direktion", C),

            new Instrument("Soprano Cornet", E_FLAT),
            new Instrument("Solo Cornet", B_FLAT),
            new Instrument("Repiano Cornet", B_FLAT),
            new Instrument("1st Cornet", B_FLAT),
            new Instrument("2nd Cornet", B_FLAT),
            new Instrument("3rd Cornet", B_FLAT),
            new Instrument("Flugel Horn", B_FLAT),
            new Instrument("Solo Horn", E_FLAT),
            new Instrument("1st Horn", E_FLAT),
            new Instrument("2nd Horn", E_FLAT),
            new Instrument("1st Baritone", B_FLAT),
            new Instrument("2nd Baritone", B_FLAT),
            new Instrument("1st Trombone", B_FLAT),
            new Instrument("1st Trombone", C),
            new Instrument("2nd Trombone", B_FLAT),
            new Instrument("2nd Trombone", C),
            new Instrument("Bass Trombone", B_FLAT),
            new Instrument("Bass Trombone", C),
            new Instrument("Euphonium", B_FLAT),
            new Instrument("1st Euphonium", B_FLAT),
            new Instrument("2nd Euphonium", B_FLAT),
            new Instrument("Eb Bass", E_FLAT),
            new Instrument("Bb Bass", B_FLAT),
            new Instrument("Drums"),
            new Instrument("Timpani"),
            new Instrument("Percussion"),
            new Instrument("Percussion 1"),
            new Instrument("Percussion 2"),
            new Instrument("Mallets"),
            new Instrument("Piano Accompaniment"),
            new Instrument("Soloist", B_FLAT),
            new Instrument("Score")
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
