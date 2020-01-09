package info.bladt.scanstation.image.processing;

import info.bladt.scanstation.model.Instrument;

import java.util.HashMap;
import java.util.Map;

public class ProcessingConfiguration {

    // Global configuration
    Configuration configuration = new Configuration();

    // Instrument specific configuration
    Map<Instrument, Configuration> instrumentConfigurationMap = new HashMap<>();

    public boolean isCrop() {
        return isCrop(null);
    }

    public boolean isCrop(Instrument instrument) {
        Configuration instrumentConfiguration = instrumentConfigurationMap.get(instrument);

        if (instrumentConfiguration != null) {
            return instrumentConfiguration.isCrop();
        } else {
            return configuration.isCrop();
        }
    }

    public void setCrop(boolean crop) {
        configuration.setCrop(crop);
    }

    public void setCrop(Instrument instrument, boolean crop) {
        instrumentConfigurationMap.computeIfAbsent(instrument, i -> {
            Configuration c = new Configuration();
            c.setCrop(crop);
            return c;
        });
    }

    public boolean isRotate() {
        return isRotate(null);
    }

    public boolean isRotate(Instrument instrument) {
        return configuration.isRotate();
    }

    public void setRotate(boolean rotate) {
        configuration.setRotate(rotate);
    }

    public boolean isDeskew(Instrument instrument) {
        return configuration.isDeskew();
    }

    public void setDeskew(boolean deskew) {
        configuration.setDeskew(deskew);
    }

    public boolean isRemoveEdges(Instrument instrument) {
        return configuration.isRemoveEdges();
    }

    public void setRemoveEdges(boolean removeEdges) {
        configuration.setRemoveEdges(removeEdges);
    }

    public int getPageWidth(Instrument instrument) {
        return configuration.getPageWidth();
    }

    public void setPageWidth(int pageWidth) {
        configuration.setPageWidth(pageWidth);
    }

    public int getPageHeight(Instrument instrument) {
        return configuration.getPageHeight();
    }

    public void setPageHeight(int pageHeight) {
        configuration.setPageHeight(pageHeight);
    }

    public double getRotationAngle(Instrument instrument) {
        return configuration.getRotationAngle();
    }

    public void setRotationAngle(double rotationAngle) {
        configuration.setRotationAngle(rotationAngle);
    }

    public int getPaperEdgeWidth(Instrument instrument) {
        return configuration.getPaperEdgeWidth();
    }

    public void setPaperEdgeWidth(int paperEdgeWidth) {
        configuration.setPaperEdgeWidth(paperEdgeWidth);
    }

    private class Configuration {
        private boolean crop;
        private boolean rotate;
        private boolean deskew;
        private boolean removeEdges;
        private int pageWidth;
        private int pageHeight;
        private double rotationAngle;
        private int paperEdgeWidth;

        public boolean isCrop() {
            return crop;
        }

        public void setCrop(boolean crop) {
            this.crop = crop;
        }

        public boolean isRotate() {
            return rotate;
        }

        public void setRotate(boolean rotate) {
            this.rotate = rotate;
        }

        public boolean isDeskew() {
            return deskew;
        }

        public void setDeskew(boolean deskew) {
            this.deskew = deskew;
        }

        public boolean isRemoveEdges() {
            return removeEdges;
        }

        public void setRemoveEdges(boolean removeEdges) {
            this.removeEdges = removeEdges;
        }

        public int getPageWidth() {
            return pageWidth;
        }

        public void setPageWidth(int pageWidth) {
            this.pageWidth = pageWidth;
        }

        public int getPageHeight() {
            return pageHeight;
        }

        public void setPageHeight(int pageHeight) {
            this.pageHeight = pageHeight;
        }

        public double getRotationAngle() {
            return rotationAngle;
        }

        public void setRotationAngle(double rotationAngle) {
            this.rotationAngle = rotationAngle;
        }

        public int getPaperEdgeWidth() {
            return paperEdgeWidth;
        }

        public void setPaperEdgeWidth(int paperEdgeWidth) {
            this.paperEdgeWidth = paperEdgeWidth;
        }

        @Override
        public String toString() {
            return "ProcessingConfiguration{" +
                    "crop=" + crop +
                    ", rotate=" + rotate +
                    ", deskew=" + deskew +
                    ", removeEdges=" + removeEdges +
                    ", pageWidth=" + pageWidth +
                    ", pageHeight=" + pageHeight +
                    ", rotationAngle=" + rotationAngle +
                    ", paperEdgeWidth=" + paperEdgeWidth +
                    '}';
        }
    }
}
