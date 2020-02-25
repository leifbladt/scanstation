package info.bladt.scanstation.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.bladt.scanstation.model.Composition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExportConfiguration {

    public static void main(String... unused) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Composition> compositions = Arrays.asList(
                new Composition("Puttin on the Ritz"),
//                new Composition("Schmelzende Riesen"),
//                new Composition("Titanic"),
                new Composition("Flowerdale"),
                new Composition("Abide With Me"),
                new Composition("Buster Strikes Back"),
                new Composition("Vesuvius"),
                new Composition("Sounds of Sousa"),
                new Composition("Death or Glory"),
                new Composition("Share My Yoke")
        );

//        for (Composition composition : compositions) {
//            Configuration configuration = ConfigurationFactory.getConfigurationOld(composition);
//
//            byte[] jsonResult2 = mapper.writerWithDefaultPrettyPrinter()
//                    .writeValueAsBytes(configuration.getProcessingConfiguration());
//
//            Path path = Path.of(getScanStationDirectory(), composition.getName(), "processingConfiguration.json");
//
//            Files.write(path, jsonResult2);
//        }


        Composition composition = new Composition("Puttin on the Ritz");

        Configuration configuration = ConfigurationFactory.getConfiguration(composition);
        System.out.println(configuration.getProcessingConfiguration().toString());
        System.out.println(configuration.getExportConfiguration().toString());
    }

}
