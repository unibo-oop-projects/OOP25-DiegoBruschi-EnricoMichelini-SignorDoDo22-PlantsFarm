package it.unibo.plantsfarm.view.inventario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import it.unibo.plantsfarm.controller.inventario.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

/**
 * Dialog that shows the experience of each item/tool and allows upgrading them.
 */
public final class UpgradeItemsView extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;

    private static final int GRID_ROWS = 3;
    private static final int GRID_COLS = 1;

    private static final int COLOR_R = 30;
    private static final int COLOR_G = 30;
    private static final int COLOR_B = 30;

    private static final int BAR_R = 90;
    private static final int BAR_G = 200;
    private static final int BAR_B = 120;

    private static final int FONT_SIZE = 12;

    private static final Color COLOR_BAR = new Color(COLOR_R, COLOR_G, COLOR_B);
    private static final Color BAR_FOREGROUND = new Color(BAR_R, BAR_G, BAR_B);

    private static final String TITLE = " ITEMS EXPERIENCE ";
    private static final String UPGRADE_TEXT = " UPGRADE ";
    private static final String FONT_NAME = "Monospaced";

    private ControllerInventario controllerInventario;

    private final LayoutManager layoutButtonImages = new GridLayout(GRID_ROWS, GRID_COLS);
    private final ImplViewGamePanel gamePanel;

    private final JPanel panelIntern;
    private final JPanel buttonImages;
    private final JPanel progressItems;
    private final JPanel buttonsActionsItem;

    private final Map<Tooltype, JProgressBar> progressBarMap = new LinkedHashMap<>();
    private final Map<Tooltype, JButton> progressButtonUpgradeMap = new LinkedHashMap<>();

    /**
     * Creates a new dialog for items experience.
     *
     * @param gamePanel the game panel that will receive focus when this dialog closes
     */
    public UpgradeItemsView(final ImplViewGamePanel gamePanel) {
        super();
        this.setTitle(TITLE);
        this.gamePanel = gamePanel;

        this.setResizable(false);
        this.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        this.setLocationRelativeTo(gamePanel);
        this.setModal(true);

        this.panelIntern = new JPanel();
        this.panelIntern.setLayout(new BorderLayout());

        this.buttonImages = new JPanel();
        this.buttonImages.setLayout(layoutButtonImages);

        this.progressItems = new JPanel();
        this.progressItems.setLayout(layoutButtonImages);

        this.buttonsActionsItem = new JPanel();
        this.buttonsActionsItem.setLayout(layoutButtonImages);

        createProgressBar();
        createButtonItemsAction();
        createItemButton();

        this.panelIntern.add(buttonImages, BorderLayout.WEST);
        this.panelIntern.add(buttonsActionsItem, BorderLayout.EAST);
        this.panelIntern.add(progressItems, BorderLayout.CENTER);

        this.add(panelIntern);

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

    /**
     * Creates the disabled buttons that display the tool names.
     */
    public void createItemButton() {
        for (final Tooltype tool : Tooltype.values()) {
            final JButton button = new JButton(" ");
            button.setText(tool.name());
            button.setEnabled(false);
            buttonImages.add(button);
        }
    }

    /**
     * Creates the upgrade action buttons for each tool.
     */
    public void createButtonItemsAction() {
        for (final Tooltype tool : Tooltype.values()) {
            final JButton upgrade = new JButton(UPGRADE_TEXT);
            upgrade.addActionListener(e -> {
                if (controllerInventario.isUpgredable(tool)) {
                    controllerInventario.pressUpgradeItem(tool);
                }
            });
            progressButtonUpgradeMap.put(tool, upgrade);
            buttonsActionsItem.add(upgrade);
        }
    }

    /**
     * Creates the progress bars for each tool.
     */
    public void createProgressBar() {
        for (final Tooltype tool : Tooltype.values()) {
            final JProgressBar progressBar = new JProgressBar();
            progressBarMap.put(tool, progressBar);
            progressItems.add(progressBar);
        }
    }

    /**
     * Sets the inventory controller used by this view.
     *
     * @param controller the inventory controller
     */
    public void setControllerInventory(final ControllerInventario controller) {
        this.controllerInventario = controller;
    }

    /**
     * Updates progress bars and enables/disables upgrade buttons.
     */
    public void update() {
        if (controllerInventario == null) {
            return;
        }

        for (final Tooltype tool : Tooltype.values()) {
            final JProgressBar bar = progressBarMap.get(tool);

            bar.setBackground(COLOR_BAR);
            bar.setForeground(BAR_FOREGROUND);
            bar.setOpaque(true);
            bar.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));

            final ItemsFarm item = controllerInventario.getInventoryClone().get(tool);
            bar.setMinimum(0);
            bar.setMaximum(item.getExperienceForLevel());
            bar.setValue(item.getExperience());
        }

        for (final Tooltype tool : Tooltype.values()) {
            final JButton button = progressButtonUpgradeMap.get(tool);
            button.setEnabled(controllerInventario.isUpgredable(tool));
        }
    }
}

