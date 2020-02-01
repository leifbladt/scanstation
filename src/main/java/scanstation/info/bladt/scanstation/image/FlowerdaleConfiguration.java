package scanstation.info.bladt.scanstation.image;

import scanstation.info.bladt.scanstation.image.export.ExportConfiguration;
import scanstation.info.bladt.scanstation.image.processing.ProcessingConfiguration;
import scanstation.info.bladt.scanstation.image.processing.RemoveEdges;

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

            setPaperEdgeWidth(new RemoveEdges.Width(70));
        }

    }

    public static class FlowerdaleExportConfiguration extends ExportConfiguration {
        private FlowerdaleExportConfiguration() {
            setScaleToFit(false);
            setScale(72 / 600f);
        }
    }
}
