package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;

public class VesuviusConfiguration extends Configuration {

    public VesuviusConfiguration() {
        setProcessingConfiguration(new VesuviusProcessingConfiguration());
        setExportConfiguration(new VesuviusExportConfiguration());
    }

    public static class VesuviusProcessingConfiguration extends ProcessingConfiguration {
        public VesuviusProcessingConfiguration() {
            setCrop(true);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPageHeight(6372);
            setPageWidth(4344);
            setPaperEdgeWidth(new RemoveEdges.Width(70));
        }

    }

    public static class VesuviusExportConfiguration extends ExportConfiguration {
        private VesuviusExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f * 1.08f);
        }
    }
}
