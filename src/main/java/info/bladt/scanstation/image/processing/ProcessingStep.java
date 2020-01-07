package info.bladt.scanstation.image.processing;

import java.awt.image.BufferedImage;

public interface ProcessingStep {

    BufferedImage process(BufferedImage input, Configuration configuration);

    abstract class Configuration {

    }
}
