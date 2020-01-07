package info.bladt.scanstation.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CropStep implements ProcessingStep {

    @Override
    public BufferedImage process(BufferedImage input, Configuration configuration) {

//        BufferedImage cropped = input.getSubimage(0, 0, input.getWidth(), input.getHeight()); //fill in the corners of the desired crop location here
//        BufferedImage cropped = input.getSubimage(0, 0, 3970, 3400); //fill in the corners of the desired crop location here
        BufferedImage cropped = input.getSubimage(0, 0, 3140, 4250); //fill in the corners of the desired crop location here

        BufferedImage copyOfImage = new BufferedImage(cropped.getWidth(), cropped.getHeight(), input.getType());
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(cropped, 0, 0, null);
        g.dispose();

        return copyOfImage; //or use it however you want
    }
}
