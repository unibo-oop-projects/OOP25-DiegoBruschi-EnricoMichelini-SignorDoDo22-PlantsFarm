package it.unibo.plantsfarm.view.inventario;

import javax.swing.JPanel;

/**
 * Inteface for update Panel in inventarioView.
 *
 */
public interface UpdatablePanel {

    /**
     * Update the view State.
     */
    void update();

    /**
     * Returns the root Swing panel of this view.
     *
     * @return JPanel
     */
    JPanel getPanel();
}