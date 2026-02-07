package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
import java.util.Random;

public class Buff {
    
    public static enum Type { HEART , LIGHTNING };
    
    private Type[] types = Type.values();
    private final Random rand = new Random();
    private Rectangle area;
    private Type type;
    private int index;

    public Buff(Rectangle area) {
        this.area = area;
        this.index = rand.nextInt(types.length);
        this.type = types[index];
    }

    public Type getBuffType() {
        return this.type;
    }

    public Rectangle getBuffPosition() {
        return this.area;
    }
}
