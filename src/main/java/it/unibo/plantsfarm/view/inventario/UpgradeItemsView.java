package it.unibo.plantsfarm.view.inventario;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDialog;

import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

/**
 * Dialog that shows the experience of each item/tool and allows upgrading them.
 */
public final class UpgradeItemsView extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;
    private static final String TITLE = " ITEMS EXPERIENCE AND STATS PLAYER ";
    private final ControllerInventario controllerInventario;
    private final UpdatablePanel panelProgressBar;
    private final UpdatablePanel panelViewImageItems;
    private final UpdatablePanel panelItemsUpgradeButtons;
    private final ImplViewGamePanel gamePanel;
    private final List<UpdatablePanel> panelsComposition;

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
        this.controllerInventario = controllerInventario;

        this.panelProgressBar = new ItemsViewBarProgress(controllerInventario);
        this.panelViewImageItems = new ItemsViewImageItem(controllerInventario) ;
        this.panelItemsUpgradeButtons = new ItemsViewButtonUpgrade(controllerInventario);
        this.panelsComposition = new LinkedList<>(List.of(panelItemsUpgradeButtons, panelProgressBar, panelViewImageItems));

        this.setLayout(new BorderLayout());
        this.add(panelItemsUpgradeButtons.getPanel(), BorderLayout.EAST);
        this.add(panelViewImageItems.getPanel(), BorderLayout.WEST);
        this.add(panelProgressBar.getPanel(), BorderLayout.CENTER);

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
        for (UpdatablePanel panelUpdatable : panelsComposition) {
            panelUpdatable.update();
        }
    }
}
