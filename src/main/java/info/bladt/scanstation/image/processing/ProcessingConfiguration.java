package info.bladt.scanstation.image.processing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import info.bladt.scanstation.model.Instrument;

import java.util.HashMap;
import java.util.Map;

import static info.bladt.scanstation.image.processing.Key.*;

public class ProcessingConfiguration {

    @JsonValue
    @JsonProperty("configuration")
    @JsonSerialize(keyUsing = CompoundKeySerializer.class)
    @JsonDeserialize(keyUsing = CompoundKeyDeserializer.class)
    private final Map<CompoundKey, Object> configuration;

    public ProcessingConfiguration() {
        configuration = new HashMap<>();
    }

    @JsonCreator
    public ProcessingConfiguration(Map<CompoundKey, Object> configuration) {
        this.configuration = configuration;
    }

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

    public boolean isAdjustContrast(Instrument instrument, Integer page) {
        return getBooleanValue(ADJUST_CONTRAST_KEY, instrument, page);
    }

    public void setAdjustContrast(boolean adjustContrast) {
        setAdjustContrast(adjustContrast, null, null);
    }

    public void setAdjustContrast(boolean adjustContrast, Instrument instrument) {
        setAdjustContrast(adjustContrast, instrument, null);
    }

    public void setAdjustContrast(boolean adjustContrast, Instrument instrument, Integer page) {
        setValue(ADJUST_CONTRAST_KEY, adjustContrast, instrument, page);
    }


    public int getImageX(Instrument instrument, Integer page) {
        return getIntegerValue(IMAGE_X_KEY, instrument, page);
    }

    public void setImageX(int pageWidth) {
        setImageX(pageWidth, null, null);
    }

    public void setImageX(int pageWidth, Instrument instrument) {
        setImageX(pageWidth, instrument, null);
    }

    public void setImageX(int pageWidth, Instrument instrument, Integer page) {
        setValue(IMAGE_X_KEY, pageWidth, instrument, page);
    }


    public int getImageY(Instrument instrument, Integer page) {
        return getIntegerValue(IMAGE_Y_KEY, instrument, page);
    }

    public void setImageY(int pageWidth) {
        setImageY(pageWidth, null, null);
    }

    public void setImageY(int pageWidth, Instrument instrument) {
        setImageY(pageWidth, instrument, null);
    }

    public void setImageY(int pageWidth, Instrument instrument, Integer page) {
        setValue(IMAGE_Y_KEY, pageWidth, instrument, page);
    }


    public int getImageWidth(Instrument instrument, Integer page) {
        return getIntegerValue(IMAGE_WIDTH_KEY, instrument, page);
    }

    public void setImageWidth(int pageWidth) {
        setImageWidth(pageWidth, null, null);
    }

    public void setImageWidth(int pageWidth, Instrument instrument) {
        setImageWidth(pageWidth, instrument, null);
    }

    public void setImageWidth(int pageWidth, Instrument instrument, Integer page) {
        setValue(IMAGE_WIDTH_KEY, pageWidth, instrument, page);
    }


    public int getImageHeight(Instrument instrument, Integer page) {
        return getIntegerValue(IMAGE_HEIGHT_KEY, instrument, page);
    }

    public void setImageHeight(int pageHeight) {
        setImageHeight(pageHeight, null, null);
    }

    public void setImageHeight(int pageHeight, Instrument instrument) {
        setImageHeight(pageHeight, instrument, null);
    }

    public void setImageHeight(int pageHeight, Instrument instrument, Integer page) {
        setValue(IMAGE_HEIGHT_KEY, pageHeight, instrument, page);
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

    public void setRotationAngle(double rotationAngle, Instrument instrument, Integer... pages) {

        if (pages == null) {
            setValue(ROTATION_ANGLE_KEY, rotationAngle, instrument, null);
        } else {
            for (Integer page : pages) {
                setValue(ROTATION_ANGLE_KEY, rotationAngle, instrument, page);
            }
        }
    }

    public void setPaperEdgeWidth(Integer paperEdgeWidth) {
        setPaperEdgeWidth(paperEdgeWidth, null, null);
    }

    public void setPaperEdgeWidth(Integer paperEdgeWidth, Instrument instrument) {
        setPaperEdgeWidth(paperEdgeWidth, instrument, null);
    }

    public void setPaperEdgeWidth(Integer paperEdgeWidth, Instrument instrument, Integer page) {
        setPaperEdgeWidthLeft(paperEdgeWidth, instrument, page);
        setPaperEdgeWidthTop(paperEdgeWidth, instrument, page);
        setPaperEdgeWidthRight(paperEdgeWidth, instrument, page);
        setPaperEdgeWidthBottom(paperEdgeWidth, instrument, page);
    }


    public Integer getPaperEdgeWidthLeft(Instrument instrument, Integer page) {
        return getIntegerValue(PAGE_EDGE_WIDTH_LEFT_KEY, instrument, page);
    }

    public void setPaperEdgeWidthLeft(Integer paperEdgeWidth) {
        setPaperEdgeWidthLeft(paperEdgeWidth, null, null);
    }

    public void setPaperEdgeWidthLeft(Integer paperEdgeWidth, Instrument instrument) {
        setPaperEdgeWidthLeft(paperEdgeWidth, instrument, null);
    }

    public void setPaperEdgeWidthLeft(Integer paperEdgeWidth, Instrument instrument, Integer page) {
        setValue(PAGE_EDGE_WIDTH_LEFT_KEY, paperEdgeWidth, instrument, page);
    }


    public Integer getPaperEdgeWidthRight(Instrument instrument, Integer page) {
        return getIntegerValue(PAGE_EDGE_WIDTH_RIGHT_KEY, instrument, page);
    }

    public void setPaperEdgeWidthRight(Integer paperEdgeWidth) {
        setPaperEdgeWidthRight(paperEdgeWidth, null, null);
    }

    public void setPaperEdgeWidthRight(Integer paperEdgeWidth, Instrument instrument) {
        setPaperEdgeWidthRight(paperEdgeWidth, instrument, null);
    }

    public void setPaperEdgeWidthRight(Integer paperEdgeWidth, Instrument instrument, Integer page) {
        setValue(PAGE_EDGE_WIDTH_RIGHT_KEY, paperEdgeWidth, instrument, page);
    }


    public Integer getPaperEdgeWidthTop(Instrument instrument, Integer page) {
        return getIntegerValue(PAGE_EDGE_WIDTH_TOP_KEY, instrument, page);
    }

    public void setPaperEdgeWidthTop(Integer paperEdgeWidth) {
        setPaperEdgeWidthTop(paperEdgeWidth, null, null);
    }

    public void setPaperEdgeWidthTop(Integer paperEdgeWidth, Instrument instrument) {
        setPaperEdgeWidthTop(paperEdgeWidth, instrument, null);
    }

    public void setPaperEdgeWidthTop(Integer paperEdgeWidth, Instrument instrument, Integer page) {
        setValue(PAGE_EDGE_WIDTH_TOP_KEY, paperEdgeWidth, instrument, page);
    }


    public Integer getPaperEdgeWidthBottom(Instrument instrument, Integer page) {
        return getIntegerValue(PAGE_EDGE_WIDTH_BOTTOM_KEY, instrument, page);
    }

    public void setPaperEdgeWidthBottom(Integer paperEdgeWidth) {
        setPaperEdgeWidthTop(paperEdgeWidth, null, null);
    }

    public void setPaperEdgeWidthBottom(Integer paperEdgeWidth, Instrument instrument) {
        setPaperEdgeWidthTop(paperEdgeWidth, instrument, null);
    }

    public void setPaperEdgeWidthBottom(Integer paperEdgeWidth, Instrument instrument, Integer page) {
        setValue(PAGE_EDGE_WIDTH_BOTTOM_KEY, paperEdgeWidth, instrument, page);
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
        return (value != null) ? (Boolean) value : Boolean.FALSE;
    }

    private Integer getIntegerValue(Key key, Instrument instrument, Integer page) {
        Object value = getValue(key, instrument, page);
        return (value != null) ? (Integer) value : 0;
    }

    private Double getDoubleValue(Key key, Instrument instrument, Integer page) {
        Object value = getValue(key, instrument, page);
        return (value != null) ? (Double) value : 0d;
    }

    private void setValue(Key key, Object value, Instrument instrument, Integer page) {
        configuration.put(new CompoundKey(key, instrument, page), value);
    }

    @Override
    public String toString() {
        return "ProcessingConfiguration{" +
                "configuration=" + configuration +
                '}';
    }
}
