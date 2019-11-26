package info.bladt.scanstation.image.acquisition;

import info.bladt.scanstation.image.acquisition.demo.DemoAcquisition;
import info.bladt.scanstation.image.acquisition.sane.SaneAcquisition;

public class AcquisitionFactory {

    private static Acquisition acquisition;

    private final static boolean USE_DEMO = true;

    // TODO Synchronized
    public static Acquisition getAcquisition() {

        if (acquisition == null) {
            if (USE_DEMO) {
                acquisition = new DemoAcquisition();
            } else {
                acquisition = new SaneAcquisition();
            }
        }

        return acquisition;
    }
}
