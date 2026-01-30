package it.unibo.plantsfarm.model.items;

public class ItemHoe extends ItemsFarmBase{
    
    public ItemHoe(int buff){
        super(buff);
    }
    
    public Tooltype getTypeItem(){ return Tooltype.HOE;}
}
