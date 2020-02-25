package info.bladt.scanstation.image.processing;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

public class CompoundKeySerializer extends JsonSerializer<CompoundKey> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(CompoundKey value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
    }
}
