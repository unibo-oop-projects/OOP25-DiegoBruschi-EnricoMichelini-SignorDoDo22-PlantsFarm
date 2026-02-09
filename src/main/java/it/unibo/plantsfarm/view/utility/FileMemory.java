package it.unibo.plantsfarm.view.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import it.unibo.plantsfarm.controller.player.Memory;

public final class FileMemory implements Memory {

    private final Path basePath;

    public FileMemory(final Path basePath) {
        this.basePath = basePath;
    }

    @Override
    public void save(final String fileName, final String data) throws IOException {
        Files.createDirectories(basePath);
        Files.writeString(basePath.resolve(fileName), data);
    }

    @Override
    public String load(final String fileName) throws IOException {
        final Path path = basePath.resolve(fileName);
        if (!Files.exists(path)) {
            return null;
        }
        return Files.readString(path);
    }
}
