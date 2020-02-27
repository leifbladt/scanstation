package info.bladt.scanstation.model;

import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instrument implements Comparable<Instrument> {

    private final String name;

    private final Key key;

    public Instrument(String instrument) {
        String[] split = StringUtils.split(instrument, "_");

        if (split == null) {
            this.name = instrument;
            this.key = null;
        } else if (split.length == 2) {
            this.name = split[0];
            this.key = Key.parse(split[1]);
        } else {
            throw new RuntimeException("Can't deserialize instrument " + instrument);
        }
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
