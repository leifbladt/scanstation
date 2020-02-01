package scanstation.info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instrument implements Comparable<Instrument> {

    public static final List<Instrument> INSTRUMENTS = Collections.unmodifiableList(Arrays.asList(
            new Instrument("Melodie", Key.C),
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
            new Instrument("Clarinet", Key.E_FLAT),
            new Instrument("Clarinet 1", Key.B_FLAT),
            new Instrument("Clarinet 2", Key.B_FLAT),
            new Instrument("Clarinet 3", Key.B_FLAT),
            new Instrument("Clarinet Bass", Key.B_FLAT),
            new Instrument("Saxophone Alto 1", Key.E_FLAT),
            new Instrument("Saxophone Alto 2", Key.E_FLAT),
            new Instrument("Saxophone Tenor", Key.B_FLAT),
            new Instrument("Saxophone Tenor 1", Key.B_FLAT),
            new Instrument("Saxophone Tenor 2", Key.B_FLAT),
            new Instrument("Saxophone Baritone", Key.E_FLAT),
            new Instrument("Flugel Horn 1", Key.B_FLAT),
            new Instrument("Flugel Horn 2", Key.B_FLAT),
            new Instrument("Trumpet 1", Key.B_FLAT),
            new Instrument("Trumpet 2", Key.B_FLAT),
            new Instrument("Trumpet 3", Key.B_FLAT),
            new Instrument("Horn 1", Key.F),
            new Instrument("Horn 1", Key.E_FLAT),
            new Instrument("Horn 2", Key.F),
            new Instrument("Horn 2", Key.E_FLAT),
            new Instrument("Horn 3", Key.F),
            new Instrument("Horn 3", Key.E_FLAT),
            new Instrument("Horn 4", Key.F),
            new Instrument("Horn 4", Key.E_FLAT),
            new Instrument("1. Tenorhorn", Key.B_FLAT),
            new Instrument("2. Tenorhorn", Key.B_FLAT),
            new Instrument("3. Tenorhorn", Key.B_FLAT),
            new Instrument("Baritone", Key.C),
            new Instrument("Baritone", Key.B_FLAT),
            new Instrument("Euphonium", Key.C),
            new Instrument("Euphonium", Key.B_FLAT),
            new Instrument("Trombone 1", Key.B_FLAT),
            new Instrument("Trombone 1", Key.C),
            new Instrument("Trombone 2", Key.B_FLAT),
            new Instrument("Trombone 2", Key.C),
            new Instrument("Trombone 3", Key.B_FLAT),
            new Instrument("Trombone 3", Key.C),
            new Instrument("Trombone 4", Key.B_FLAT),
            new Instrument("Trombone 4", Key.C),
            new Instrument("Bass", Key.C),
            new Instrument("Bass 1", Key.C),
            new Instrument("Bass 1", Key.E_FLAT),
            new Instrument("Bass 2", Key.C),
            new Instrument("Bass 2", Key.B_FLAT),
            new Instrument("Bass String"),
            new Instrument("Percussion"),
            new Instrument("Percussion Timpani"),
            new Instrument("Pauke - Glockenspiel"),
            new Instrument("Direktion", Key.C),

            new Instrument("Soprano Cornet", Key.E_FLAT),
            new Instrument("Solo Cornet", Key.B_FLAT),
            new Instrument("Repiano Cornet", Key.B_FLAT),
            new Instrument("1st Cornet", Key.B_FLAT),
            new Instrument("2nd Cornet", Key.B_FLAT),
            new Instrument("3rd Cornet", Key.B_FLAT),
            new Instrument("Flugel Horn", Key.B_FLAT),
            new Instrument("Solo Horn", Key.E_FLAT),
            new Instrument("1st Horn", Key.E_FLAT),
            new Instrument("2nd Horn", Key.E_FLAT),
            new Instrument("1st Baritone", Key.B_FLAT),
            new Instrument("2nd Baritone", Key.B_FLAT),
            new Instrument("1st Trombone", Key.B_FLAT),
            new Instrument("1st Trombone", Key.C),
            new Instrument("2nd Trombone", Key.B_FLAT),
            new Instrument("2nd Trombone", Key.C),
            new Instrument("Bass Trombone", Key.B_FLAT),
            new Instrument("Bass Trombone", Key.C),
            new Instrument("Euphonium", Key.B_FLAT),
            new Instrument("1st Euphonium", Key.B_FLAT),
            new Instrument("2nd Euphonium", Key.B_FLAT),
            new Instrument("1st Bass", Key.E_FLAT),
            new Instrument("1st Bass", Key.C),
            new Instrument("2nd Bass", Key.B_FLAT),
            new Instrument("2nd Bass", Key.C),
            new Instrument("Drums"),
            new Instrument("Timpani"),
            new Instrument("Percussion"),
            new Instrument("Percussion 1"),
            new Instrument("Percussion 2"),
            new Instrument("Percussion 3"),
            new Instrument("Mallets"),
            new Instrument("Piano Accompaniment"),
            new Instrument("Soloist", Key.B_FLAT),
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
