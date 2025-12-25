package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.WindowFactory;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * Initializes the screen.
 */
public final class ShopScreen {

    private static final String TITLE = "Shop";

    private final JDialog dialog;
    private final JButton sellButton;
    private final JButton getButton;

    /**
     * Manages the view for the Shop feature.
     */
    public ShopScreen() {
        this.dialog = WindowFactory.createMenuWindow(TITLE);

        final JPanel root = new JPanel(new GridLayout(1, 2));

        final JPanel leftPanel = new JPanel();
        this.sellButton = ButtonFactory.createButton("SELL");
        leftPanel.add(this.sellButton);
        root.add(leftPanel);

        final JPanel rightPanel = new JPanel();
        this.getButton = ButtonFactory.createButton("GET");
        rightPanel.add(this.getButton);
        root.add(rightPanel);

        this.dialog.add(root);
    }

    /**
     * Sell an item.
     *
     * @param plantName The name of the plant.
     * @param price     The price.
     */
    public void addSellItem(final String plantName, final int price) {
    }

    /**
     * Sets up the SELL button action.
     *
     * @param listener The action to perform.
     */
    public void setupSellButton(final ActionListener listener) {
        this.sellButton.addActionListener(listener);
    }

    /**
     * Sets up the GET button action.
     *
     * @param cost     the cost of the plant
     * @param listener Action when clicked.
     */
    public void setupBuyButtons(final int cost, final ActionListener listener) {
        this.getButton.setText("GET (" + cost + ")");
        this.getButton.addActionListener(listener);
    }

    /**
     * Shows the dialog.
     */
    public void show() {
        this.dialog.setVisible(true);
    }
}
