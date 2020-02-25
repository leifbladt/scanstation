package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;
import info.bladt.scanstation.model.Key;
import org.junit.jupiter.api.Test;

import static info.bladt.scanstation.image.processing.Key.CROP_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompoundKeyTest {

    @Test
    public void constructor_KeyOnly() {
        CompoundKey compoundKey = new CompoundKey("CROP_KEY");

        assertEquals(new CompoundKey(CROP_KEY), compoundKey);
    }

    @Test
    public void constructor_OnlyKeyAndInstrument() {
        CompoundKey compoundKey = new CompoundKey("CROP_KEY/Bass");

        assertEquals(new CompoundKey(CROP_KEY, new Instrument("Bass")), compoundKey);
    }

    @Test
    public void constructor_OnlyKeyAndInstrumentWithKey() {
        CompoundKey compoundKey = new CompoundKey("CROP_KEY/Bass (C)");

        // TODO Check with equality on CompoundKey
        assertEquals(new CompoundKey(CROP_KEY, new Instrument("Bass", Key.C)).toString(), compoundKey.toString());
    }

    @Test
    public void constructor_WithKeyAndInstrumentAndPage() {
        CompoundKey compoundKey = new CompoundKey("CROP_KEY/Bass/2");

        // TODO Check with equality on CompoundKey
        assertEquals(new CompoundKey(CROP_KEY, new Instrument("Bass"), 2).toString(), compoundKey.toString());
    }

    @Test
    public void toString_OnlyKey() {
        CompoundKey compoundKey = new CompoundKey(CROP_KEY);

        assertEquals("CROP_KEY", compoundKey.toString());
    }

    @Test
    public void toString_OnlyKeyAndInstrument() {
        CompoundKey compoundKey = new CompoundKey(CROP_KEY, new Instrument("Bass"));

        assertEquals("CROP_KEY/Bass", compoundKey.toString());
    }

    @Test
    public void toString_OnlyKeyAndInstrumentWithKey() {
        CompoundKey compoundKey = new CompoundKey(CROP_KEY, new Instrument("Bass", Key.C));

        assertEquals("CROP_KEY/Bass (C)", compoundKey.toString());
    }

    @Test
    public void toString_WithKeyAndInstrumentAndPage() {
        CompoundKey compoundKey = new CompoundKey(CROP_KEY, new Instrument("Bass"), 2);

        assertEquals("CROP_KEY/Bass/2", compoundKey.toString());
    }
}