package it.unibo.plantsfarm.model.items;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;

public  class ItemsFarmBase implements ItemsFarm {

    private int totalintegrity = 100;
    private int actualintegrity = 10;
    private int minIntegrity = 0;
    private int minLevel = 0;
    private int maxLevel = 30;
    private int level;
    private int costRepair;
    private Tooltype type;

    public ItemsFarmBase(final int integrity, final int level, final int costRepair, Tooltype type){
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
    public void repair() {
        this.actualintegrity = totalintegrity;
        System.out.println("SONO QUA  RIPARA ITEM ActualIntegrity nell'item: " +  actualintegrity);
    }

    @Override
    public Tooltype getTooltype() {
        return this.type;
    }

    @Override
    public void upgrade() {
        this.level = level + 1;
    }

    @Override
    public void useItem() {
        this.actualintegrity = actualintegrity - 4;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getCostRepair() {
        return this.costRepair;
    }

    @Override
    public int getMaxIntegrity() {
        return this.totalintegrity;
    }

    @Override
    public int getMinIntegrity() {
        return this.minIntegrity;
    }

    @Override
    public int getMaxLevel() {
        return this.totalintegrity;
    }

    @Override
    public int getMinLevel() {
        return this.minIntegrity;
    }

}
