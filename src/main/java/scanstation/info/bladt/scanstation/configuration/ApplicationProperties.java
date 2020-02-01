package scanstation.info.bladt.scanstation.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class ApplicationProperties {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationProperties.class);

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(Path.of(getHomeDirectory(), ".scanstation", "application.properties").toFile()));
        } catch (IOException e) {
            LOGGER.error("Could not read application properties file", e);
        }
    }

    private ApplicationProperties() {}

    public static String getScanStationDirectory() {
        return properties.getProperty("scanStationDirectory", "./ScanStation");
    }

    private static String getHomeDirectory() {
        return System.getProperty("user.home");
    }
}
