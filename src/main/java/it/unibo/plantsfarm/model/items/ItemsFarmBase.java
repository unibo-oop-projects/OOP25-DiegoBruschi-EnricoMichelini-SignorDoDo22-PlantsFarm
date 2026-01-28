package it.unibo.plantsfarm.model.items;

public abstract class ItemsFarmBase {
    protected int posx;
    protected int posy;
    protected int buff = 5;
    public int getBuffItem(){ return this.buff; }

} 