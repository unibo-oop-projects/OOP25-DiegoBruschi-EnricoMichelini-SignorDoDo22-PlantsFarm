package it.unibo.Stats.api;

public interface FactoryStat {

    /**
     *  Used to decouple the creation of Stats from their concrete implementation
     * @return
     */
    Stats factoryMethod();  
} 
