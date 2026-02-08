package it.unibo.plantsfarm.controller.garden;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.garden.ImplSpawningBuffs;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.tiles.SolidBlock;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public class SpawningBuffsController {
    private final List<Buff> activeBuffs = new LinkedList<>(List.of());
    public static final long SPAWN_COOLDOWN = 15_000L;
    public long lastPickUp = System.currentTimeMillis();
    public static final int MAX_PRESENT_BUFFS = 3;
    private final TileMap map;
    private Rectangle buffPosition;
    private boolean forbiddenPosition;
    private List<Soil> soilList;
    private List<SolidBlock> solidBlocks;
    private ImplSpawningBuffs spawningBuffs;
    private AbstractPlayer player;

    public SpawningBuffsController(TileMap map, AbstractPlayer player) {
        this.spawningBuffs = new ImplSpawningBuffs();
        this.map = map;
        this.player = player;
        this.soilList = map.getSoilList();
        this.solidBlocks = map.getSolidBlocks();
    }

    public void updateUpGrade() {
        if (activeBuffs.size() < 2 && lastPickUp + SPAWN_COOLDOWN < System.currentTimeMillis()) {

            double randomPosX = (int)(Math.random() * ((ImplViewGamePanel.WORLD_WIDTH) + 1));
            double randomPosY = (int)(Math.random() * ((ImplViewGamePanel.WORLD_HEIGHT) + 1));
            if(verifyPosUpgrade(randomPosX, randomPosY)){
                this.buffPosition = new Rectangle((int) randomPosX, (int) randomPosY, 48, 48);
                Buff buff = new Buff(buffPosition);
                activeBuffs.add(buff);
                lastPickUp = System.currentTimeMillis();
            }
        }

    }

    public void removeBuffFromMap(Buff buff) {
        activeBuffs.remove(buff);
        lastPickUp = System.currentTimeMillis();
    }

    public List<Buff> getBuffList() {
        return this.activeBuffs;
    }

    public boolean verifyPosUpgrade(double x, double y){


        double randomPosX = (int)(Math.random() * ((ImplViewGamePanel.WORLD_WIDTH) + 1));
        double randomPosY = (int)(Math.random() * ((ImplViewGamePanel.WORLD_HEIGHT) + 1));

        this.buffPosition = new Rectangle((int) randomPosX, (int) randomPosY, 48, 48);

        for (Soil soil : soilList) {
            if (buffPosition.intersects(soil.getCoordinate()) || soil.getCoordinate().contains(buffPosition)) {
                return false;
            }
        }

        for (SolidBlock block : solidBlocks) {
            if (block.getCoordinate().intersects(buffPosition) || block.getCoordinate().contains(buffPosition)) {
                return false;
            }
        }

        return true;

    }
}
