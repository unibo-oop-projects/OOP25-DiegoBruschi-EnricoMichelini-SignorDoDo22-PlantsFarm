package it.unibo.plantsfarm.view.menu;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;

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
        super(new GridLayout(ROWS, COLS, GAP, GAP));

        this.shop = ButtonFactory.createMenuButton(Texture.SHOP_ICON);
        this.storage = ButtonFactory.createMenuButton(Texture.STORAGE_ICON);
        this.encyclopedia = ButtonFactory.createMenuButton(Texture.ENCYCLOPEDIA_ICON);
        this.exit = ButtonFactory.createMenuButton(Texture.SETTINGS_ICON);

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
