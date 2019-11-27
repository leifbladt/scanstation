package info.bladt.scanstation.image.scan.sane;

import au.com.southsky.jfreesane.SaneDevice;
import au.com.southsky.jfreesane.SaneException;
import au.com.southsky.jfreesane.SaneSession;
import info.bladt.scanstation.image.scan.Scanner;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

public class SaneScanner implements Scanner {

    @Override
    public BufferedImage acquireImage() {
        try {
            System.out.println("### Connecting to SANE");
            InetAddress address = InetAddress.getByName("scanner.fritz.box");
            SaneSession session = SaneSession.withRemoteSane(address);

            System.out.println("### List devices");
            List<SaneDevice> devices = session.listDevices();
            for (SaneDevice device : devices) {
                System.out.println(device.getVendor() + ": " + device.getModel());
            }

            System.out.println("### Prepare device");
            // Prepare device
            SaneDevice device = devices.get(0);
            device.open();

            // Set scan options
            device.getOption("mode").setStringValue("Color");
            device.getOption("resolution").setIntegerValue(150);
            device.getOption("br-x").setFixedValue(80d);
            device.getOption("br-y").setFixedValue(120d);

            // Actually get image
            BufferedImage bufferedImage = device.acquireImage();

            // TODO Move to finally block
            device.close();

            return bufferedImage;
        } catch (IOException | SaneException e) {
            return null;
        }
    }
}
