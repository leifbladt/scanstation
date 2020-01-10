package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;

import java.util.HashMap;
import java.util.Map;

public class ProcessingConfiguration {

    private final static String CROP_KEY = "crop";
    private final static String ROTATE_KEY = "rotate";
    private final static String DESKEW_KEY = "deskew";
    private final static String REMOVE_EDGES_KEY = "removeEdges";
    private final static String PAGE_WIDTH_KEY = "pageWidth";
    private final static String PAGE_HEIGHT_KEY = "pageHeight";
    private final static String ROTATION_ANGLE_KEY = "rotationAngle";
    private final static String PAGE_EDGE_WIDTH_KEY = "pageEdgeWidth";

    private Map<String, Object> configuration = new HashMap<>();
    private Map<Instrument, Map<String, Object>> instrumentConfiguration = new HashMap<>();

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

    private Object getValue(String key, Instrument instrument) {
        Object value = null;
        Map<String, Object> instrumentMap = instrumentConfiguration.get(instrument);

        if (instrumentMap != null) {
            value = instrumentMap.get(key);
        }

        if (value == null) {
            value = configuration.get(key);
        }

        return value;
    }

    private Boolean getBooleanValue(String key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Boolean)value : Boolean.FALSE;
    }

    private Integer getIntegerValue(String key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Integer)value : null;
    }

    private Double getDoubleValue(String key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? (Double)value : 0d;
    }

    private void setValue(String key, Object value) {
        configuration.put(key, value);
    }

    private void setValue(String key, Object value, Instrument instrument) {
        instrumentConfiguration.computeIfAbsent(instrument, i -> {
            Map<String, Object> c = new HashMap<>();
            c.put(key , value);
            return c;
        });
    }
}
