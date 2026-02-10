package it.unibo.plantsfarm.controller.player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.inventario.ModelInventario;

public final class SavePlayer {

    private static final Logger LOGGER = Logger.getLogger(SavePlayer.class.getName());
    private final String fileName = System.getProperty("user.home") + File.separator + ".plantsfarm" + File.separator + "player.txt";
    private static final String PAIR_SEPARATOR = ";";
    private static final String VALUE_SEPARATOR = "=";
    private final Memory memory;
    private final ModelInventario inventario;

    public SavePlayer(final Memory memory, final ModelInventario inventario) {
        this.memory = memory;
        this.inventario = inventario;
    }

    /**
     * Save inventory items.
     */
    public void save() {
    final StringBuilder sb = new StringBuilder();

    for (final Tooltype tool : Tooltype.values()) {
        final ItemsFarm item = inventario.getItem(tool);
        if (item == null) {
            continue; // oppure salva 0 o un default
        }
        sb.append(tool.name())
          .append(VALUE_SEPARATOR)
          .append(item.getLevel())
          .append(PAIR_SEPARATOR);
    }

    try {
        memory.save(fileName, sb.toString());
    } catch (final IOException e) {
        LOGGER.log(Level.SEVERE, "Could not save player inventory", e);
    }
}
    /**
     * Load inventory items.
     */
    public void load() {
    try {
        final String data = memory.load(fileName);
        if (data == null || data.isBlank()) {
            return;
        }

        final String[] pairs = data.split(PAIR_SEPARATOR);
        for (final String pair : pairs) {
            if (!pair.contains(VALUE_SEPARATOR)) {
                continue;
            }

            final String[] parts = pair.split(VALUE_SEPARATOR);
            if (parts.length < 2) {
                continue;
            }

            try {
                final Tooltype type = Tooltype.valueOf(parts[0]);
                final int level = Integer.parseInt(parts[1]);
                inventario.loadItem(type, level);
            } catch (final IllegalArgumentException e) {
                LOGGER.log(Level.WARNING, "Invalid inventory data: " + pair, e);
            }
        }
    } catch (final IOException e) {
        LOGGER.log(Level.WARNING, "Couldn't load player inventory", e);
    }
}
}
