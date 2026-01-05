package it.unibo;

import javax.swing.JFrame;

import it.unibo.GamePanel.ImplControllerGamePanel;

import it.unibo.Model.EdiblePlant;
import it.unibo.Model.Encyclopedia;
import it.unibo.Model.Plant;
import it.unibo.Model.PlantType;
import it.unibo.Model.Storage;
import it.unibo.Visual.MainScreen;
import it.unibo.Controller.MainScreenController;


public class Main {
    
 public static void main(String[] args) {
    
    JFrame frame =  new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700,700);
    frame.setVisible(true);
    ImplControllerGamePanel controll = new ImplControllerGamePanel();
    frame.add(controll.getView());
   controll.start();
   int mediumValue = 4;

        //EDIBLE PLANTS
        //COMMON
        EdiblePlant tomato = new EdiblePlant(PlantType.TOMATO, 5, 2, 5, "Common");
        EdiblePlant carrot = new EdiblePlant(PlantType.CARROT, 3, 1, 3, "Common");
        EdiblePlant corn = new EdiblePlant(PlantType.CORN, 5, 3, 6, "Common");
        EdiblePlant onion = new EdiblePlant(PlantType.ONION, 3, 1, 3, "Common");
        EdiblePlant potato = new EdiblePlant(PlantType.POTATO, 3, 2, 5, "Common");
        EdiblePlant radish = new EdiblePlant(PlantType.RADISH, 3, 1, 2, "Common");
        EdiblePlant zucchini = new EdiblePlant(PlantType.ZUCCHINI, mediumValue, 1, 3, "Common");
        EdiblePlant pepper = new EdiblePlant(PlantType.PEPPER, 5, 1, 4, "Common");

        //RARE
        EdiblePlant eggplant = new EdiblePlant(PlantType.EGGPLANT, mediumValue, 2, 5, "Rare");
        EdiblePlant pumpkin = new EdiblePlant(PlantType.PUMPKIN, 6, 3, 6, "Rare");
        EdiblePlant cherry = new EdiblePlant(PlantType.CHERRY, 5, 2, 6, "Rare");
        EdiblePlant apple = new EdiblePlant(PlantType.APPLE, 5, 2, 5, "Rare");
        EdiblePlant watermelon = new EdiblePlant(PlantType.WATERMELON, 5, 3, 6, "Rare");
        EdiblePlant fig = new EdiblePlant(PlantType.FIG, 6, 2, 5, "Rare");

        //EPIC
        EdiblePlant mango = new EdiblePlant(PlantType.MANGO, 5, 3, 7, "Epic");
        EdiblePlant avocado = new EdiblePlant(PlantType.AVOCADO, 5, 3, 7, "Epic");
        EdiblePlant buddhaHand = new EdiblePlant(PlantType.BUDDHAHAND, 7, 4, 8, "Epic");
        EdiblePlant dragonfruit = new EdiblePlant(PlantType.DRAGONFRUIT, 5, 3, 7, "Epic");

        //ORNAMENTAL PLANTS
        Plant monstera = new Plant(PlantType.MONSTERA, false, mediumValue, "Rare");
        Plant orchid = new Plant(PlantType.ORCHID, false, 3, "Epic");
        Plant begonia = new Plant(PlantType.BEGONIA, false, 5, "Common");
        Plant hibiscus = new Plant(PlantType.HIBISCUS, false, mediumValue, "Rare");
        Plant nepenthes = new Plant(PlantType.NEPENTHES, false, mediumValue, "Epic");
        Plant strelitzia = new Plant(PlantType.STRELITZIA, false, 5, "Rare");

        //MODEL SETUP
        Encyclopedia plants_encyclopedia = new Encyclopedia();
        
        //Add Edibles
        plants_encyclopedia.addPlant(tomato);
        plants_encyclopedia.addPlant(carrot);
        plants_encyclopedia.addPlant(corn);
        plants_encyclopedia.addPlant(onion);
        plants_encyclopedia.addPlant(potato);
        plants_encyclopedia.addPlant(pumpkin);
        plants_encyclopedia.addPlant(pepper);
        plants_encyclopedia.addPlant(eggplant);
        plants_encyclopedia.addPlant(radish);
        plants_encyclopedia.addPlant(zucchini);
        plants_encyclopedia.addPlant(cherry);
        plants_encyclopedia.addPlant(mango);
        plants_encyclopedia.addPlant(avocado);
        plants_encyclopedia.addPlant(buddhaHand);
        plants_encyclopedia.addPlant(dragonfruit);
        plants_encyclopedia.addPlant(apple);
        plants_encyclopedia.addPlant(watermelon);
        plants_encyclopedia.addPlant(fig);
        
        //Add Ornamentals
        plants_encyclopedia.addPlant(monstera);
        plants_encyclopedia.addPlant(orchid);
        plants_encyclopedia.addPlant(begonia);
        plants_encyclopedia.addPlant(hibiscus);
        plants_encyclopedia.addPlant(nepenthes);
        plants_encyclopedia.addPlant(strelitzia);

        //STORAGE SETUP
        Storage storage = new Storage();
        
        //Testing
        storage.addProduct(PlantType.TOMATO, 5);
        storage.addProduct(PlantType.CARROT, 10);
        storage.addProduct(PlantType.DRAGONFRUIT, 1);
        storage.addProduct(PlantType.MANGO, 15);

        System.out.println(plants_encyclopedia.toString());

        tomato.unlock();
        nepenthes.unlock();
        hibiscus.unlock();
        dragonfruit.unlock();
        avocado.unlock();
        strelitzia.unlock();
        plants_encyclopedia.unlockAll();
        
        //VIEW SETUP
        MainScreen screen = new MainScreen();
        screen.show();

        //CONTROLLER SETUP
        new MainScreenController(screen, plants_encyclopedia, storage);
    
    }
}
