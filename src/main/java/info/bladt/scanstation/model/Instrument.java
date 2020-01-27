package info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static info.bladt.scanstation.model.Key.*;

public class Instrument implements Comparable<Instrument> {

    public static final List<Instrument> INSTRUMENTS = Collections.unmodifiableList(Arrays.asList(
            new Instrument("Melodie", C),
            new Instrument("Flute"),
            new Instrument("Flute 1"),
            new Instrument("Flute 2"),
            new Instrument("Flute 2 - Piccolo"),
            new Instrument("Flute Piccolo"),
            new Instrument("Oboe"),
            new Instrument("Oboe 1"),
            new Instrument("Oboe 2"),
            new Instrument("Bassoon"),
            new Instrument("Bassoon 1"),
            new Instrument("Bassoon 2"),
            new Instrument("Clarinet", E_FLAT),
            new Instrument("Clarinet 1", B_FLAT),
            new Instrument("Clarinet 2", B_FLAT),
            new Instrument("Clarinet 3", B_FLAT),
            new Instrument("Clarinet Bass", B_FLAT),
            new Instrument("Saxophone Alto 1", E_FLAT),
            new Instrument("Saxophone Alto 2", E_FLAT),
            new Instrument("Saxophone Tenor", B_FLAT),
            new Instrument("Saxophone Tenor 1", B_FLAT),
            new Instrument("Saxophone Tenor 2", B_FLAT),
            new Instrument("Saxophone Baritone", E_FLAT),
            new Instrument("Flugel Horn 1", B_FLAT),
            new Instrument("Flugel Horn 2", B_FLAT),
            new Instrument("Trumpet 1", B_FLAT),
            new Instrument("Trumpet 2", B_FLAT),
            new Instrument("Trumpet 3", B_FLAT),
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
            new Instrument("Baritone", C),
            new Instrument("Baritone", B_FLAT),
            new Instrument("Euphonium", C),
            new Instrument("Euphonium", B_FLAT),
            new Instrument("Trombone 1", B_FLAT),
            new Instrument("Trombone 1", C),
            new Instrument("Trombone 2", B_FLAT),
            new Instrument("Trombone 2", C),
            new Instrument("Trombone 3", B_FLAT),
            new Instrument("Trombone 3", C),
            new Instrument("Trombone 4", B_FLAT),
            new Instrument("Trombone 4", C),
            new Instrument("Bass", C),
            new Instrument("Bass 1", C),
            new Instrument("Bass 1", E_FLAT),
            new Instrument("Bass 2", C),
            new Instrument("Bass 2", B_FLAT),
            new Instrument("Bass String"),
            new Instrument("Percussion"),
            new Instrument("Percussion Timpani"),
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
            new Instrument("1st Bass", E_FLAT),
            new Instrument("1st Bass", C),
            new Instrument("2nd Bass", B_FLAT),
            new Instrument("2nd Bass", C),
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
    public String toString() {
        if (getKey() == null) {
            return name;
        } else {
            return String.format("%s (%s)", getName(), getKey().toString());
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
    public int compareTo(Instrument o) {
        return getFilenamePart().compareTo(o.getFilenamePart());
    }
}
