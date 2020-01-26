package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;

public class TitanicConfiguration extends Configuration {

    public TitanicConfiguration() {
        setProcessingConfiguration(new TitanicProcessingConfiguration());
        setExportConfiguration(new TitanicExportConfiguration());
    }

    public static class TitanicProcessingConfiguration extends ProcessingConfiguration {
        public TitanicProcessingConfiguration() {
            setCrop(false);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);

            setPaperEdgeWidth(new RemoveEdges.Width(20));
        }

    }

    public static class TitanicExportConfiguration extends ExportConfiguration {
        private TitanicExportConfiguration() {
            setScaleToFit(false);
            setScale(72 / 300f * 0.9f);
        }
    }
}
