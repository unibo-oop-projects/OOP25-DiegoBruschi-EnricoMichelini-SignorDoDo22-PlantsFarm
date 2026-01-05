package it.unibo.Controller;

import java.util.Map;
import javax.swing.ImageIcon;

import it.unibo.Model.PlantType;
import it.unibo.Model.Storage;
import it.unibo.Visual.StorageFrame;
import it.unibo.Visual.Texture;

public class StorageController {

    private final StorageFrame storageFrame;
    private final Storage storage; 

    public StorageController(StorageFrame storageFrame, Storage storage) {
        this.storageFrame = storageFrame;
        this.storage = storage;
        
        setupView();
        refreshQuantities();
    }

    private void setupView() {
        for (PlantType type : PlantType.values()) {
            if (isEdible(type)) {
                String displayName = type.getDisplayName();
                String iconName = type.getIconFileName();
                ImageIcon icon = Texture.getPlantIcon(iconName, Texture.PLANT_LIST_DIMENSION);
                
                storageFrame.createStorageSlot(displayName, icon);
            }
        }
    }

    public void refreshQuantities() {
        Map<PlantType, Integer> products = storage.getAllProducts(); 
        
        for (Map.Entry<PlantType, Integer> entry : products.entrySet()) {
            PlantType type = entry.getKey();
            int quantity = entry.getValue();
            
            storageFrame.updateItemQuantity(type.getDisplayName(), quantity);
        }
    }
    
    private boolean isEdible(PlantType type) {
        String cat = type.getCategory();
        return "Fruit".equals(cat) || "Vegetable".equals(cat);
    }
}
