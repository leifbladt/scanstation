package info.bladt.scanstation.image.export;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import info.bladt.scanstation.model.Instrument;

import java.util.HashMap;
import java.util.Map;

import static info.bladt.scanstation.image.export.Key.*;


public class ExportConfiguration {

    @JsonValue
    @JsonProperty("configuration")
    @JsonSerialize(keyUsing = CompoundKeySerializer.class)
    @JsonDeserialize(keyUsing = CompoundKeyDeserializer.class)
    private Map<CompoundKey, Object> configuration;

    public ExportConfiguration() {
        this.configuration = new HashMap<>();
    }

    @JsonCreator
    public ExportConfiguration(Map<CompoundKey, Object> configuration) {
        this.configuration = configuration;
    }

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
        return (value != null) ? PageSize.valueOf(value.toString()) : null;
    }

    public void setPageSize(PageSize pageSize) {
        setPageSize(pageSize, null);
    }

    public void setPageSize(PageSize pageSize, Instrument instrument) {
        setValue(PAGE_SIZE_KEY, pageSize, instrument);
    }

    public PageOrientation getPageOrientation(Instrument instrument) {
        Object value = getValue(PAGE_ORIENTATION_KEY, instrument);
        return (value != null) ? PageOrientation.valueOf(value.toString()) : null;
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
        return (value != null) ? Boolean.valueOf(value.toString()) : Boolean.FALSE;
    }

    private Float getFloatValue(Key key, Instrument instrument) {
        Object value = getValue(key, instrument);
        return (value != null) ? Float.valueOf(value.toString()) : null;
    }

    private Object getValue(Key key, Instrument instrument) {

        Object value = configuration.get(new CompoundKey(key, instrument));

        if (value == null) {
            value = configuration.get(new CompoundKey(key));
        }

        return value;
    }

    @Override
    public String toString() {
        return "ExportConfiguration{" +
                "configuration=" + configuration +
                '}';
    }
}
