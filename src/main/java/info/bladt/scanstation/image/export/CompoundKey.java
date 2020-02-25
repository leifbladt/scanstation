package info.bladt.scanstation.image.export;

import com.fasterxml.jackson.annotation.JsonValue;
import info.bladt.scanstation.model.Instrument;
import org.springframework.util.StringUtils;

import java.util.Objects;

class CompoundKey {
    private final ExportKey key;
    private final Instrument instrument;

    public CompoundKey(ExportKey key) {
        this(key, null);
    }

    public CompoundKey(ExportKey key, Instrument instrument) {
        this.key = key;
        this.instrument = instrument;
    }

    public CompoundKey(String key) {
        String[] split = StringUtils.split(key, "/");

        if (split == null) {
            this.key = ExportKey.valueOf(key);
            this.instrument = null;
        } else if (split.length == 2) {
            this.key = ExportKey.valueOf(split[0]);
            this.instrument = new Instrument(split[1]);
        } else {
            throw new RuntimeException("Can't deserialize key " + key);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompoundKey that = (CompoundKey) o;
        return key == that.key &&
                Objects.equals(instrument, that.instrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, instrument);
    }

    @JsonValue
    @Override
    public String toString() {
        String s = key.toString();

        if (instrument != null) {
            s = s + "/" + instrument.toString();
        }

        return s;
    }
}
