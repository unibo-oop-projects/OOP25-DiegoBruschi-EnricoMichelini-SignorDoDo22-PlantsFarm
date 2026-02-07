package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;

public class ImplSpawningBuffs {

    public Buff generateBuff(Rectangle area) {
        return new Buff(area);
    }
    
}
