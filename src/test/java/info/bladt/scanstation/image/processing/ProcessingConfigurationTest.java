package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingConfigurationTest {

    private static final Instrument INSTRUMENT = Instrument.INSTRUMENTS.get(0);

    private ProcessingConfiguration configuration;

    @BeforeEach
    public void setUp() {
        configuration = new ProcessingConfiguration();
    }

    @Test
    public void isCrop_NotSet() {
        boolean crop = configuration.isCrop(INSTRUMENT, 1);

        assertFalse(crop);
    }

    @Test
    public void isCrop_GlobalFalse_InstrumentTrue() {
        configuration.setCrop(false);
        configuration.setCrop(INSTRUMENT, true);

        boolean crop = configuration.isCrop(INSTRUMENT, 1);

        assertTrue(crop);
    }

    @Test
    public void isCrop_GlobalFalse_InstrumentFalse_PageTrue() {
        configuration.setCrop(false);
        configuration.setCrop(INSTRUMENT, false);
        configuration.setCrop(INSTRUMENT, 1, true);

        boolean crop = configuration.isCrop(INSTRUMENT, 1);

        assertTrue(crop);
    }

    @Test
    public void isCrop_GlobalTrue_InstrumentFalse() {
        configuration.setCrop(true);
        configuration.setCrop(INSTRUMENT, false);

        boolean crop = configuration.isCrop(INSTRUMENT, 1);

        assertFalse(crop);
    }

    @Test
    public void isCrop_GlobalTrue_InstrumentTrue_PageFalse() {
        configuration.setCrop(true);
        configuration.setCrop(INSTRUMENT, true);
        configuration.setCrop(INSTRUMENT, 1, false);

        boolean crop = configuration.isCrop(INSTRUMENT, 1);

        assertFalse(crop);
    }

    @Test
    public void isCrop_GlobalFalse_InstrumentNotSet() {
        configuration.setCrop(false);

        boolean crop = configuration.isCrop(INSTRUMENT, null);

        assertFalse(crop);
    }

    @Test
    public void isCrop_GlobalTrue_InstrumentNotSet() {
        configuration.setCrop(true);

        boolean crop = configuration.isCrop(INSTRUMENT, null);

        assertTrue(crop);
    }

    @Test
    public void isRotate_NotSet() {
        boolean rotate = configuration.isRotate(INSTRUMENT, null);

        assertFalse(rotate);
    }

    @Test
    public void isRotate_GlobalFalse_InstrumentTrue() {
        configuration.setRotate(false);
        configuration.setRotate(INSTRUMENT, true);

        boolean rotate = configuration.isRotate(INSTRUMENT, null);

        assertTrue(rotate);
    }

    @Test
    public void isRotate_GlobalTrue_InstrumentFalse() {
        configuration.setRotate(true);
        configuration.setRotate(INSTRUMENT, false);

        boolean rotate = configuration.isRotate(INSTRUMENT, null);

        assertFalse(rotate);
    }

    @Test
    public void isRotate_GlobalFalse_InstrumentNotSet() {
        configuration.setRotate(false);

        boolean rotate = configuration.isRotate(INSTRUMENT, null);

        assertFalse(rotate);
    }

    @Test
    public void isRotate_GlobalTrue_InstrumentNotSet() {
        configuration.setRotate(true);

        boolean rotate = configuration.isRotate(INSTRUMENT, null);

        assertTrue(rotate);
    }


    @Test
    public void getRotationAngle_NotSet() {
        double rotationAngle = configuration.getRotationAngle(INSTRUMENT, null);

        assertEquals(rotationAngle, 0.0);
    }

    @Test
    public void getRotationAngle_InstrumentSet() {
        configuration.setRotationAngle(37.5);
        configuration.setRotationAngle(45.0, INSTRUMENT);

        double rotationAngle = configuration.getRotationAngle(INSTRUMENT, null);

        assertEquals(rotationAngle, 45.0);
    }

    @Test
    public void getRotationAngle_InstrumentNotSet() {
        configuration.setRotationAngle(37.5);

        double rotationAngle = configuration.getRotationAngle(INSTRUMENT, null);

        assertEquals(rotationAngle, 37.5);
    }

    @Test
    public void getPageHeight_NotSet() {
        int rotationAngle = configuration.getPageHeight(INSTRUMENT, null);

        assertEquals(rotationAngle, 0);
    }

    @Test
    public void getPageHeight_InstrumentSet() {
        configuration.setPageHeight(297);
        configuration.setPageHeight(210, INSTRUMENT);

        int rotationAngle = configuration.getPageHeight(INSTRUMENT, null);

        assertEquals(rotationAngle, 210);
    }

    @Test
    public void getPageHeight_InstrumentNotSet() {
        configuration.setPageHeight(297);

        int rotationAngle = configuration.getPageHeight(INSTRUMENT, null);

        assertEquals(rotationAngle, 297);
    }
}