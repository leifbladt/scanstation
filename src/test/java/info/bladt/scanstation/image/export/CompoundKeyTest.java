package info.bladt.scanstation.image.export;

import info.bladt.scanstation.model.Instrument;
import info.bladt.scanstation.model.Key;
import org.junit.jupiter.api.Test;

import static info.bladt.scanstation.image.export.ExportKey.PAGE_SIZE_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompoundKeyTest {

    @Test
    public void constructor_KeyOnly() {
        CompoundKey compoundKey = new CompoundKey("PAGE_SIZE_KEY");

        assertEquals(new CompoundKey(PAGE_SIZE_KEY), compoundKey);
    }

    @Test
    public void constructor_OnlyKeyAndInstrument() {
        CompoundKey compoundKey = new CompoundKey("PAGE_SIZE_KEY/Bass");

        assertEquals(new CompoundKey(PAGE_SIZE_KEY, new Instrument("Bass")), compoundKey);
    }

    @Test
    public void constructor_OnlyKeyAndInstrumentWithKey() {
        CompoundKey compoundKey = new CompoundKey("PAGE_SIZE_KEY/Bass_C");

        assertEquals(new CompoundKey(PAGE_SIZE_KEY, new Instrument("Bass", Key.C)), compoundKey);
    }

    @Test
    public void toString_OnlyKey() {
        CompoundKey compoundKey = new CompoundKey(PAGE_SIZE_KEY);

        assertEquals("PAGE_SIZE_KEY", compoundKey.toString());
    }

    @Test
    public void toString_OnlyKeyAndInstrument() {
        CompoundKey compoundKey = new CompoundKey(PAGE_SIZE_KEY, new Instrument("Bass"));

        assertEquals("PAGE_SIZE_KEY/Bass", compoundKey.toString());
    }

    @Test
    public void toString_OnlyKeyAndInstrumentWithKey() {
        CompoundKey compoundKey = new CompoundKey(PAGE_SIZE_KEY, new Instrument("Bass", Key.C));

        assertEquals("PAGE_SIZE_KEY/Bass (C)", compoundKey.toString());
    }
}