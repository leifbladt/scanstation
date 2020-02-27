package info.bladt.scanstation.file;

import info.bladt.scanstation.model.Composition;
import info.bladt.scanstation.model.Instrument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static info.bladt.scanstation.configuration.ApplicationProperties.getScanStationDirectory;

public class TiffReader {

    private TiffReader() {
    }

    public static List<Page> getInputImages(String folder, Composition composition) throws IOException {
        return getInputImages(folder, composition, null);
    }

    public static List<Page> getInputImages(String folder, Composition composition, Instrument instrument) throws IOException {
        Path inputPath = Path.of(getScanStationDirectory(), composition.getName(), folder);

        String fileNameMatcher = (instrument == null) ? ".*[0-9][0-9].tif" : instrument.getFilenamePart() + " [0-9][0-9].tif";

        try (Stream<Path> pathStream = Files.find(inputPath, 1, getByFilename(fileNameMatcher))) {

            return pathStream
                    .map(path -> {
                        Path fileName = path.getFileName();
                        Pattern pattern = Pattern.compile("(.*) ([0-9][0-9]).tif");
                        Matcher matcher = pattern.matcher(fileName.toString());
                        if (matcher.find()) {
                            Instrument i = Instrument.parse(matcher.group(1));
                            return new Page(i, Integer.parseInt(matcher.group(2)), path);
                        } else {
                            throw new RuntimeException("Could not extract page number (" + fileName + ")");
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

    private static BiPredicate<Path, BasicFileAttributes> getByFilename(String fileNameMatcher) {
        return (path, basicFileAttributes) -> path.toFile().getName().matches(fileNameMatcher);
    }

    public static class Page {
        private final Instrument instrument;
        private final int number;
        private final Path path;

        public Page(Instrument instrument, int number, Path path) {
            this.instrument = instrument;
            this.number = number;
            this.path = path;
        }

        public Instrument getInstrument() {
            return instrument;
        }

        public int getNumber() {
            return number;
        }

        public Path getPath() {
            return path;
        }
    }
}
