package it.unibo.plantsfarm.view;

import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.TextureUtils;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Represents the view for selecting seeds to plant.
 * It adapts its size based on the screen resolution.
 */
public final class SeedView {

    private static final String TITLE_EDIBLE = "Edible Seeds";
    private static final String TITLE_ORNAMENTAL = "Ornamental Plants";
    private static final Color BG_COLOR = new Color(245, 245, 220);

    private static final int GRID_COLS = 3;
    private static final int VISIBLE_ROWS = 3;

    private static final double ICON_SCALE = 0.10;
    private static final double GAP_SCALE = 0.005;
    private static final double PADDING_SCALE = 0.01;

    private final JDialog dialog;
    private final JPanel gridPanel;
    private final int iconSize;

    /**
     * Creates a new SeedView.
     *
     * @param isEdible True to show edible plants, false for ornamental ones.
     */
    public SeedView(final boolean isEdible) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = screenSize.height;

        this.iconSize = (int) (screenHeight * ICON_SCALE);
        final int gap = (int) (screenHeight * GAP_SCALE);
        final int padding = (int) (screenHeight * PADDING_SCALE);

        this.dialog = new JDialog();
        this.dialog.setTitle(isEdible ? TITLE_EDIBLE : TITLE_ORNAMENTAL);
        this.dialog.setModal(true);
        this.dialog.setResizable(false);

        this.dialog.getRootPane().registerKeyboardAction(e -> close(),
            KeyStroke.getKeyStroke(KeyEvent.VK_P, 0),
            JPanel.WHEN_IN_FOCUSED_WINDOW
        );

        this.gridPanel = new JPanel();
        this.gridPanel.setLayout(new GridLayout(0, GRID_COLS, gap, gap));
        this.gridPanel.setBackground(BG_COLOR);
        this.gridPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        final JScrollPane scrollPane = new JScrollPane(this.gridPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        if (isEdible) {
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        } else {
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        }

        final int contentSize = (this.iconSize * VISIBLE_ROWS) + (gap * (VISIBLE_ROWS - 1)) + (padding * 2);

        int scrollBarWidth = 0;
        if (isEdible) {
            scrollBarWidth = scrollPane.getVerticalScrollBar().getPreferredSize().width;
        }

        scrollPane.setPreferredSize(new Dimension(contentSize + scrollBarWidth, contentSize));

        this.dialog.add(scrollPane);
        this.dialog.pack();
        this.dialog.setLocationRelativeTo(null);
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

        if (icon.getIconWidth() != this.iconSize) {
            final Image img = icon.getImage().getScaledInstance(this.iconSize, this.iconSize, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }

        if (!isDiscovered) {
            icon = TextureUtils.createLockedIcon(icon);
        }
        final JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(this.iconSize, this.iconSize));
        button.addActionListener(listener);
        this.gridPanel.add(button);
    }

    /**
     * Shows the dialog.
     */
    public void show() {
        this.dialog.setVisible(true);
    }

    /**
     * Closes the dialog.
     */
    public void close() {
        this.dialog.dispose();
    }
}
