package info.bladt.scanstation.image.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;

public class FileModule {

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
}
