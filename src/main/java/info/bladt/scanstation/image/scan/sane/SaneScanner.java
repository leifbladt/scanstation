package info.bladt.scanstation.image.scan.sane;

import au.com.southsky.jfreesane.SaneDevice;
import au.com.southsky.jfreesane.SaneException;
import au.com.southsky.jfreesane.SaneSession;
import info.bladt.scanstation.image.scan.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Component
public class SaneScanner implements Scanner {

    private static final Logger LOGGER = LogManager.getLogger(SaneScanner.class);

    private SaneDevice saneDevice = null;

    @Override
    public BufferedImage acquireImage() {
        SaneDevice device = null;

        try {
            LOGGER.debug("### Connecting to SANE");
            InetAddress address = InetAddress.getByName("scanner.fritz.box");
            SaneSession session = SaneSession.withRemoteSane(address);
            device = getDevice(session);

            device.open();

            // Set scan options
            device.getOption("mode").setStringValue("Gray");
            device.getOption("resolution").setIntegerValue(600);
            device.getOption("br-x").setFixedValue(210d);
            device.getOption("br-y").setFixedValue(297d);

            // Actually get image
            return device.acquireImage();
        } catch (IOException | SaneException e) {
            LOGGER.error("Could not acquire image", e);
            return null;
        } finally {
            if (device != null) {
                try {
                    device.close();
                } catch (IOException e) {
                    LOGGER.error("Could not close device", e);
                }
            }
        }
    }

    private SaneDevice getDevice(SaneSession session) throws IOException, SaneException {

        if (saneDevice == null) {
            LOGGER.debug("### List devices");
            List<SaneDevice> devices = session.listDevices();
            for (SaneDevice d : devices) {
                LOGGER.debug("{}: {}", d.getVendor(), d.getModel());
            }

            saneDevice = devices.get(0);
        }

        return saneDevice;
    }
}
