package info.bladt.scanstation.image.acquisition.sane;

import au.com.southsky.jfreesane.SaneDevice;
import au.com.southsky.jfreesane.SaneException;
import au.com.southsky.jfreesane.SaneSession;
import info.bladt.scanstation.image.acquisition.Acquisition;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

public class SaneAcquisition implements Acquisition {

    @Override
    public Image acquireImage() {
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

            // TODO Move to separate module
            System.out.println("### Write scan to file");
            if (!ImageIO.write(bufferedImage, "JPG", new File("scan.jpg"))) {
                System.out.println("Error while writing file");
            }

            // TODO Move to finally block
            device.close();

            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException | SaneException e) {
            return null;
        }
    }
}
