package info.bladt.scanstation.model;

import info.bladt.scanstation.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompositionService {

    @Autowired
    private FileService fileService;

    public List<Composition> getCompositions() {
        return fileService.getDirectories().stream()
                .map(Composition::new).collect(Collectors.toList());
    }
}
