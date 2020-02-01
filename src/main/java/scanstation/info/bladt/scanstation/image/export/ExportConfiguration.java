package scanstation.info.bladt.scanstation.image.export;

import scanstation.info.bladt.scanstation.model.Instrument;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static scanstation.info.bladt.scanstation.image.export.ExportConfiguration.Key.*;

public class ExportConfiguration {

    private Map<CompoundKey, Object> configuration = new HashMap<>();

    public boolean isScaleToFit(Instrument instrument) {
        return getBooleanValue(SCALE_TO_FIT_KEY, instrument);
    }

    public void setScaleToFit(boolean scaleToFit) {
        setScaleToFit(scaleToFit, null);
    }

    public void setScaleToFit(boolean scaleToFit, Instrument instrument) {
        setValue(SCALE_TO_FIT_KEY, scaleToFit, instrument);
    }

    public float getScale(Instrument instrument) {
        return getFloatValue(SCALE_KEY, instrument);
    }

    public void setScale(float scale) {
        setScale(scale, null);
    }

    public void setScale(float scale, Instrument instrument) {
        setValue(SCALE_KEY, scale, instrument);
    }

    public PageSize getPageSize(Instrument instrument) {
        Object value = getValue(PAGE_SIZE_KEY, instrument);
        return (value != null) ? (PageSize) value : null;
    }

    public void setPageSize(PageSize pageSize) {
        setPageSize(pageSize, null);
    }

    public void setPageSize(PageSize pageSize, Instrument instrument) {
        setValue(PAGE_SIZE_KEY, pageSize, instrument);
    }

    public PageOrientation getPageOrientation(Instrument instrument) {
        Object value = getValue(PAGE_ORIENTATION_KEY, instrument);
        return (value != null) ? (PageOrientation)value : null;
    }

    public void setPageOrientation(PageOrientation pageOrientation) {
        setPageOrientation(pageOrientation, null);
    }

    public void setPageOrientation(PageOrientation pageOrientation, Instrument instrument) {
        setValue(PAGE_ORIENTATION_KEY, pageOrientation, instrument);
    }

    private void setValue(Key key, Object value, Instrument instrument) {
        configuration.put(new CompoundKey(key, instrument), value);
    }

    private Boolean getBooleanValue(Key key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Boolean) value : Boolean.FALSE;
    }

    private Float getFloatValue(Key key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Float) value : null;
    }

    private Object getValue(Key key, Instrument instrument) {

        Object value = configuration.get(new CompoundKey(key, instrument));

        if (value == null) {
            value = configuration.get(new CompoundKey(key));
        }

        return value;
    }

    private class CompoundKey {
        private final Key key;
        private final Instrument instrument;

        public CompoundKey(Key key) {
            this(key, null);
        }

        public CompoundKey(Key key, Instrument instrument) {
            this.key = key;
            this.instrument = instrument;
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

        @Override
        public String toString() {
            return "CompoundKey{" +
                    "key='" + key + '\'' +
                    ", instrument=" + instrument +
                    '}';
        }
    }

    public enum Key {
        SCALE_TO_FIT_KEY,
        SCALE_KEY,
        PAGE_SIZE_KEY,
        PAGE_ORIENTATION_KEY
    }

    public enum PageSize {
        DIN_A4,
        DIN_A5
    }

    public enum PageOrientation {
        LANDSCAPE,
        PORTRAIT
    }
}
