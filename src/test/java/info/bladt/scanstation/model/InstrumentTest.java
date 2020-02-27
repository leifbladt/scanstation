package info.bladt.scanstation.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentTest {

    @Test
    void constructor_NullValue() {
        Instrument instrument = new Instrument(null);

        assertNull(instrument.getName());
        assertNull(instrument.getKey());
    }

    @Test
    void constructor_EmptyString() {
        Instrument instrument = new Instrument("");

        assertNull(instrument.getName());
        assertNull(instrument.getKey());
    }

    @Test
    void constructor_OnlyWhitespace() {
        Instrument instrument = new Instrument("  ");

        assertNull(instrument.getName());
        assertNull(instrument.getKey());
    }

    @Test
    void constructor_InstrumentWithoutKey() {
        Instrument instrument = new Instrument("Solo Cornet");

        assertEquals("Solo Cornet", instrument.getName());
        assertNull(instrument.getKey());
    }

    @Test
    void constructor_InstrumentWithKey() {
        Instrument instrument = new Instrument("Solo Cornet_Bb");

        assertEquals("Solo Cornet", instrument.getName());
        assertEquals(Key.B_FLAT, instrument.getKey());
    }

    @Test
    void constructor_InstrumentWithUnknownKey() {
        Instrument instrument = new Instrument("Solo Cornet_D");

        assertEquals("Solo Cornet", instrument.getName());
        assertNull(instrument.getKey());
    }

    @Test
    void parse_NullValue() {
        Instrument instrument = Instrument.parse(null);

        assertNull(instrument);
    }

    @Test
    void parse_EmptyString() {
        Instrument instrument = Instrument.parse("");

        assertNull(instrument);
    }

    @Test
    void parse_OnlyWhitespace() {
        Instrument instrument = Instrument.parse("  ");

        assertNull(instrument);
    }

    @Test
    void parse_InstrumentWithoutKey() {
        Instrument instrument = Instrument.parse("Solo Cornet");

        assertEquals(new Instrument("Solo Cornet"), instrument);
    }

    @Test
    void parse_InstrumentWithKey() {
        Instrument instrument = Instrument.parse("Solo Cornet_Bb");

        assertEquals(new Instrument("Solo Cornet", Key.B_FLAT), instrument);
    }
    @Test
    void parse_InstrumentWithUnknownKey() {
        Instrument instrument = Instrument.parse("Solo Cornet_D");

        assertEquals(new Instrument("Solo Cornet"), instrument);
    }
}