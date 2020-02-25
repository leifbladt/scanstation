package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.export.PageOrientation;
import info.bladt.scanstation.image.export.PageSize;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;

public class DefaultConfiguration extends Configuration {

    public DefaultConfiguration() {
        setProcessingConfiguration(new DefaultProcessingConfiguration());
        setExportConfiguration(new DefaultExportConfiguration());
    }

    public static class DefaultProcessingConfiguration extends ProcessingConfiguration {
        public DefaultProcessingConfiguration() {
            setCrop(false);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPaperEdgeWidth(75);
        }

    }

    public static class DefaultExportConfiguration extends ExportConfiguration {
        private DefaultExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f * 1.0f);
        }
    }
}
