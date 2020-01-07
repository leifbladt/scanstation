package info.bladt.scanstation.image.file;

import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;

public class TiffReader {

    private TiffReader() {}

    public static List<Page> getInputImages(String folder, Composition composition, Instrument instrument) throws IOException {
        Path inputPath = Path.of(getScanStationDirectory(), folder, composition.getName());

        try (Stream<Path> pathStream = Files.find(inputPath, 1,
                (path, basicFileAttributes) -> path.toFile().getName().matches(instrument.getFilenamePart() + " [0-9][0-9].tif"))) {

            return pathStream
                    .map(path -> {
                        Pattern pattern = Pattern.compile(".*([0-9][0-9]).tif");
                        Matcher matcher = pattern.matcher(path.toString());
                        if (matcher.find()) {
                            return new Page(Integer.parseInt(matcher.group(1)), path);
                        } else {
                            throw new RuntimeException("Could not extract page number");
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

    public static class Page {
        private final int number;
        private final Path path;

        public Page(int number, Path path) {
            this.number = number;
            this.path = path;
        }

        public int getNumber() {
            return number;
        }

        public Path getPath() {
            return path;
        }
    }
}
