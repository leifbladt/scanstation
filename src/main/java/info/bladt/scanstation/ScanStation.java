package info.bladt.scanstation;

import info.bladt.scanstation.ui.scan.ScanPanel;

import javax.swing.*;

public class ScanStation {
    private static void createAndShowGUI() {
        JFrame f = new JFrame("ScanStation");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(1024, 768);


        JPanel scanPanel = new ScanPanel();
        JPanel editPanel = new JPanel();

        editPanel.add(new JLabel("bearbeiten"));

        JTabbedPane scanPane = new JTabbedPane();
        scanPane.addTab("Scan", scanPanel);
        scanPane.addTab("Bearbeitung", editPanel);

        f.getContentPane().add(scanPane);
        f.setVisible(true);
    }

    public static void main(String... unused) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
