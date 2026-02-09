package it.unibo.plantsfarm.controller.player;

import java.io.IOException;

public interface Memory {
    void save(String fileName, String data) throws IOException;
    String load(String fileName) throws IOException;
}