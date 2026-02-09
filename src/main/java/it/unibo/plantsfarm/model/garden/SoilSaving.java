package it.unibo.plantsfarm.model.garden;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import it.unibo.plantsfarm.model.tiles.Soil;

public final class SoilSaving {

    private String userPath = System.getProperty("user.home");
    private String saveDirectory = userPath + File.separator + ".plantsfarm";
    private String fileName = saveDirectory + File.separator + "plants";

    public void saveGame(final List<Soil> pod) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            oos.writeObject(pod);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Soil> loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileName))) {
            return (List<Soil>) ois.readObject();
        } catch (final FileNotFoundException e) {
            System.out.println("Nessun salvataggio trovato.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reset() {
        final File file = new File(this.fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Salvataggio eliminato con successo.");
            } else {
                System.err.println("Impossibile eliminare il file di salvataggio.");
            }
        }
    }
}
