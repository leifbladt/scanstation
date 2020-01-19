package info.bladt.scanstation.image.export;

public class ExportConfiguration {

    // pageSize

    private boolean scaleToFit;
    private float scale;

    public boolean isScaleToFit() {
        return scaleToFit;
    }

    public void setScaleToFit(boolean scaleToFit) {
        this.scaleToFit = scaleToFit;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
