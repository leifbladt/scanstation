package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;
import info.bladt.scanstation.image.processing.RemoveEdges;
import info.bladt.scanstation.model.Instrument;

import static info.bladt.scanstation.model.Key.B_FLAT;
import static info.bladt.scanstation.model.Key.E_FLAT;

public class PuttingOnTheRitzConfiguration extends Configuration {

    public PuttingOnTheRitzConfiguration() {
        setProcessingConfiguration(new FlowerdaleProcessingConfiguration());
        setExportConfiguration(new FlowerdaleExportConfiguration());
    }

    public static class FlowerdaleProcessingConfiguration extends ProcessingConfiguration {
        public FlowerdaleProcessingConfiguration() {
            setCrop(true);
            setRotate(false);
            setDeskew(true);
            setRemoveEdges(true);

            setPageHeight(6700);
            setPageWidth(4800);
            setPaperEdgeWidth(new RemoveEdges.Width(20, 70, 70, 70));

            setPageHeight(6082, new Instrument("1st Bass", E_FLAT));
            setPageWidth(4292, new Instrument("1st Bass", E_FLAT));
            setPaperEdgeWidth(new RemoveEdges.Width(70, 70, 70, 70), new Instrument("1st Bass", E_FLAT));

            setPageHeight(6082, new Instrument("2nd Bass", B_FLAT));
            setPageWidth(4292, new Instrument("2nd Bass", B_FLAT));
            setPaperEdgeWidth(new RemoveEdges.Width(70, 70, 70, 70), new Instrument("2nd Bass", B_FLAT));

            setRotate(new Instrument("Score"), true);
            setCrop(new Instrument("Score"), false);
            setPaperEdgeWidth(new RemoveEdges.Width(75, 250, 75, 75), new Instrument("Score"));
            setRotationAngle(-90, new Instrument("Score"), 1, 3, 5, 7, 9, 11, 13, 15);
            setRotationAngle(90, new Instrument("Score"), 2, 4, 6, 8, 10, 12, 14, 16);

        }

    }

    public static class FlowerdaleExportConfiguration extends ExportConfiguration {
        private FlowerdaleExportConfiguration() {
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
