package info.bladt.scanstation.ui.scan;

import au.com.southsky.jfreesane.SaneDevice;
import au.com.southsky.jfreesane.SaneSession;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ScanAction extends AbstractAction {

    private final Worker worker;

    public ScanAction() {
        worker = new Worker();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        worker.execute();
    }

    @Override
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        worker.addPropertyChangeListener(listener);
    }

    public BufferedImage get() throws ExecutionException, InterruptedException {
        return worker.get();
    }
    private class Worker extends SwingWorker<BufferedImage, Void> {

        @Override
        protected BufferedImage doInBackground() throws Exception {
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

            System.out.println("### Write scan to file");
            if (!ImageIO.write(bufferedImage, "JPG", new File("scan.jpg"))) {
                System.out.println("Error while writing file");
            }

            try {
                device.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bufferedImage;
        }
    }
}
