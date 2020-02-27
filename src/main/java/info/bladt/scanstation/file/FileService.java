package info.bladt.scanstation.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.model.Composition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;

@Service
public class FileService {

    private static final Logger LOGGER = LogManager.getLogger(FileService.class);

    @Autowired
    private ObjectMapper objectMapper;

    public List<String> getDirectories() {
        Path inputPath = Path.of(getScanStationDirectory());

        try (Stream<Path> list = Files.list(inputPath)) {
            return list
                    .filter(f -> f.toFile().isDirectory())
                    .map(dir -> dir.getFileName().toString()).collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public ProcessingConfiguration readProcessingConfiguration(Composition composition) {
        return readConfiguration(composition, "processingConfiguration.json", ProcessingConfiguration.class);
    }

    public ExportConfiguration readExportConfiguration(Composition composition) {
        return readConfiguration(composition, "exportConfiguration.json", ExportConfiguration.class);
    }

    private <T> T readConfiguration(Composition composition, String filename, Class<T> clazz) {
        try {
            Path path = Path.of(getScanStationDirectory(), composition.getName(), filename);
            byte[] lines = Files.readAllBytes(path);

            return objectMapper.readValue(lines, clazz);

        } catch (IOException e) {
            LOGGER.error("Can't read configuration from file ({})", composition, e);
            return null;
        }
    }
}
