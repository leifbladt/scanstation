package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;
import info.bladt.scanstation.model.Instrument;

import static info.bladt.scanstation.model.Key.C;
import static info.bladt.scanstation.model.Key.E_FLAT;

public class DeathOrGloryConfiguration extends Configuration {

    public DeathOrGloryConfiguration() {
        setProcessingConfiguration(new DeathOrGloryProcessingConfiguration());
        setExportConfiguration(new DeathOrGloryExportConfiguration());
    }

    public static class DeathOrGloryProcessingConfiguration extends ProcessingConfiguration {
        public DeathOrGloryProcessingConfiguration() {
            setCrop(false);
            setRotate(true);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setRotationAngle(-90);
            setPaperEdgeWidth(new RemoveEdges.Width(70));

            Instrument score = new Instrument("Score");
            setCrop(true);

            setImageX(150, score);
            setImageY(150, score);
            setImageWidth(4660, score);
            setImageHeight(6750, score);
        }

    }

    public static class DeathOrGloryExportConfiguration extends ExportConfiguration {
        private DeathOrGloryExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.LANDSCAPE);
            setScaleToFit(false);
            setScale(72 / 600f);
        }
    }
}
