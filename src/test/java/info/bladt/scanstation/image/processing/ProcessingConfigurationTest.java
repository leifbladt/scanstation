package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingConfigurationTest {

    public static final Instrument INSTRUMENT = Instrument.INSTRUMENTS.get(0);

    @Test
    void isCrop() {
        ProcessingConfiguration configuration = new ProcessingConfiguration();

        boolean crop = configuration.isCrop();

        assertEquals(crop, false);
    }

    @Test
    void isCrop_GlobalFalse() {
        ProcessingConfiguration configuration = new ProcessingConfiguration();
        configuration.setCrop(false);

        boolean crop = configuration.isCrop();

        assertEquals(crop, false);
    }

    @Test
    void isCrop_GlobalTrue() {
        ProcessingConfiguration configuration = new ProcessingConfiguration();
        configuration.setCrop(true);

        boolean crop = configuration.isCrop();

        assertEquals(crop, true);
    }

    @Test
    void isCrop_GlobalFalse_InstrumentTrue() {
        ProcessingConfiguration configuration = new ProcessingConfiguration();
        configuration.setCrop(false);
        configuration.setCrop(INSTRUMENT, true);

        boolean cropGlobal = configuration.isCrop();
        boolean cropInstrument = configuration.isCrop(INSTRUMENT);

        assertEquals(cropGlobal, false);
        assertEquals(cropInstrument, true);
    }

    @Test
    void isCrop_GlobalTrue_InstrumentFalse() {
        ProcessingConfiguration configuration = new ProcessingConfiguration();
        configuration.setCrop(true);
        configuration.setCrop(INSTRUMENT, false);

        boolean cropGlobal = configuration.isCrop();
        boolean cropInstrument = configuration.isCrop(INSTRUMENT);

        assertEquals(cropGlobal, true);
        assertEquals(cropInstrument, false);
    }

    @Test
    void isCrop_GlobalFalse_InstrumentNotSet() {
        ProcessingConfiguration configuration = new ProcessingConfiguration();
        configuration.setCrop(false);

        boolean cropGlobal = configuration.isCrop();
        boolean cropInstrument = configuration.isCrop(INSTRUMENT);

        assertEquals(cropGlobal, false);
        assertEquals(cropInstrument, false);
    }

    @Test
    void isCrop_GlobalTrue_InstrumentNotSet() {
        ProcessingConfiguration configuration = new ProcessingConfiguration();
        configuration.setCrop(true);

        boolean cropGlobal = configuration.isCrop();
        boolean cropInstrument = configuration.isCrop(INSTRUMENT);

        assertEquals(cropGlobal, true);
        assertEquals(cropInstrument, true);
    }
}