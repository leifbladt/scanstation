package info.bladt.scanstation.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.model.Composition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;

@Service
public class ConfigurationService {

    private static final Logger LOGGER = LogManager.getLogger(ConfigurationService.class);

    private ConfigurationService() {
    }

    public static Configuration getConfiguration(Composition composition) {

        Configuration configuration = new Configuration();

        configuration.setProcessingConfiguration(readProcessingConfiguration(composition));
        configuration.setExportConfiguration(readExportConfiguration(composition));

        return configuration;
    }

    private static ProcessingConfiguration readProcessingConfiguration(Composition composition) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            Path path = Path.of(getScanStationDirectory(), composition.getName(), "processingConfiguration.json");
            byte[] lines = Files.readAllBytes(path);

            return mapper.readValue(lines, ProcessingConfiguration.class);

        } catch (IOException e) {
            LOGGER.error("Can't read processing configuration from file ({})", composition, e);
            return null;
        }
    }

    private static ExportConfiguration readExportConfiguration(Composition composition) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            Path path = Path.of(getScanStationDirectory(), composition.getName(), "exportConfiguration.json");
            byte[] lines = Files.readAllBytes(path);

            return mapper.readValue(lines, ExportConfiguration.class);

        } catch (IOException e) {
            LOGGER.error("Can't read export configuration from file ({})", composition, e);
            return null;
        }
    }
}
