package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;
import info.bladt.scanstation.model.Instrument;

import static info.bladt.scanstation.model.Key.B_FLAT;
import static info.bladt.scanstation.model.Key.E_FLAT;

public class PuttingOnTheRitzConfiguration extends Configuration {

    public PuttingOnTheRitzConfiguration() {
        setProcessingConfiguration(new PuttingOnTheRitzProcessingConfiguration());
        setExportConfiguration(new PuttingOnTheRitzExportConfiguration());
    }

    public static class PuttingOnTheRitzProcessingConfiguration extends ProcessingConfiguration {
        public PuttingOnTheRitzProcessingConfiguration() {
            setCrop(true);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPageHeight(6700);
            setPageWidth(4800);
            setPaperEdgeWidth(new RemoveEdges.Width(20, 70, 70, 70));

            Instrument firstBass = new Instrument("1st Bass", E_FLAT);
            setPageHeight(6082, firstBass);
            setPageWidth(4292, firstBass);
            setPaperEdgeWidth(new RemoveEdges.Width(70, 70, 70, 70), firstBass);

            Instrument secondBass = new Instrument("2nd Bass", B_FLAT);
            setPageHeight(6082, secondBass);
            setPageWidth(4292, secondBass);
            setPaperEdgeWidth(new RemoveEdges.Width(70, 70, 70, 70), secondBass);

            Instrument score = new Instrument("Score");
            setRotate(score, true);
            setCrop(score, false);
            setPaperEdgeWidth(new RemoveEdges.Width(75, 250, 75, 75), score);
            setRotationAngle(-90, score, 1, 3, 5, 7, 9, 11, 13, 15);
            setRotationAngle(90, score, 2, 4, 6, 8, 10, 12, 14, 16);

        }

    }

    public static class PuttingOnTheRitzExportConfiguration extends ExportConfiguration {
        private PuttingOnTheRitzExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f);

            setScale(72 / 600f * 1.1f, new Instrument("1st Bass", E_FLAT));
            setScale(72 / 600f * 1.1f, new Instrument("2nd Bass", B_FLAT));

            setPageOrientation(PageOrientation.LANDSCAPE, new Instrument("Score"));
        }
    }
}
