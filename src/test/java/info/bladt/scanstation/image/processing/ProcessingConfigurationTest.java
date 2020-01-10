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
        boolean crop = configuration.isCrop(INSTRUMENT);

        assertFalse(crop);
    }

    @Test
    public void isCrop_GlobalFalse_InstrumentTrue() {
        configuration.setCrop(false);
        configuration.setCrop(INSTRUMENT, true);

        boolean crop = configuration.isCrop(INSTRUMENT);

        assertTrue(crop);
    }

    @Test
    public void isCrop_GlobalTrue_InstrumentFalse() {
        configuration.setCrop(true);
        configuration.setCrop(INSTRUMENT, false);

        boolean crop = configuration.isCrop(INSTRUMENT);

        assertFalse(crop);
    }

    @Test
    public void isCrop_GlobalFalse_InstrumentNotSet() {
        configuration.setCrop(false);

        boolean crop = configuration.isCrop(INSTRUMENT);

        assertFalse(crop);
    }

    @Test
    public void isCrop_GlobalTrue_InstrumentNotSet() {
        configuration.setCrop(true);

        boolean crop = configuration.isCrop(INSTRUMENT);

        assertTrue(crop);
    }

    @Test
    public void isRotate_NotSet() {
        boolean rotate = configuration.isRotate(INSTRUMENT);

        assertFalse(rotate);
    }

    @Test
    public void isRotate_GlobalFalse_InstrumentTrue() {
        configuration.setRotate(false);
        configuration.setRotate(INSTRUMENT, true);

        boolean rotate = configuration.isRotate(INSTRUMENT);

        assertTrue(rotate);
    }

    @Test
    public void isRotate_GlobalTrue_InstrumentFalse() {
        configuration.setRotate(true);
        configuration.setRotate(INSTRUMENT, false);

        boolean rotate = configuration.isRotate(INSTRUMENT);

        assertFalse(rotate);
    }

    @Test
    public void isRotate_GlobalFalse_InstrumentNotSet() {
        configuration.setRotate(false);

        boolean rotate = configuration.isRotate(INSTRUMENT);

        assertFalse(rotate);
    }

    @Test
    public void isRotate_GlobalTrue_InstrumentNotSet() {
        configuration.setRotate(true);

        boolean rotate = configuration.isRotate(INSTRUMENT);

        assertTrue(rotate);
    }


    @Test
    public void getRotationAngle_NotSet() {
        double rotationAngle = configuration.getRotationAngle(INSTRUMENT);

        assertEquals(rotationAngle, 0.0);
    }

    @Test
    public void getRotationAngle_InstrumentSet() {
        configuration.setRotationAngle(37.5);
        configuration.setRotationAngle(45.0, INSTRUMENT);

        double rotationAngle = configuration.getRotationAngle(INSTRUMENT);

        assertEquals(rotationAngle, 45.0);
    }

    @Test
    public void getRotationAngle_InstrumentNotSet() {
        configuration.setRotationAngle(37.5);

        double rotationAngle = configuration.getRotationAngle(INSTRUMENT);

        assertEquals(rotationAngle, 37.5);
    }
}