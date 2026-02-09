package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
import java.util.Random;

public final class Buff {

    public enum Type { OMNI_BUFF }

    private final Type[] types = Type.values();
    private final Random rand = new Random();
    private final Rectangle area;
    private final Type type;
    private final int index;

    public Buff(final Rectangle area) {
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
