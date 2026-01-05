package it.unibo.Controller;

import java.util.Random;

public class ControllerUtils {

    private static final Random random = new Random();
    
    public static int getRandomNumber(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Il massimo deve essere maggiore di 0");
        }
        return random.nextInt(max);
    }


}
