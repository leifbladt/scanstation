package scanstation.info.bladt.scanstation.image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Contrast {

    public BufferedImage process(BufferedImage input) {

        BufferedImage filtered = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        RescaleOp rescaleOp = new RescaleOp(1.2f, -32, null);
        rescaleOp.filter(input, filtered);

        return filtered;
    }
}
