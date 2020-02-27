package info.bladt.scanstation.image;

import info.bladt.scanstation.image.file.FileService;
import info.bladt.scanstation.model.Composition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    private FileService fileService;

    public Configuration getConfiguration(Composition composition) {

        Configuration configuration = new Configuration();

        configuration.setProcessingConfiguration(fileService.readProcessingConfiguration(composition));
        configuration.setExportConfiguration(fileService.readExportConfiguration(composition));

        return configuration;
    }

}
