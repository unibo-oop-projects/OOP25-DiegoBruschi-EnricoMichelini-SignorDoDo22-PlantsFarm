package it.unibo.plantsfarm.view;

import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.TextureUtils;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public final class SeedView {

    private static final String TITLE_EDIBLE = "Edible Seeds";
    private static final String TITLE_ORNAMENTAL = "Ornamental Plants";
    private static final int WIDTH = 380;
    private static final int HEIGHT = 300;
    private static final int ICON_SIZE = 50;
    private static final Color BG_COLOR = new Color(245, 245, 220);
    private static final int GRID_COLS = 5;
    private static final int GAP = 5;

    private final JDialog dialog;
    private final JPanel gridPanel;

    public SeedView(final boolean isEdible) {
        this.dialog = new JDialog();
        this.dialog.setTitle(isEdible ? TITLE_EDIBLE : TITLE_ORNAMENTAL);
        this.dialog.setModal(true);
        this.dialog.setSize(WIDTH, HEIGHT);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setResizable(false);

        this.dialog.getRootPane().registerKeyboardAction(e -> close(),
            KeyStroke.getKeyStroke(KeyEvent.VK_P, 0),
            JPanel.WHEN_IN_FOCUSED_WINDOW
        );

        this.gridPanel = new JPanel();
        this.gridPanel.setLayout(new GridLayout(0, GRID_COLS, GAP, GAP));
        this.gridPanel.setBackground(BG_COLOR);
        this.gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.dialog.add(this.gridPanel);
    }

    /**
     * Adds a plant button to the grid.
     * 
     * @param plantName     The name of the plant.
     * @param isDiscovered  If false, the icon will be darkened.
     * @param listener      The action to perform on click.
     */
    public void addPlantButton(final String plantName, final boolean isDiscovered, final ActionListener listener) {
        ImageIcon icon = Texture.getPlantIcon(plantName);
        if (!isDiscovered) {
            icon = TextureUtils.createLockedIcon(icon);
        }
        final JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
        button.addActionListener(listener);
        this.gridPanel.add(button);
    }

    public void show() {
        this.dialog.setVisible(true);
    }

    public void close() {
        this.dialog.dispose();
    }
}
