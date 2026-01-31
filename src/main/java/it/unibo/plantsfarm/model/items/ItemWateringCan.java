package it.unibo.plantsfarm.model.items;

public final class ItemWateringCan extends ItemsFarmBase {

    public ItemWateringCan(final int buff) {
        super(buff);
    }

    public Tooltype getTypeItem() {
        return Tooltype.WATERINGCAN;
    }
}
