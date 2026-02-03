package it.unibo.plantsfarm.model.items;


import it.unibo.plantsfarm.model.items.api.ItemsFarm;

public class ItemsExpert implements ItemsFarm {

    private int totalintegrity = 200000;
    private int actualintegrity;
    private int level;
    private final int costRepair;
    private Tooltype type;

    public ItemsExpert(final int integrity, final int level, final int costRepair, Tooltype type){
        this.costRepair = costRepair;
        this.totalintegrity = integrity;
        this.actualintegrity = totalintegrity;
        this.level = level;
        this.type = type;

    }

    @Override
    public int getIntegrity() {
        return this.actualintegrity;
    }

    @Override
    public void repairItem() {
       return;
    }

    @Override
    public Tooltype getTooltype() {
        return type;
    }

    @Override
    public void upgrade() {
        return;
    }

    @Override
    public void useItem() {
        return;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getCostRepair() {
        return this.costRepair;
    }

}
