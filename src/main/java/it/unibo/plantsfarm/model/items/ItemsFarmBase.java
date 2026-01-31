package it.unibo.plantsfarm.model.items;

public abstract class ItemsFarmBase {

    protected int posx;
    protected int posy;
    private final int buff;

    public ItemsFarmBase(final int buff) {
        this.buff = buff;
    }

    public int getBuffItem() {
        return this.buff;
    }
}
