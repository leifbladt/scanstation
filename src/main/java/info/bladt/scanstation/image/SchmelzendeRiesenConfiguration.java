package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;

public class SchmelzendeRiesenConfiguration extends Configuration {

    public SchmelzendeRiesenConfiguration() {
        setProcessingConfiguration(new SchmelzendeRiesenProcessingConfiguration());
        setExportConfiguration(new SchmelzendeRiesenExportConfiguration());
    }

    public static class SchmelzendeRiesenProcessingConfiguration extends ProcessingConfiguration {
        public SchmelzendeRiesenProcessingConfiguration() {
            setCrop(false);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);

            setPaperEdgeWidth(new RemoveEdges.Width(70));
        }

    }

    public static class SchmelzendeRiesenExportConfiguration extends ExportConfiguration {
        private SchmelzendeRiesenExportConfiguration() {
            setScaleToFit(false);
            setScale(72 / 600f);
        }
    }
}
