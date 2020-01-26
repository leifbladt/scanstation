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
                return new FlowerdaleConfiguration();
            case "Abide With Me":
                return new AbideWithMeConfiguration();
            case "Buster Strikes Back":
                return new BusterStrikesBackConfiguration();
            case "Vesuvius":
                return new VesuviusConfiguration();
            case "Sounds of Sousa":
                return new SoundsOfSousaConfiguration();
            default:
                return new DefaultConfiguration();
        }
    }
}
