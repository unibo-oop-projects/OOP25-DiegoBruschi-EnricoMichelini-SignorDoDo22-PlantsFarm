package it.unibo.plantsfarm.view.inventario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.inventario.UpdatablePanels.MainItemsViewPanel;


/**
 * Dialog that shows the experience of each item/tool and allows upgrading them.
 */
public final class UpgradeItemsView extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;
    private static final String TITLE = " ITEMS EXPERIENCE AND STATS PLAYER ";
    private final UpdatablePanel mainPanel;
    private final ImplViewGamePanel gamePanel;

    /**
     * Creates a new dialog for items experience.
     *
     * @param gamePanel the game panel that will receive focus when this dialog closes
     *
     * @param controllerInventario for review the item of the player.
     *
     */
    public UpgradeItemsView(final ImplViewGamePanel gamePanel, final ControllerInventario controllerInventario) {
        super();
        this.setTitle(TITLE);
        this.gamePanel = gamePanel;
        this.mainPanel = new MainItemsViewPanel(controllerInventario);
        this.add(mainPanel.getPanel());
        this.setResizable(false);
        this.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        this.setLocationRelativeTo(gamePanel);
        this.setModal(true);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(final WindowEvent e) {
                restoreFocus();
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                restoreFocus();
            }
        });
    }

    private void restoreFocus() {
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }


    public void updateAllItemsPanel(){
        mainPanel.update();
    }
}
