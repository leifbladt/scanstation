module scanstation {
    requires java.desktop;
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires org.apache.pdfbox;
    requires jfreesane;
    requires org.apache.logging.log4j;

    opens scanstation.info.bladt.scanstation.controller to javafx.fxml;
    opens scanstation.info.bladt.scanstation.image.processing;
    opens scanstation.info.bladt.scanstation.model to scanstation.test;
//    opens scanstation.info.bladt.scanstation.image.processing to org.junit.platform.commons;
//    opens scanstation.info.bladt.scanstation.model to org.junit.platform.commons;

    exports scanstation.info.bladt.scanstation;
}