package it.unibo.plantsfarm.model.items;

public final class ItemHoe extends ItemsFarmBase {
    public ItemHoe(final int buff) {
        super(buff);
    }

    public Tooltype getTypeItem() {
        return Tooltype.HOE;
    }
}
