package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;

public class FlowerdaleConfiguration extends Configuration {

    public FlowerdaleConfiguration() {
        setProcessingConfiguration(new FlowerdaleProcessingConfiguration());
        setExportConfiguration(new FlowerdaleExportConfiguration());
    }

    public static class FlowerdaleProcessingConfiguration extends ProcessingConfiguration {
        public FlowerdaleProcessingConfiguration() {
            setCrop(false);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);

            setPaperEdgeWidth(70);
        }

    }

    public static class FlowerdaleExportConfiguration extends ExportConfiguration {
        private FlowerdaleExportConfiguration() {
            setScaleToFit(false);
            setScale(72 / 600f);
        }
    }
}
