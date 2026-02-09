package it.unibo.plantsfarm.view.inventario.updatablepanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.view.inventario.UpdatablePanel;

/**
 * Display all the progress bar for each item in inventory.
 */
public final class ItemsViewBarProgress implements UpdatablePanel {

    private static final int GRID_ROWS = 3;
    private static final int GRID_COLS = 1;

    private static final Color BAR_BACKGROUND = new Color(30, 30, 30);
    private static final Color BAR_FOREGROUND = new Color(90, 200, 120);

    private static final String FONT_NAME = "Monospaced";
    private static final int FONT_SIZE = 12;
    private static final Font BAR_FONT = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);

    private final LayoutManager layout = new GridLayout(GRID_ROWS, GRID_COLS);
    private final Map<Tooltype, JProgressBar> progressBarMap = new EnumMap<>(Tooltype.class);

    private final ControllerInventario controllerInventario;
    private final JPanel panel = new JPanel();

    /**
     * Creates a new view that displays the progress bars of the inventory items.
     *
     * @param controllerInventario the inventory controller used to retrieve item data
     */
    public ItemsViewBarProgress(final ControllerInventario controllerInventario) {
        this.controllerInventario = Objects.requireNonNull(controllerInventario);
        panel.setLayout(layout);
        createProgressBars();
    }

    private void createProgressBars() {
        for (final Tooltype tool : Tooltype.values()) {
            final JProgressBar bar = new JProgressBar();
            bar.setBackground(BAR_BACKGROUND);
            bar.setForeground(BAR_FOREGROUND);
            bar.setOpaque(true);
            bar.setFont(BAR_FONT);
            bar.setMinimum(0);

            progressBarMap.put(tool, bar);
            panel.add(bar);
        }
    }

    @Override
    public void update() {
        final var inv = controllerInventario.getInventoryClone();
        for (final Tooltype tool : Tooltype.values()) {
            final JProgressBar bar = progressBarMap.get(tool);
            final ItemsFarm item = inv.get(tool);

            bar.setMaximum(item.getExperienceForLevel());
            bar.setValue(item.getExperience());
        }
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
