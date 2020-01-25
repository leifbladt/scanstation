package info.bladt.scanstation.model;

import info.bladt.scanstation.image.file.FileModule;

import java.util.List;
import java.util.stream.Collectors;

public class CompositionModule {

    private final FileModule fileModule;

    public CompositionModule() {
        this.fileModule = new FileModule();
    }

    public List<Composition> getCompositions() {
        return fileModule.getDirectories().stream()
                .map(Composition::new).collect(Collectors.toList());
    }
}
