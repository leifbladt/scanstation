package info.bladt.scanstation.image;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;

public class ExportConfiguration {

    public static void main(String... unused) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(new PuttingOnTheRitzConfiguration().getProcessingConfiguration());

        byte[] jsonResult2 = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsBytes(new PuttingOnTheRitzConfiguration().getProcessingConfiguration());

        Path path = Path.of(getScanStationDirectory(), "Abide With Me", "processingConfiguration.json");

        Files.write(path, jsonResult2);

        System.out.println(jsonResult);

    }

}
