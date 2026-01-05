package it.unibo.Controller;

import java.util.List;
import javax.swing.ImageIcon;

import it.unibo.Model.Encyclopedia;
import it.unibo.Model.Plant;
import it.unibo.Visual.ShopMenuFrame;
import it.unibo.Visual.Texture;

public class ShopController {

    private final int maxNumberPlants = 1; 
    private final ShopMenuFrame shopMenuFrame;
    private final Encyclopedia encyclopedia;
    private boolean request1 = true;
    private boolean request2 = true;

    public ShopController(ShopMenuFrame shopMenuFrame, Encyclopedia encyclopedia){
        this.shopMenuFrame = shopMenuFrame;
        this.encyclopedia = encyclopedia;
        setupListeners();
    }

    private void setupListeners(){
        shopMenuFrame.addSellListener(e -> {
            if (request1){
                request1=false;
                request2=true;
                setRandomPlant(1);
            }
            else if(request2){
                request2=false;
                request1=true;
                setRandomPlant(2);
            }
        });
    }
    
    private void setRandomPlant(int index){
        List<Plant> ediblePlantsList = encyclopedia.getUnlockedEdiblePlants();
        int total = encyclopedia.getNumberUnlockedEdiblePlants();

        //TO DO, ERROR
        if (total == 0) return;

        int randomIndexPlant = ControllerUtils.getRandomNumber(total);
        Plant selectedPlant = ediblePlantsList.get(randomIndexPlant);
                
        String iconFileName = selectedPlant.getType().getIconFileName();
        ImageIcon plantIcon = Texture.getPlantIcon(iconFileName, Texture.PLANT_DETAIL_DIMENSION);

        shopMenuFrame.setSellButtonContent(index, selectedPlant.getType().getDisplayName(), plantIcon);
    }
}
