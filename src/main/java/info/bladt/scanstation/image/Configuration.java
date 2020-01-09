package info.bladt.scanstation.image;

import info.bladt.scanstation.image.export.ExportConfiguration;
import info.bladt.scanstation.image.processing.ProcessingConfiguration;

public class Configuration {

    private ProcessingConfiguration processingConfiguration;

    private ExportConfiguration exportConfiguration;

    public ProcessingConfiguration getProcessingConfiguration() {
        return processingConfiguration;
    }

    public void setProcessingConfiguration(ProcessingConfiguration processingConfiguration) {
        this.processingConfiguration = processingConfiguration;
    }

    public ExportConfiguration getExportConfiguration() {
        return exportConfiguration;
    }

    public void setExportConfiguration(ExportConfiguration exportConfiguration) {
        this.exportConfiguration = exportConfiguration;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "processingConfiguration=" + processingConfiguration +
                ", exportConfiguration=" + exportConfiguration +
                '}';
    }
}
