package info.bladt.scanstation.image.processing;

import com.fasterxml.jackson.annotation.JsonValue;
import info.bladt.scanstation.model.Instrument;

import java.util.Objects;

public class CompoundKey {
    private final Key key;
    private final Instrument instrument;
    private final Integer page;

    public CompoundKey(Key key) {
        this(key, null, null);
    }

    public CompoundKey(Key key, Instrument instrument) {
        this(key, instrument, null);
    }

    public CompoundKey(Key key, Instrument instrument, Integer page) {
        this.key = key;
        this.instrument = instrument;
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompoundKey that = (CompoundKey) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(instrument, that.instrument) &&
                Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, instrument, page);
    }

    @JsonValue
    @Override
    public String toString() {
        String s = key.toString();

        if (instrument != null) {
            s = s + "/" + instrument.toString();

            if (page != null) {
                s = s + "/" + page;
            }
        }

        return s;
    }
}
