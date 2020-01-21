package info.bladt.scanstation.image;

import info.bladt.scanstation.model.Composition;

public class ConfigurationFactory {

    private ConfigurationFactory() {
    }

    public static Configuration getConfiguration(Composition composition) {
        switch (composition.toString()) {
            case "Puttin on the Ritz":
                return new PuttingOnTheRitzConfiguration();
            case "Schmelzende Riesen":
                return new SchmelzendeRiesenConfiguration();
            case "Titanic":
                return new TitanicConfiguration();
            case "Flowerdale":
            default:
                return new FlowerdaleConfiguration();
        }
    }
}
