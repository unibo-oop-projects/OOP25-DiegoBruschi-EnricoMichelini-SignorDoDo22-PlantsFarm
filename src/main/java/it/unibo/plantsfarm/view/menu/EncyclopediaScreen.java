package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.WindowFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Manages the view for the Encyclopedia feature.
 */
public final class EncyclopediaScreen {

    private static final String TITLE = "Encyclopedia";
    private static final int COLS = 3;
    private static final int GAP = 10;
    private static final int PADDING = 20;
    private static final int COLOR_R = 245;
    private static final int COLOR_G = 245;
    private static final int COLOR_B = 220;
    private static final int FONT_SIZE_TITLE = 24;
    private static final int FONT_SIZE_DESC = 16;

    private final JDialog dialog;
    private final JPanel gridPanel;
    private final JPanel detailsPanel;

    private final JLabel plantNameLabel;
    private final JTextArea plantDescriptionArea;

    /**
     * Initializes the encyclopedia window.
     */
    public EncyclopediaScreen() {

        this.dialog = WindowFactory.createMenuWindow(TITLE);
        this.dialog.setLayout(new GridLayout(1, 2));

        this.gridPanel = new JPanel(new GridLayout(0, COLS, GAP, GAP));
        this.gridPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        this.detailsPanel = new JPanel(new BorderLayout(GAP, GAP));
        this.detailsPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.detailsPanel.setBackground(new Color(COLOR_R, COLOR_G, COLOR_B));

        this.plantNameLabel = new JLabel("Select a Plant");
        this.plantNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.plantNameLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));

        this.plantDescriptionArea = new JTextArea("Click on a plant to see details here.");
        this.plantDescriptionArea.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_DESC));

        this.detailsPanel.add(this.plantNameLabel, BorderLayout.NORTH);
        this.detailsPanel.add(this.plantDescriptionArea, BorderLayout.CENTER);
        this.dialog.add(this.gridPanel);
        this.dialog.add(this.detailsPanel);
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

            button.addActionListener(e -> updateDetails(plant));

            this.gridPanel.add(button);
        }

        if (!plants.isEmpty()) {
            updateDetails(plants.get(0));
        }

        this.gridPanel.revalidate();
        this.gridPanel.repaint();
        this.dialog.setVisible(true);
    }

    /**
     * Update the current plant description.
     *
     * @param plant the plant selected.
     */
    private void updateDetails(final Plant plant) {
        this.plantNameLabel.setText(plant.getName());
        this.plantDescriptionArea.setText(plant.toString());
    }
}
