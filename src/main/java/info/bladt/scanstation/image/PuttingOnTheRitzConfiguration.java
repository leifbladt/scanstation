package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;

public class PuttingOnTheRitzConfiguration extends Configuration {

    public PuttingOnTheRitzConfiguration() {
        setProcessingConfiguration(new FlowerdaleProcessingConfiguration());
        setExportConfiguration(new FlowerdaleExportConfiguration());
    }

    public static class FlowerdaleProcessingConfiguration extends ProcessingConfiguration {
        public FlowerdaleProcessingConfiguration() {
            setCrop(true);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);

            setPageHeight(6700);
            setPageWidth(4800);
            setPaperEdgeWidth(new RemoveEdges.Width(20, 70, 70, 70));
        }

    }

    public static class FlowerdaleExportConfiguration extends ExportConfiguration {
        private FlowerdaleExportConfiguration() {
            setScaleToFit(false);
            setScale(72 / 600f);
        }
    }
}
