package info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static info.bladt.scanstation.model.Key.*;

public class Instrument {

    public static final List<Instrument> INSTRUMENTS = Collections.unmodifiableList(Arrays.asList(
            new Instrument("Melodie", C),
            new Instrument("Flöte", C),
            new Instrument("Flöte 1", C),
            new Instrument("Flöte 2", C),
            new Instrument("Flöte 2 - Piccolo", C),
            new Instrument("Oboe 1", C),
            new Instrument("Oboe 2", C),
            new Instrument("Fagott 1", C),
            new Instrument("Fagott 2", C),
            new Instrument("Klarinette", E_FLAT),
            new Instrument("Klarinette 1", B_FLAT),
            new Instrument("Klarinette 2", B_FLAT),
            new Instrument("Klarinette 3", B_FLAT),
            new Instrument("Bass-Klarinette", B_FLAT),
            new Instrument("Alt-Saxophon 1", E_FLAT),
            new Instrument("Alt-Saxophon 2", E_FLAT),
            new Instrument("Tenor-Saxophon", B_FLAT),
            new Instrument("Tenor-Saxophon 1", B_FLAT),
            new Instrument("Tenor-Saxophon 2", B_FLAT),
            new Instrument("Bariton-Saxophon", E_FLAT),
            new Instrument("Flügelhorn 1", B_FLAT),
            new Instrument("Flügelhorn 2", B_FLAT),
            new Instrument("Trompete 1", B_FLAT),
            new Instrument("Trompete 2", B_FLAT),
            new Instrument("Trompete 3", B_FLAT),
            new Instrument("Horn 1", F),
            new Instrument("Horn 1", E_FLAT),
            new Instrument("Horn 2", F),
            new Instrument("Horn 2", E_FLAT),
            new Instrument("Horn 3", F),
            new Instrument("Horn 3", E_FLAT),
            new Instrument("Horn 4", F),
            new Instrument("Horn 4", E_FLAT),
            new Instrument("1. Tenorhorn", B_FLAT),
            new Instrument("2. Tenorhorn", B_FLAT),
            new Instrument("3. Tenorhorn", B_FLAT),
            new Instrument("Bariton", C),
            new Instrument("Bariton", B_FLAT),
            new Instrument("Euphonium", C),
            new Instrument("Euphonium", B_FLAT),
            new Instrument("Posaune 1", B_FLAT),
            new Instrument("Posaune 1", C),
            new Instrument("Posaune 2", B_FLAT),
            new Instrument("Posaune 2", C),
            new Instrument("Posaune 3", B_FLAT),
            new Instrument("Posaune 3", C),
            new Instrument("Tuba 1", C),
            new Instrument("Tuba 1", E_FLAT),
            new Instrument("Tuba 2", C),
            new Instrument("Tuba 2", B_FLAT),
            new Instrument("Schlagzeug"),
            new Instrument("Pauke - Glockenspiel"),
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
            new Instrument("Eb Bass", C),
            new Instrument("Bb Bass", B_FLAT),
            new Instrument("Bb Bass", C),
            new Instrument("Drums"),
            new Instrument("Timpani"),
            new Instrument("Percussion"),
            new Instrument("Percussion 1"),
            new Instrument("Percussion 2"),
            new Instrument("Percussion 3"),
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

    public static Instrument parse(String instrument) {

        if (instrument == null || instrument.isBlank()) {
            return null;
        }

        Pattern pattern = Pattern.compile("(.*)_(.*)");
        Matcher matcher = pattern.matcher(instrument);
        if (matcher.find()) {
            return new Instrument(matcher.group(1), Key.parse(matcher.group(2)));
        } else {
            return new Instrument(instrument);
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
