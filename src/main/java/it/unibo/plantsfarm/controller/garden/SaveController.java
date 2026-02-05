package it.unibo.plantsfarm.controller.garden;

import java.io.FileOutputStream;
import java.io.IOException;
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
}
