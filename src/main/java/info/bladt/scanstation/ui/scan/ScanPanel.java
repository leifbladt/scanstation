package info.bladt.scanstation.ui.scan;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingWorker.StateValue;

public class ScanPanel extends JPanel {

    public ScanPanel() {

        JTextField textField = new JTextField("Horn", 20);

        JPanel furtherAction = new JPanel();
        furtherAction.add(new JLabel("Weiter?"));
        furtherAction.setVisible(false);
        JLabel scanResult = new JLabel();
        JButton button = new JButton("Scan");

        ScanAction l = new ScanAction();
        l.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName()) && (StateValue.DONE.equals(evt.getNewValue()))) {
                try {
                    scanResult.setIcon(new ImageIcon(l.get().getScaledInstance(600, 800, Image.SCALE_SMOOTH)));
                    furtherAction.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button.addActionListener(l);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(button);

        add(panel, BorderLayout.PAGE_START);
        add(scanResult, BorderLayout.CENTER);
        add(furtherAction, BorderLayout.PAGE_END);
    }
}
