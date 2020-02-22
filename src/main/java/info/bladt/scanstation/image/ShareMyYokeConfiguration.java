package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;
import info.bladt.scanstation.model.Instrument;

import static info.bladt.scanstation.model.Key.C;

public class ShareMyYokeConfiguration extends Configuration {

    public ShareMyYokeConfiguration() {
        setProcessingConfiguration(new ShareMyYokeProcessingConfiguration());
        setExportConfiguration(new ShareMyYokeExportConfiguration());
    }

    public static class ShareMyYokeProcessingConfiguration extends ProcessingConfiguration {
        public ShareMyYokeProcessingConfiguration() {
            setCrop(false);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPaperEdgeWidth(new RemoveEdges.Width(70));
        }

    }

    public static class ShareMyYokeExportConfiguration extends ExportConfiguration {
        private ShareMyYokeExportConfiguration() {
            setPageSize(PageSize.DIN_A5);
            setPageOrientation(PageOrientation.LANDSCAPE);
            setScaleToFit(false);
            setScale(72 / 600f);

            Instrument pianoAccompaniment = new Instrument("Piano Accompaniment");
            setPageSize(PageSize.DIN_A4, pianoAccompaniment);
            setPageOrientation(PageOrientation.PORTRAIT, pianoAccompaniment);

            Instrument firstBass = new Instrument("1st Bass", C);
            setPageSize(PageSize.DIN_A4, firstBass);
            setPageOrientation(PageOrientation.PORTRAIT, firstBass);

            Instrument secondBass = new Instrument("2nd Bass", C);
            setPageSize(PageSize.DIN_A4, secondBass);
            setPageOrientation(PageOrientation.PORTRAIT, secondBass);
        }
    }
}
