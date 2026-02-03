package it.unibo.plantsfarm.model.items;

import static it.unibo.plantsfarm.model.player.PlayersTypes.EXPERTFARMER;
import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;
import java.util.LinkedList;
import java.util.List;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.PlayersTypes;


public class InventoryFactory {

    public List<ItemsFarm> createInventory(PlayersTypes typePlayer) {

        if(typePlayer == EXPERTFARMER) {
            List<ItemsFarm> inventory = new LinkedList<>();
            inventory.add(new ItemsExpert(100000,10,0,Tooltype.FERTILIZER));
            inventory.add(new ItemsExpert(100000,10,0,Tooltype.WATERCAN));
            inventory.add(new ItemsExpert(100000,10,0,Tooltype.HOE));
            return inventory;

        }else if(typePlayer == FARMER) {
            List<ItemsFarm> inventory = new LinkedList<>();
            inventory.add(new ItemsExpert(20,1,10,Tooltype.FERTILIZER));
            inventory.add(new ItemsExpert(20,1,10,Tooltype.WATERCAN));
            inventory.add(new ItemsExpert(20,1,10,Tooltype.HOE));
            return inventory;

        } else {
            throw new IllegalArgumentException("TypePlayer not found ");
        }
    }
}

