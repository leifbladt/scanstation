package info.bladt.scanstation.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class ApplicationProperties {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(Path.of(getHomeDirectory(), ".scanstation", "application.properties").toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getScanStationDirectory() {
        return properties.getProperty("scanStationDirectory", ".");
    }

    private static String getHomeDirectory() {
        return System.getProperty("user.home");
    }
}
