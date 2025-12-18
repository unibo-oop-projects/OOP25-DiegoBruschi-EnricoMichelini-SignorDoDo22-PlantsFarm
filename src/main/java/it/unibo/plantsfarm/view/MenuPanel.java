package it.unibo.plantsfarm.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents the menù panel of the game.
 */
public final class MenuPanel extends JPanel {

    private static final int ROWS = 4;
    private static final int COLS = 1;
    private static final int GAP = 10;

    /**
     * initializes the menu panel with Shop, Storage, Encyclopedia, and Exit buttons.
     */
    public MenuPanel() {

        super(new GridLayout(ROWS, COLS, 0, GAP));

        final JButton shop = new JButton("SHOP");
        final JButton storage = new JButton("STORAGE");
        final JButton encyclopedia = new JButton("ENCYCLOPEDIA");
        final JButton exit = new JButton("EXIT");

        this.add(shop);
        this.add(storage);
        this.add(encyclopedia);
        this.add(exit);

        exit.addActionListener(e -> System.exit(0));
    }
}
