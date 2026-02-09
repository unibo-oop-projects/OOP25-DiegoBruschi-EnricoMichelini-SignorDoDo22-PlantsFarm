package it.unibo.plantsfarm.controller.garden;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.tiles.SolidBlock;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

public final class SpawningBuffsController {
    public static final int MAX_PRESENT_BUFFS = 3;
    public static final long SPAWN_COOLDOWN = 15_000L;
    public static final int BUFF_SIZE = 48;

    private final List<Buff> activeBuffs = new LinkedList<>(List.of());
    private final TileMap mappa;
    private long lastPickUp = System.currentTimeMillis();
    private Rectangle buffPosition;
    private final List<Soil> soilList;
    private final List<SolidBlock> solidBlocks;

    public SpawningBuffsController(final TileMap map) {
        this.mappa = map;
        this.soilList = mappa.getSoilList();
        this.solidBlocks = mappa.getSolidBlocks();
    }

    public void updateUpGrade() {
        if (activeBuffs.size() < 2 && lastPickUp + SPAWN_COOLDOWN < System.currentTimeMillis()) {

            final double randomPosX = (int) (Math.random() * (ImplViewGamePanel.WORLD_WIDTH + 1));
            final double randomPosY = (int) (Math.random() * (ImplViewGamePanel.WORLD_HEIGHT + 1));
            if (verifyPosUpgrade(randomPosX, randomPosY)) {
                this.buffPosition = new Rectangle((int) randomPosX, (int) randomPosY, BUFF_SIZE, BUFF_SIZE);
                final Buff buff = new Buff(buffPosition);
                activeBuffs.add(buff);
                lastPickUp = System.currentTimeMillis();
            }
        }

    }

    public void removeBuffFromMap(final Buff buff) {
        activeBuffs.remove(buff);
        lastPickUp = System.currentTimeMillis();
    }

    public List<Buff> getBuffList() {
        return this.activeBuffs;
    }

    public boolean verifyPosUpgrade(final double posX, final double posY) {

        this.buffPosition = new Rectangle((int) posX, (int) posY, BUFF_SIZE, BUFF_SIZE);

        for (final Soil soil : soilList) {
            if (buffPosition.intersects(soil.getCoordinate()) || soil.getCoordinate().contains(buffPosition)) {
                return false;
            }
        }

        for (final SolidBlock block : solidBlocks) {
            if (buffPosition.intersects(block.getCoordinate()) || block.getCoordinate().contains(buffPosition)) {
                return false;
            }
        }
        return true;
    }
}
