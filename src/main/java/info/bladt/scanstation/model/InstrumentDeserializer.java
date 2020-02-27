package info.bladt.scanstation.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;

public class InstrumentDeserializer extends StdScalarDeserializer<Instrument> {

    public InstrumentDeserializer() {
        super(Instrument.class);
    }

    @Override
    public Instrument deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();

        if (t == JsonToken.VALUE_STRING) {
            String str = p.getText().trim();
            return Instrument.parse(str);
        }
        ctxt.handleUnexpectedToken(handledType(), p);
        return null;
    }
}
