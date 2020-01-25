package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;

public class SoundsOfSousaConfiguration extends Configuration {

    public SoundsOfSousaConfiguration() {
        setProcessingConfiguration(new SoundsOfSousaProcessingConfiguration());
        setExportConfiguration(new SoundsOfSousaExportConfiguration());
    }

    public static class SoundsOfSousaProcessingConfiguration extends ProcessingConfiguration {
        public SoundsOfSousaProcessingConfiguration() {
            setCrop(true);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPageHeight(6052);
            setPageWidth(4272);
            setPaperEdgeWidth(new RemoveEdges.Width(70));
        }

    }

    public static class SoundsOfSousaExportConfiguration extends ExportConfiguration {
        private SoundsOfSousaExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f * 1.1f);
        }
    }
}
