package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static info.bladt.scanstation.image.processing.ProcessingConfiguration.Key.*;

public class ProcessingConfiguration {

    private Map<CompoundKey, Object> configuration = new HashMap<>();

    public boolean isCrop(Instrument instrument) {
        return getBooleanValue(CROP_KEY, instrument);
    }

    public void setCrop(boolean crop) {
        setValue(CROP_KEY, crop);
    }

    public void setCrop(Instrument instrument, boolean crop) {
        setValue(CROP_KEY, crop, instrument);
    }


    public boolean isRotate(Instrument instrument) {
        return getBooleanValue(ROTATE_KEY, instrument);
    }

    public void setRotate(boolean rotate) {
        setValue(ROTATE_KEY, rotate);
    }

    public void setRotate(Instrument instrument, boolean rotate) {
        setValue(ROTATE_KEY, rotate, instrument);
    }


    public boolean isDeskew(Instrument instrument) {
        return getBooleanValue(DESKEW_KEY, instrument);
    }

    public void setDeskew(boolean deskew) {
        setValue(DESKEW_KEY, deskew);
    }

    public void setDeskew(boolean deskew, Instrument instrument) {
        setValue(DESKEW_KEY, deskew, instrument);
    }


    public boolean isRemoveEdges(Instrument instrument) {
        return getBooleanValue(REMOVE_EDGES_KEY, instrument);
    }

    public void setRemoveEdges(boolean removeEdges) {
        setValue(REMOVE_EDGES_KEY, removeEdges);
    }

    public void setRemoveEdges(boolean removeEdges, Instrument instrument) {
        setValue(REMOVE_EDGES_KEY, removeEdges, instrument);
    }


    public int getPageWidth(Instrument instrument) {
        return getIntegerValue(PAGE_WIDTH_KEY, instrument);
    }

    public void setPageWidth(int pageWidth) {
        setValue(PAGE_WIDTH_KEY, pageWidth);
    }

    public void setPageWidth(int pageWidth, Instrument instrument) {
        setValue(PAGE_WIDTH_KEY, pageWidth, instrument);
    }


    public int getPageHeight(Instrument instrument) {
        return getIntegerValue(PAGE_HEIGHT_KEY, instrument);
    }

    public void setPageHeight(int pageHeight) {
        setValue(PAGE_HEIGHT_KEY, pageHeight);
    }

    public void setPageHeight(int pageHeight, Instrument instrument) {
        setValue(PAGE_HEIGHT_KEY, pageHeight, instrument);
    }


    public double getRotationAngle(Instrument instrument) {
        return getDoubleValue(ROTATION_ANGLE_KEY, instrument);
    }

    public void setRotationAngle(double rotationAngle) {
        setValue(ROTATION_ANGLE_KEY, rotationAngle);
    }

    public void setRotationAngle(double rotationAngle, Instrument instrument) {
        setValue(ROTATION_ANGLE_KEY, rotationAngle, instrument);
    }


    public int getPaperEdgeWidth(Instrument instrument) {
        return getIntegerValue(PAGE_EDGE_WIDTH_KEY, instrument);
    }

    public void setPaperEdgeWidth(int paperEdgeWidth) {
        setValue(PAGE_EDGE_WIDTH_KEY, paperEdgeWidth);
    }

    public void setPaperEdgeWidth(int paperEdgeWidth, Instrument instrument) {
        setValue(PAGE_EDGE_WIDTH_KEY, paperEdgeWidth, instrument);
    }

    private Object getValue(Key key, Instrument instrument) {
        CompoundKey compoundKey = new CompoundKey(key, instrument);
        Object value = null;
        Object instrumentMap = configuration.get(compoundKey);

        if (instrumentMap != null) {
            value = instrumentMap;
        }

        if (value == null) {
            value = configuration.get(new CompoundKey(key));
        }

        return value;
    }

    private Boolean getBooleanValue(Key key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Boolean)value : Boolean.FALSE;
    }

    private Integer getIntegerValue(Key key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Integer)value : 0;
    }

    private Double getDoubleValue(Key key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Double)value : 0d;
    }

    private void setValue(Key key, Object value) {
        configuration.put(new CompoundKey(key), value);
    }

    private void setValue(Key key, Object value, Instrument instrument) {
        configuration.put(new CompoundKey(key, instrument), value);
    }

    private class CompoundKey {
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

        @Override
        public String toString() {
            return "CompoundKey{" +
                    "key='" + key + '\'' +
                    ", instrument=" + instrument +
                    ", page=" + page +
                    '}';
        }
    }
     public enum Key {
         CROP_KEY,
         ROTATE_KEY,
         DESKEW_KEY,
         REMOVE_EDGES_KEY,
         PAGE_WIDTH_KEY,
         PAGE_HEIGHT_KEY,
         ROTATION_ANGLE_KEY,
         PAGE_EDGE_WIDTH_KEY
     }
}
