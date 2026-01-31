package it.unibo.plantsfarm.model.player.api;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.Mappa;
import it.unibo.plantsfarm.model.Soil;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

/**
 * Abstract base class representing a generic player entity.
 * It stores the player position, movement speed and direction.
 */
public abstract class Player {

    public static final int FARMER_SPEED = 300;
    public static final int EXPERT_FARMER_SPEED = 500;
    public List<Soil> listSoil = new LinkedList<>();

    /** Movement speed of the player (units per second). */
    protected double speed;

    private Plant piantaDisponibile = new Plant(PlantType.TOMATO);

    /** Current X position of the player in world coordinates. */
    private double posX = ImplViewGamePanel.WORLD_WIDTH / 2;

    /** Current Y position of the player in world coordinates. */
    private double posY = ImplViewGamePanel.WORLD_HEIGHT / 2;

    /** Current movement direction of the player. */
    private UserInput direction = UserInput.FERMO;

    private Mappa map = new Mappa();

    public Player() {
        map.loadMap("/maps/map.txt");
    }

    /**
     * Updates the position of the player based on the elapsed time
     * and the current movement direction.
     *
     * @param time the elapsed time since the last update, in milliseconds
     */
    public void updatePlayer(final long time) {
        final double delta = speed * time / 1000.0;
        double nextPosX = posX;
        double nextPosY = posY;

        switch (direction) {
            case LEFT -> nextPosX -= delta;
            case RIGHT -> nextPosX += delta;
            case UP -> nextPosY -= delta;
            case DOWN -> nextPosY += delta;
            case ACTIONHOE -> pianta();
            case FERMO -> { }
        }

        if (nextPosX > 1
            && nextPosY > 1
            && nextPosX < ImplViewGamePanel.WORLD_WIDTH
            && nextPosY < ImplViewGamePanel.WORLD_HEIGHT) {
            posX = nextPosX;
            posY = nextPosY;
        }

        System.out.println("Player position: " + posX + " " + posY);
    }

    /**
     * Sets the current movement direction of the player.
     *
     * @param direction the new movement direction
     */
    public final void setDirection(final UserInput direction) {
        this.direction = direction;
    }

    /**
     * Returns the current X position of the player.
     *
     * @return the X coordinate in world space
     */
    public final double getPosx() {
        return this.posX;
    }

    /**
     * Returns the current Y position of the player.
     *
     * @return the Y coordinate in world space
     */
    public final double getPosy() {
        return this.posY;
    }

    /**
     * Returns the current movement direction of the player.
     *
     * @return the last direction set for the player
     */
    public final UserInput getDirection() {
        return this.direction;
    }

    public void pianta() {
        final Rectangle hitbox = new Rectangle((int) posX, (int) posY, 30, 30);
        for (final Soil zolla : listSoil) {
            if (zolla.getCoordinate().contains(hitbox)) {
                if (!zolla.getIsPlanted()) {
                    zolla.setPlanted(piantaDisponibile);
                }
                if (zolla.getPlant() != null) {
                    zolla.getPlant().water(System.currentTimeMillis());
                }
            }
        }
    }

    public void updateSoil(final Long now) {
        for (final Soil zolla : listSoil) {
            final Plant plant = zolla.getPlant();
            if (plant != null) {
                plant.updateNeedsWater(now);
            }
        }
    }
}
