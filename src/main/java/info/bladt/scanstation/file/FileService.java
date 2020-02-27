package info.bladt.scanstation.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
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

    public List<Composition> getCompositions() {
        return getDirectories().stream()
                .map(Composition::new).collect(Collectors.toList());
    }

    public ProcessingConfiguration readProcessingConfiguration(Composition composition) {
        Path path = Path.of(getScanStationDirectory(), composition.getName(), "processingConfiguration.json");
        return readConfiguration(path, ProcessingConfiguration.class);
    }

    public ExportConfiguration readExportConfiguration(Composition composition) {
        Path path = Path.of(getScanStationDirectory(), composition.getName(), "exportConfiguration.json");
        return readConfiguration(path, ExportConfiguration.class);
    }

    public List<Instrument> getInstruments() {
        Path path = Path.of(getScanStationDirectory(), "instruments.json");
        return Arrays.asList(readConfiguration(path, Instrument[].class));
    }

    private <T> T readConfiguration(Path path, Class<T> clazz) {
        try {
            byte[] lines = Files.readAllBytes(path);

            return objectMapper.readValue(lines, clazz);

        } catch (IOException e) {
            LOGGER.error("Can't read configuration from file ({})", e);
            return null;
        }
    }
}
