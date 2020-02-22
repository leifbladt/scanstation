package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;
import info.bladt.scanstation.model.Key;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompoundKeyTest {

    @Test
    public void toString_OnlyKey() {
        CompoundKey compoundKey = new CompoundKey(info.bladt.scanstation.image.processing.Key.CROP_KEY);

        assertEquals("CROP_KEY", compoundKey.toString());
    }

    @Test
    public void toString_OnlyKeyAndInstrument() {
        CompoundKey compoundKey = new CompoundKey(info.bladt.scanstation.image.processing.Key.CROP_KEY, new Instrument("Bass"));

        assertEquals("CROP_KEY/Bass", compoundKey.toString());
    }

    @Test
    public void toString_OnlyKeyAndInstrumentWithKey() {
        CompoundKey compoundKey = new CompoundKey(info.bladt.scanstation.image.processing.Key.CROP_KEY, new Instrument("Bass", Key.C));

        assertEquals("CROP_KEY/Bass (C)", compoundKey.toString());
    }

    @Test
    public void toString_WithKeyAndInstrumentAndPage() {
        CompoundKey compoundKey = new CompoundKey(info.bladt.scanstation.image.processing.Key.CROP_KEY, new Instrument("Bass"), 2);

        assertEquals("CROP_KEY/Bass/2", compoundKey.toString());
    }
}