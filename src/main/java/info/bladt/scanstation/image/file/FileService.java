package info.bladt.scanstation.image.file;

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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;

@Service
public class FileService {

    private static final Logger LOGGER = LogManager.getLogger(FileService.class);


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

    public ExportConfiguration readExportConfiguration(Composition composition) {

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
