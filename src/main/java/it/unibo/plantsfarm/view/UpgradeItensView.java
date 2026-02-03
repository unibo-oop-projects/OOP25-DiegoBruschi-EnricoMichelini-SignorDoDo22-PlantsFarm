package it.unibo.plantsfarm.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class UpgradeItensView extends JDialog {

    private final JPanel gamePanel;

    public UpgradeItensView(final JPanel gamePanel) {
        super();
        this.gamePanel = gamePanel;

        this.setResizable(false);
        this.setSize(400, 400);
        this.setLocationRelativeTo(gamePanel);
        this.setModal(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(final WindowEvent e) {
                restoreFocus();
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                restoreFocus();
            }
        });
    }

    private void restoreFocus() {
        gamePanel.requestFocusInWindow();
        gamePanel.setFocusable(true);
    }
}
