package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.WindowFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public final class EncyclopediaScreen {

    private static final String TITLE = "Encyclopedia";
    private static final int COLS = 4;
    private static final int GAP = 10;

    private final JDialog dialog;
    private final JPanel gridPanel;

    public EncyclopediaScreen() {
        this.dialog = WindowFactory.createMenuWindow(TITLE);
        this.gridPanel = new JPanel(new GridLayout(0, COLS, GAP, GAP));
        this.dialog.add(this.gridPanel, BorderLayout.CENTER);
    }

    /**
     * Shows the window with plant buttons.
     * 
     * @param plants The list of Plant objects.
     */
    public void show(final List<Plant> plants) {
        this.gridPanel.removeAll();

        for (final Plant plant : plants) {
            final JButton button = ButtonFactory.createMenuButton(plant.getName());
            button.addActionListener(e -> {
                System.out.println("Selected Plant: " + plant.getName());
            });

            this.gridPanel.add(button);
        }

        this.gridPanel.revalidate();
        this.gridPanel.repaint();
        this.dialog.setVisible(true);
    }
}
