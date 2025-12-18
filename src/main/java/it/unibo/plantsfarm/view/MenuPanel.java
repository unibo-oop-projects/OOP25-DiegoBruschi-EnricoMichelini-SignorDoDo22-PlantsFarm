package it.unibo.plantsfarm.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents the menù panel of the game.
 */
public final class MenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int ROWS = 4;
    private static final int COLS = 1;
    private static final int GAP = 10;

    private final JButton shop;
    private final JButton storage;
    private final JButton encyclopedia;
    private final JButton exit;

    /**
     * Initializes the menu panel using the ButtonFactory.
     */
    public MenuPanel() {
        super(new GridLayout(ROWS, COLS, 0, GAP));

        this.shop = ButtonFactory.createMenuButton("SHOP");
        this.storage = ButtonFactory.createMenuButton("STORAGE");
        this.encyclopedia = ButtonFactory.createMenuButton("ENCYCLOPEDIA");
        this.exit = ButtonFactory.createMenuButton("EXIT");

        this.add(this.shop);
        this.add(this.storage);
        this.add(this.encyclopedia);
        this.add(this.exit);
    }

    /**
     * Attaches a listener to the Shop button.
     * 
     * @param listener The action to perform.
     */
    public void addShopListener(final ActionListener listener) {
        this.shop.addActionListener(listener);
    }

    /**
     * Attaches a listener to the Storage button.
     * 
     * @param listener The action to perform.
     */
    public void addStorageListener(final ActionListener listener) {
        this.storage.addActionListener(listener);
    }

    /**
     * Attaches a listener to the Encyclopedia button.
     * 
     * @param listener The action to perform.
     */
    public void addEncyclopediaListener(final ActionListener listener) {
        this.encyclopedia.addActionListener(listener);
    }

    /**
     * Attaches a listener to the Exit button.
     * 
     * @param listener The action to perform.
     */
    public void addExitListener(final ActionListener listener) {
        this.exit.addActionListener(listener);
    }
}
