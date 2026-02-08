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

public class SaveController {

    public void saveGame(List<Soil> pod, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(pod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Soil> loadGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Soil>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nessun salvataggio trovato.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reset(String fileName) {
    File file = new File(fileName);
    if (file.exists()) {
        if (file.delete()) {
            System.out.println("Salvataggio eliminato con successo.");
        } else {
            System.err.println("Impossibile eliminare il file di salvataggio.");
        }
    }
}
}