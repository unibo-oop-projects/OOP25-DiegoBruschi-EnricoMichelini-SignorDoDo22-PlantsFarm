package it.unibo.plantsfarm.view.menu;

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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * View for the Shop.
 * Left: Selling panel, plant labels for requests.
 * Right: Get random plant panel.
 */
public final class ShopScreen {

    private static final String TITLE = "Shop";
    private static final String FONT_FAMILY = "SansSerif";
    private static final int GAP = 15;
    private static final int PADDING = 20;
    private static final int BUY_COST = 250;

    private static final int LEFT_GRID_ROWS = 3;
    private static final int LEFT_GRID_COLS = 1;
    private static final int GRID_H_GAP = 5;

    private static final int RIGHT_GRID_ROWS = 2;
    private static final int RIGHT_GRID_COLS = 2;

    private static final int ITEM_WIDTH = 130;
    private static final int ITEM_HEIGHT = 150;
    private static final int ICON_TEXT_GAP = 10;
    private static final int BUY_BTN_GAP = 5;

    private static final int BORDER_GRAY = 200;
    private static final Color BG_COLOR = new Color(245, 245, 220);
    private static final Color PANEL_BG = new Color(255, 255, 255, 180);

    private static final Font FONT = new Font(FONT_FAMILY, Font.BOLD, 16);

    private final JDialog screen;
    private final JPanel plantRequests;
    private final JPanel rightBoxPanel;
    private final JPanel leftRequestPanel;

    /**
     * Creates the Shop Screen.
     */
    public ShopScreen() {
        this.screen = WindowFactory.createMenuWindow(TITLE);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        final JPanel center = new JPanel(new GridLayout(1, 2, GAP, 0));
        center.setBackground(BG_COLOR);
        center.setOpaque(false);

        this.leftRequestPanel = new JPanel(new BorderLayout(0, 10));
        setupPanel(this.leftRequestPanel, "Merchant's Requests");

        this.plantRequests = new JPanel(new GridLayout(LEFT_GRID_ROWS, LEFT_GRID_COLS, 10, 10));
        this.plantRequests.setOpaque(false);
        this.plantRequests.setBorder(BorderFactory.createEmptyBorder(10, GRID_H_GAP, 10, GRID_H_GAP));

        this.leftRequestPanel.add(this.plantRequests, BorderLayout.CENTER);
        center.add(this.leftRequestPanel);

        this.rightBoxPanel = new JPanel(new GridLayout(RIGHT_GRID_ROWS, RIGHT_GRID_COLS, 10, 10));
        setupPanel(this.rightBoxPanel, "Mystery Boxes");
        center.add(this.rightBoxPanel);

        mainPanel.add(center);

        this.screen.add(mainPanel);
    }

    private void setupPanel(final JPanel panel, final String title) {
        panel.setBackground(PANEL_BG);
        final TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
            title
        );
        border.setTitleFont(FONT);
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(BorderFactory.createCompoundBorder(
            border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
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

        final ImageIcon icon = Texture.getPlantIcon(plantName);
        final String text = plantName + " x" + quantity + " (" + price + " Coins)";

        final JLabel itemLabel = new JLabel(text, icon, SwingConstants.CENTER);
        itemLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        itemLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        itemLabel.setIconTextGap(ICON_TEXT_GAP);
        itemLabel.setFont(FONT);
        itemLabel.setPreferredSize(new Dimension(ITEM_WIDTH, ITEM_HEIGHT));
        itemLabel.setBorder(BorderFactory.createLineBorder(
            new Color(BORDER_GRAY, BORDER_GRAY, BORDER_GRAY), 1)
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
     * Sets up the 4 Buy buttons in the right panel, using different rarity icons.
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

        for (int i = 0; i < 4; i++) {
            final int cost = (i + 1) * BUY_COST;
            final JButton buyButton = ButtonFactory.createButton("Buy (" + cost + ")");

            buyButton.putClientProperty("itemCost", cost);

            buyButton.setIcon(giftIcons[i]);
            buyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            buyButton.setHorizontalTextPosition(SwingConstants.CENTER);
            buyButton.setIconTextGap(BUY_BTN_GAP);
            buyButton.addActionListener(listener);
            this.rightBoxPanel.add(buyButton);
        }
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
