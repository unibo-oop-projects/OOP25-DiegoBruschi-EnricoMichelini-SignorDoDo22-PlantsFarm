package it.unibo.plantsfarm.model.garden;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unibo.plantsfarm.model.tiles.Soil;

public final class SoilSaving {

    private final String fileName = System.getProperty("user.home") + File.separator + ".plantsfarm" + File.separator + "plants";
    private static final Logger LOGGER = Logger.getLogger(SoilSaving.class.getName());

    public void saveGame(final List<Soil> pod) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            oos.writeObject(pod);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il salvataggio dei soils.", e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Soil> loadGame() {
        final File file = new File(this.fileName);

        if (!file.exists()) {
            LOGGER.log(Level.INFO, "Nessun salvataggio trovato.");
            return List.of();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileName))) {
            return (List<Soil>) ois.readObject();
        } catch (final FileNotFoundException e) {
            LOGGER.log(Level.INFO, "Nessun file trovato.", e);
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il caricamento dei soils.", e);
        }
        return List.of();
    }

    public void reset() {
        final File file = new File(this.fileName);
        if (file.exists()) {
            if (file.delete()) {
                LOGGER.log(Level.INFO, "Salvataggio eliminato con successo.");
            } else {
                LOGGER.log(Level.SEVERE, "Impossibile eliminare il salvataggio.");
            }
        }
    }
}
