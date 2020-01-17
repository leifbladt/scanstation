package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static info.bladt.scanstation.image.processing.ProcessingConfiguration.Key.*;

public class ProcessingConfiguration {

    private Map<CompoundKey, Object> configuration = new HashMap<>();

    public boolean isCrop(Instrument instrument, Integer page) {
        return getBooleanValue(CROP_KEY, instrument, page);
    }

    public void setCrop(boolean crop) {
        setCrop(null, null, crop);
    }

    public void setCrop(Instrument instrument, boolean crop) {
        setCrop(instrument, null, crop);
    }

    public void setCrop(Instrument instrument, Integer page, boolean crop) {
        setValue(CROP_KEY, crop, instrument, page);
    }

    public boolean isRotate(Instrument instrument, Integer page) {
        return getBooleanValue(ROTATE_KEY, instrument, page);
    }

    public void setRotate(boolean rotate) {
        setRotate(null, null, rotate);
    }

    public void setRotate(Instrument instrument, boolean rotate) {
        setRotate(instrument, null, rotate);
    }

    public void setRotate(Instrument instrument, Integer page, boolean rotate) {
        setValue(ROTATE_KEY, rotate, instrument, page);
    }


    public boolean isDeskew(Instrument instrument, Integer page) {
        return getBooleanValue(DESKEW_KEY, instrument, page);
    }

    public void setDeskew(boolean deskew) {
        setDeskew(deskew, null, null);
    }

    public void setDeskew(boolean deskew, Instrument instrument) {
        setDeskew(deskew, instrument, null);
    }

    public void setDeskew(boolean deskew, Instrument instrument, Integer page) {
        setValue(DESKEW_KEY, deskew, instrument, page);
    }


    public boolean isRemoveEdges(Instrument instrument, Integer page) {
        return getBooleanValue(REMOVE_EDGES_KEY, instrument, page);
    }

    public void setRemoveEdges(boolean removeEdges) {
        setRemoveEdges(removeEdges, null, null);
    }

    public void setRemoveEdges(boolean removeEdges, Instrument instrument) {
        setRemoveEdges(removeEdges, instrument, null);
    }

    public void setRemoveEdges(boolean removeEdges, Instrument instrument, Integer page) {
        setValue(REMOVE_EDGES_KEY, removeEdges, instrument, page);
    }


    public int getPageWidth(Instrument instrument, Integer page) {
        return getIntegerValue(PAGE_WIDTH_KEY, instrument, page);
    }

    public void setPageWidth(int pageWidth) {
        setPageWidth(pageWidth, null, null);
    }

    public void setPageWidth(int pageWidth, Instrument instrument) {
        setPageWidth(pageWidth, instrument, null);
    }

    public void setPageWidth(int pageWidth, Instrument instrument, Integer page) {
        setValue(PAGE_WIDTH_KEY, pageWidth, instrument, page);
    }


    public int getPageHeight(Instrument instrument, Integer page) {
        return getIntegerValue(PAGE_HEIGHT_KEY, instrument, page);
    }

    public void setPageHeight(int pageHeight) {
        setPageHeight(pageHeight, null, null);
    }

    public void setPageHeight(int pageHeight, Instrument instrument) {
        setPageHeight(pageHeight, instrument, null);
    }

    public void setPageHeight(int pageHeight, Instrument instrument, Integer page) {
        setValue(PAGE_HEIGHT_KEY, pageHeight, instrument, page);
    }


    public double getRotationAngle(Instrument instrument, Integer page) {
        return getDoubleValue(ROTATION_ANGLE_KEY, instrument, page);
    }

    public void setRotationAngle(double rotationAngle) {
        setRotationAngle(rotationAngle, null, null);
    }

    public void setRotationAngle(double rotationAngle, Instrument instrument) {
        setRotationAngle(rotationAngle, instrument, null);
    }

    public void setRotationAngle(double rotationAngle, Instrument instrument, Integer page) {
        setValue(ROTATION_ANGLE_KEY, rotationAngle, instrument, page);
    }


    public int getPaperEdgeWidth(Instrument instrument, Integer page) {
        return getIntegerValue(PAGE_EDGE_WIDTH_KEY, instrument, page);
    }

    public void setPaperEdgeWidth(int paperEdgeWidth) {
        setPaperEdgeWidth(paperEdgeWidth, null, null);
    }

    public void setPaperEdgeWidth(int paperEdgeWidth, Instrument instrument) {
        setPaperEdgeWidth(paperEdgeWidth, instrument, null);
    }

    public void setPaperEdgeWidth(int paperEdgeWidth, Instrument instrument, Integer page) {
        setValue(PAGE_EDGE_WIDTH_KEY, paperEdgeWidth, instrument, page);
    }

    private Object getValue(Key key, Instrument instrument, Integer page) {

        Object value = configuration.get(new CompoundKey(key, instrument, page));

        if (value == null) {
            value = configuration.get(new CompoundKey(key, instrument));
        }

        if (value == null) {
            value = configuration.get(new CompoundKey(key));
        }

        return value;
    }

    private Boolean getBooleanValue(Key key, Instrument instrument, Integer page) {
        Object value = getValue(key, instrument, page);
        return (value != null) ? (Boolean)value : Boolean.FALSE;
    }

    private Integer getIntegerValue(Key key, Instrument instrument, Integer page) {
        Object value = getValue(key, instrument, page);
        return (value != null) ? (Integer)value : 0;
    }

    private Double getDoubleValue(Key key, Instrument instrument, Integer page) {
        Object value = getValue(key, instrument, page);
        return (value != null) ? (Double)value : 0d;
    }

    private void setValue(Key key, Object value, Instrument instrument, Integer page) {
        configuration.put(new CompoundKey(key, instrument, page), value);
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
