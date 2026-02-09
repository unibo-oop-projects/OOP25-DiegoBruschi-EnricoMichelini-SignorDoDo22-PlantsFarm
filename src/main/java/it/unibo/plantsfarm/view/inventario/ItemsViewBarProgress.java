package it.unibo.plantsfarm.view.inventario;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;

public class ItemsViewBarProgress  implements UpdatablePanel {

    private final Map<Tooltype, JProgressBar> progressBarMap = new LinkedHashMap<>();
    private static final int COLOR_R = 30;
    private static final int COLOR_G = 30;
    private static final int COLOR_B = 30;
    private static final int GRID_ROWS = 3;
    private static final int GRID_COLS = 1;
    private static final int BAR_R = 90;
    private static final int BAR_G = 200;
    private static final int BAR_B = 120;
    private static final String FONT_NAME = "Monospaced";
    private static final int FONT_SIZE = 12;
    private static final Color COLOR_BAR = new Color(COLOR_R, COLOR_G, COLOR_B);
    private static final Color BAR_FOREGROUND = new Color(BAR_R, BAR_G, BAR_B);

    private final LayoutManager layoutButtonImages = new GridLayout(GRID_ROWS, GRID_COLS);
    private final ControllerInventario controllerInventario;
    private final JPanel panel;

    public ItemsViewBarProgress(ControllerInventario controllerInventario) {
        this.controllerInventario = controllerInventario;
        this.panel = new JPanel();
        panel.setLayout(layoutButtonImages);
        createProgressBar();
    }

    /**
     * Creates the progress bars for each tool.
     */
    public void createProgressBar() {
        for (final Tooltype tool : Tooltype.values()) {
            final JProgressBar progressBar = new JProgressBar();
            progressBarMap.put(tool, progressBar);
            panel.add(progressBar);
        }
    }


    /**
     *
     */
    @Override
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
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

}
