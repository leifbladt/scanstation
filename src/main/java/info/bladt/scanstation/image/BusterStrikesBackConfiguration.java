package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;
import info.bladt.scanstation.model.Instrument;
import info.bladt.scanstation.model.Key;

import static info.bladt.scanstation.model.Key.C;

public class BusterStrikesBackConfiguration extends Configuration {

    public BusterStrikesBackConfiguration() {
        setProcessingConfiguration(new BusterStrikesBackProcessingConfiguration());
        setExportConfiguration(new BusterStrikesBackExportConfiguration());
    }

    public static class BusterStrikesBackProcessingConfiguration extends ProcessingConfiguration {
        public BusterStrikesBackProcessingConfiguration() {
            setCrop(true);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);
            setAdjustContrast(true);

            setPageHeight(6760);
            setPageWidth(4750);
            setPaperEdgeWidth(new RemoveEdges.Width(70));

            Instrument firstBass = new Instrument("1st Bass", C);
            setCrop(firstBass, false);

            Instrument secondBass = new Instrument("2nd Bass", C);
            setCrop(secondBass, false);

            Instrument score = new Instrument("Score");
            setRotate(score, true);
            setCrop(score, false);
            setPaperEdgeWidth(new RemoveEdges.Width(75, 75, 75, 75), score);
            setRotationAngle(90, score, 1, 3, 5, 7, 9, 11, 13, 15);
            setRotationAngle(-90, score, 2, 4, 6, 8, 10, 12, 14);
        }

    }

    public static class BusterStrikesBackExportConfiguration extends ExportConfiguration {
        private BusterStrikesBackExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f);

            setPageOrientation(PageOrientation.LANDSCAPE, new Instrument("Score"));
        }
    }
}
