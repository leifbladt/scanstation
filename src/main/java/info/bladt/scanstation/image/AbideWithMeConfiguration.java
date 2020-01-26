package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;

public class AbideWithMeConfiguration extends Configuration {

    public AbideWithMeConfiguration() {
        setProcessingConfiguration(new AbideWithMeProcessingConfiguration());
        setExportConfiguration(new AbideWithExportConfiguration());
    }

    public static class AbideWithMeProcessingConfiguration extends ProcessingConfiguration {
        public AbideWithMeProcessingConfiguration() {
            setCrop(false);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPaperEdgeWidth(new RemoveEdges.Width(70));
        }

    }

    public static class AbideWithExportConfiguration extends ExportConfiguration {
        private AbideWithExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f);
        }
    }
}
