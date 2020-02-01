open module scanstation {
    requires java.desktop;
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires org.apache.pdfbox;
    requires jfreesane;
    requires org.apache.logging.log4j;

    requires org.junit.jupiter.api;

    exports scanstation.info.bladt.scanstation;
}