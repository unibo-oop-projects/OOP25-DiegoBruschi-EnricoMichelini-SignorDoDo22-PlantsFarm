package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.view.music.MusicPlayer;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.WindowFactory;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

/**
 * View for the Shop.
 */
public final class ShopScreen {

    private static final String TITLE = "Shop";
    private static final String FONT_FAMILY = "Arial";
    private static final int BUY_COST = 250;

    private static final int LEFT_GRID_ROWS = 3;
    private static final int LEFT_GRID_COLS = 1;
    private static final int RIGHT_GRID_ROWS = 2;
    private static final int RIGHT_GRID_COLS = 2;

    private static final int BUY_BTN_COUNT = 4;

    private static final double FONT_RATIO = 0.017;
    private static final double PADDING_RATIO = 0.02;
    private static final double GAP_RATIO = 0.02;
    private static final double ITEM_WIDTH_RATIO = 0.2;
    private static final double ITEM_HEIGHT_RATIO = 0.1;

    private static final int ITEM_BORDER = 200;
    private static final int PANEL_BORDER = 100;
    private static final int BORDER_WIDTH = 2;
    private static final int PANEL = 180;
    private static final int BLACK = 255;

    private static final Color BG_COLOR = new Color(245, 245, 220);
    private static final Color PANEL_BG = new Color(BLACK, BLACK, BLACK, PANEL);

    private final JDialog screen;
    private final JPanel plantRequests;
    private final JPanel rightBoxPanel;
    private final JPanel leftRequestPanel;
    private final MusicPlayer soundSystem;
    private final Font font;
    private final int itemWidth;
    private final int itemHeight;
    private final int gap;

    /**
     * Creates the Shop Screen.
     */
    public ShopScreen() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = screenSize.height;
        final int screenWidth = screenSize.width;

        this.gap = (int) (screenHeight * GAP_RATIO);
        final int padding = (int) (screenHeight * PADDING_RATIO);
        final int fontSize = (int) (screenHeight * FONT_RATIO);
        this.itemWidth = (int) (screenWidth * ITEM_WIDTH_RATIO);
        this.itemHeight = (int) (screenHeight * ITEM_HEIGHT_RATIO);
        this.font = new Font(FONT_FAMILY, Font.BOLD, fontSize);

        this.soundSystem = new MusicPlayer();
        this.screen = WindowFactory.createMenuWindow(TITLE);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        final JPanel center = new JPanel(new GridLayout(1, 2, gap, 0));
        center.setBackground(BG_COLOR);
        center.setOpaque(false);

        this.leftRequestPanel = new JPanel(new BorderLayout(0, gap));
        setupPanel(this.leftRequestPanel, "Merchant's Requests", padding);

        this.plantRequests = new JPanel(new GridLayout(LEFT_GRID_ROWS, LEFT_GRID_COLS, gap, gap));
        this.plantRequests.setOpaque(false);
        this.plantRequests.setBorder(BorderFactory.createEmptyBorder(gap, gap / 2, gap, gap / 2));

        this.leftRequestPanel.add(this.plantRequests, BorderLayout.CENTER);
        center.add(this.leftRequestPanel);

        this.rightBoxPanel = new JPanel(new GridLayout(RIGHT_GRID_ROWS, RIGHT_GRID_COLS, gap, gap));
        setupPanel(this.rightBoxPanel, "Mystery Boxes", padding);
        center.add(this.rightBoxPanel);

        mainPanel.add(center);

        this.screen.add(mainPanel);
    }

    private void setupPanel(final JPanel panel, final String title, final int padding) {
        panel.setBackground(PANEL_BG);
        final TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(PANEL_BORDER, PANEL_BORDER, PANEL_BORDER), BORDER_WIDTH),
            title
        );
        border.setTitleFont(this.font);
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(BorderFactory.createCompoundBorder(
            border,
            BorderFactory.createEmptyBorder(padding / 2, padding / 2, padding / 2, padding / 2)
        ));
    }

    /**
     * Adds a selling option to the left panel.
     *
     * @param plantName The plant name.
     * @param quantity  The quantity to sell.
     * @param price     The total price.
     */
    public void addSellItem(final String plantName, final int quantity, final int price) {
        final ImageIcon icon = Texture.getShopPlantIcon(plantName);
        final String text = plantName + " x" + quantity + " (" + price + " Coins)";

        final JLabel itemLabel = new JLabel(text, icon, SwingConstants.CENTER);

        itemLabel.setVerticalTextPosition(SwingConstants.CENTER);
        itemLabel.setHorizontalTextPosition(SwingConstants.RIGHT);

        itemLabel.setIconTextGap(this.gap);
        itemLabel.setFont(this.font);
        itemLabel.setPreferredSize(new Dimension(this.itemWidth, this.itemHeight));
        itemLabel.setBorder(BorderFactory.createLineBorder(
            new Color(ITEM_BORDER, ITEM_BORDER, ITEM_BORDER), 1)
        );

        this.plantRequests.add(itemLabel);
    }

    /**
     * Clears the current request items to allow refresh.
     */
    public void resetRequestsPanel() {
        this.plantRequests.setVisible(false);
        this.plantRequests.removeAll();
        this.plantRequests.setVisible(true);
        this.screen.repaint();
    }

    /**
     * Sets up the SELL button at the bottom of the left panel.
     *
     * @param listener The action to perform.
     */
    public void setSellButton(final ActionListener listener) {
        final JButton sellButton = ButtonFactory.createButton("SELL");
        sellButton.addActionListener(listener);

        this.leftRequestPanel.add(sellButton, BorderLayout.SOUTH);

        this.leftRequestPanel.revalidate();
        this.leftRequestPanel.repaint();
    }

    /**
     * Sets up the 4 Buy buttons in the right panel.
     *
     * @param listener Action when clicked.
     */
    public void setBuyButtons(final ActionListener listener) {
        this.rightBoxPanel.removeAll();

        final ImageIcon[] giftIcons = {
            Texture.GIFT_ICON,
            Texture.RARE_GIFT_ICON,
            Texture.EPIC_GIFT_ICON,
            Texture.LEGENDARY_GIFT_ICON,
        };

        for (int i = 0; i < BUY_BTN_COUNT; i++) {
            final int cost = (i + 1) * BUY_COST;
            final JButton buyButton = ButtonFactory.createButton("Buy (" + cost + ")");

            buyButton.putClientProperty("itemCost", cost);

            buyButton.setIcon(giftIcons[i]);
            buyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            buyButton.setHorizontalTextPosition(SwingConstants.CENTER);
            buyButton.setIconTextGap(this.gap / 2);
            buyButton.addActionListener(listener);
            this.rightBoxPanel.add(buyButton);
        }
    }

    /**
     * Enables or disables all buy buttons.
     *
     * @param enabled True to enable, false to disable.
     */
    public void setBuyButtonsEnabled(final boolean enabled) {
        for (final Component comp : this.rightBoxPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(enabled);
            }
        }
    }

    /**
     * Plays the coin sound.
     */
    public void playCoinSound() {
        this.soundSystem.playEffect("music/menuSound/coin.wav");
    }

    /**
     * Plays the mystery box sound.
     */
    public void playMisteryBoxSound() {
        this.soundSystem.playEffect("music/menuSound/box.wav");
    }

    /**
     * Shows the dialog.
     */
    public void show() {
        this.screen.setVisible(true);
    }

    /**
     * Closes the dialog.
     */
    public void close() {
        this.screen.dispose();
    }
}
