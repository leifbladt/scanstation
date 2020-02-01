package scanstation.info.bladt.scanstation.image;

import scanstation.info.bladt.scanstation.image.export.ExportConfiguration;
import scanstation.info.bladt.scanstation.image.processing.ProcessingConfiguration;
import scanstation.info.bladt.scanstation.image.processing.RemoveEdges;
import scanstation.info.bladt.scanstation.model.Instrument;

public class TubaMuklConfiguration extends Configuration {

    public TubaMuklConfiguration() {
        setProcessingConfiguration(new TubaMuklProcessingConfiguration());
        setExportConfiguration(new TubaMuklExportConfiguration());
    }

    public static class TubaMuklProcessingConfiguration extends ProcessingConfiguration {
        public TubaMuklProcessingConfiguration() {
            setCrop(false);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPaperEdgeWidth(new RemoveEdges.Width(70));

            Instrument score = new Instrument("Score");
            setRotate(score, true);
            setCrop(score, false);
            setPaperEdgeWidth(new RemoveEdges.Width(75, 75, 75, 150), score);
            setRotationAngle(90, score, 1, 3, 5, 7, 9, 11, 13, 15, 17);
            setRotationAngle(-90, score, 2, 4, 6, 8, 10, 12, 14, 16, 18);
        }

    }

    public static class TubaMuklExportConfiguration extends ExportConfiguration {
        private TubaMuklExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f);

            setPageOrientation(PageOrientation.LANDSCAPE, new Instrument("Score"));
        }
    }
}
