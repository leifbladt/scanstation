package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;
import info.bladt.scanstation.model.Instrument;

import static info.bladt.scanstation.model.Key.C;

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

            setImageHeight(6052);
            setImageWidth(4272);
            setPaperEdgeWidth(new RemoveEdges.Width(70));

            Instrument firstBass = new Instrument("1st Bass", C);
            setCrop(firstBass, false);

            Instrument secondBass = new Instrument("2nd Bass", C);
            setCrop(secondBass, false);
        }

    }

    public static class SoundsOfSousaExportConfiguration extends ExportConfiguration {
        private SoundsOfSousaExportConfiguration() {
            setPageSize(PageSize.DIN_A4);
            setPageOrientation(PageOrientation.PORTRAIT);
            setScaleToFit(false);
            setScale(72 / 600f * 1.1f);

            Instrument firstBass = new Instrument("1st Bass", C);
            setScale(72 / 600f, firstBass);

            Instrument secondBass = new Instrument("2nd Bass", C);
            setScale(72 / 600f, secondBass);
        }
    }
}
