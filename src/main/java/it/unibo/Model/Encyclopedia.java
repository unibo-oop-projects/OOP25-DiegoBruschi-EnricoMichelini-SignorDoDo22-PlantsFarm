/* ENCYCLOPEDIA
 * contains main information for each plant
 * DONE
 */

package it.unibo.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Encyclopedia {

    private final List<Plant> plants;

    public Encyclopedia() {
        this.plants = new ArrayList<>();
    }

    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }

    public void addPlant(Plant plant) {
        if (!plants.contains(plant)) {
            plants.add(plant);
        }
    }

    public int numberPlants() {
        return plants.size();
    }

    public void unlockAll() {
        for (Plant plant : this.getPlants()) {
            plant.unlock();
        }
    }

    public List<Plant> getUnlockedEdiblePlants() {
        List<Plant> unlockedEdiblePlantsList = new ArrayList<>();
        for (Plant plant : plants) {
            if(plant.isEdible() && plant.isDiscovered()){
                unlockedEdiblePlantsList.add(plant);
            }
        }
        return unlockedEdiblePlantsList;
    }

    public int getNumberUnlockedEdiblePlants() {
        int total=0;
        for (Plant plant : plants) {
            if(plant.isEdible() && plant.isDiscovered()){
                total++;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== Encyclopedia ===\n");
        for (Plant p : plants) {
            sb.append(p.getType().getDisplayName()).append("\n");
        }
        return sb.toString();
    }
}

