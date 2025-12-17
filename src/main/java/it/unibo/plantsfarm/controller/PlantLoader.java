package it.unibo.plantsfarm.controller;

import it.unibo.plantsfarm.model.Plant;
import it.unibo.plantsfarm.model.PlantFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for loading the specific plant catalog.
 */
public class PlantLoader {

    /**
     * Loads the complete list of available plants.
     *
     * @return A list of initialized Plant objects.
     */
    public final List<Plant> loadPlants() {
        final List<Plant> plants = new ArrayList<>();

        //EDIBLE PLANTS
        plants.add(PlantFactory.createPlant("Tomato", true, PlantFactory.GROWTH_M, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Carrot", true, PlantFactory.GROWTH_S, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Corn", true, PlantFactory.GROWTH_MM, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Onion", true, PlantFactory.GROWTH_S, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Potato", true, PlantFactory.GROWTH_M, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Radish", true, PlantFactory.GROWTH_SS, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Zucchini", true, PlantFactory.GROWTH_S, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Pepper", true, PlantFactory.GROWTH_M, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Eggplant", true, PlantFactory.GROWTH_M, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Pumpkin", true, PlantFactory.GROWTH_MM, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Cherry", true, PlantFactory.GROWTH_MM, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Apple", true, PlantFactory.GROWTH_M, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Watermelon", true, PlantFactory.GROWTH_MM, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Fig", true, PlantFactory.GROWTH_M, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Mango", true, PlantFactory.GROWTH_L, PlantFactory.EPIC));
        plants.add(PlantFactory.createPlant("Avocado", true, PlantFactory.GROWTH_L, PlantFactory.EPIC));
        plants.add(PlantFactory.createPlant("BuddhaHand", true, PlantFactory.GROWTH_LL, PlantFactory.EPIC));
        plants.add(PlantFactory.createPlant("Dragonfruit", true, PlantFactory.GROWTH_L, PlantFactory.EPIC));

        //ORNAMENTAL PLANTS
        plants.add(PlantFactory.createPlant("Monstera", false, PlantFactory.GROWTH_M, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Orchid", false, PlantFactory.GROWTH_S, PlantFactory.EPIC));
        plants.add(PlantFactory.createPlant("Begonia", false, PlantFactory.GROWTH_M, PlantFactory.COMMON));
        plants.add(PlantFactory.createPlant("Hibiscus", false, PlantFactory.GROWTH_M, PlantFactory.RARE));
        plants.add(PlantFactory.createPlant("Nepenthes", false, PlantFactory.GROWTH_M, PlantFactory.EPIC));
        plants.add(PlantFactory.createPlant("Strelitzia", false, PlantFactory.GROWTH_M, PlantFactory.RARE));

        return plants;
    }
}
