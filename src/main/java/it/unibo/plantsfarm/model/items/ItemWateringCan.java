package it.unibo.plantsfarm.model.items;

public class ItemWateringCan extends ItemsFarmBase {
    
    public ItemWateringCan(int buff){
        super(buff);
    }
    
    public Tooltype getTypeItem(){ return Tooltype.WATERINGCAN;}
}
