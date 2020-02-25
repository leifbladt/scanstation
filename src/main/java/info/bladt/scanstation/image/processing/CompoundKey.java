package info.bladt.scanstation.image.processing;

import com.fasterxml.jackson.annotation.JsonValue;
import info.bladt.scanstation.model.Instrument;
import org.springframework.util.StringUtils;

import java.util.Objects;

class CompoundKey {
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

    public CompoundKey(String key) {
        String[] split = StringUtils.split(key, "/");

        if (split == null) {
            this.key = Key.valueOf(key);
            this.instrument = null;
            this.page = null;
        } else {

            switch (split.length) {
                case 2:
                    this.key = Key.valueOf(split[0]);
                    this.instrument = Instrument.parse(split[1]);
                    this.page = null;
                    break;
                case 3:
                    this.key = Key.valueOf(split[0]);
                    this.instrument = Instrument.parse(split[1]);
                    this.page = Integer.parseInt(split[2]);
                    break;
                default:
                    throw new RuntimeException("Can't deserialize key " + key);
            }
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append(key.toString());

        if (instrument != null) {
            sb.append("/");
            sb.append(instrument.toString());
        }

        if (page != null) {
            sb.append("/");
            sb.append(page);
        }

        return sb.toString();
    }
}
