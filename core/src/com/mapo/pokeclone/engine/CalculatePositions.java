package com.mapo.pokeclone.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mapo.pokeclone.entitys.Entity;
import com.mapo.pokeclone.screens.GameScreen;

public class CalculatePositions {
    private static OrthographicCamera camera = null;
    private static float minCameraX = 0.0f;
    private static float maxCameraX = 0.0f;
    private static float minCameraY = 0.0f;
    private static float maxCameraY = 0.0f;
    private final TiledMapTileLayer collisionLayer;
    private final float cellWith;
    private final float cellHeight;
	private float randomMovementTimer = 0;
private static final float DIRECTION_CHANGE_INTERVAL = 2.0f; // Change direction every 2 seconds

    public CalculatePositions(OrthographicCamera camera, TiledMapTileLayer collisionLayer) {
        CalculatePositions.camera = camera;
        this.collisionLayer = collisionLayer;
        int worldSizeX = GameScreen.getWorldSizeX();
        int worldSizeY = GameScreen.getWorldSizeX();

        minCameraX = camera.zoom * (camera.viewportWidth / 2);
        maxCameraX = worldSizeX - minCameraX;
        minCameraY = camera.zoom * (camera.viewportHeight / 2);
        maxCameraY = worldSizeY - minCameraY;
        cellWith = collisionLayer.getWidth() / 16f;
        cellHeight = collisionLayer.getHeight() / 16f;
    }

    public void calculate(Entity entity) {
        if (entity.rightMove) {
            entity.setX(entity.getX() + entity.getVelocity().x * Gdx.graphics.getDeltaTime());
            entity.setAnimation("right");
            if ((this.isBlocked(this.getCell("right", entity)))) {
                entity.setX(entity.getX() - cellWith );
            }
        }
        if (entity.leftMove) {
            entity.setX(entity.getX() - entity.getVelocity().x * Gdx.graphics.getDeltaTime());
            entity.setAnimation("left");
            if ((this.isBlocked(this.getCell("left", entity)))) {
                entity.setX(entity.getX() + cellWith);
            }
        }
        if (entity.topMove) {
            entity.setY(entity.getY() + entity.getVelocity().y * Gdx.graphics.getDeltaTime());
            entity.setAnimation("up");
            if ((this.isBlocked(this.getCell("up", entity)))) {
                entity.setY(entity.getY() - cellHeight);
            }
        }
        if (entity.bottomMove) {
            entity.setY(entity.getY() - entity.getVelocity().y * Gdx.graphics.getDeltaTime());
            entity.setAnimation("down");
            if ((this.isBlocked(this.getCell("down",   entity )))) {
                entity.setY(entity.getY() + cellHeight);
            }
        }
    }

public void calculateRandomMovement(Entity entity) {
    // Update timer
    randomMovementTimer += Gdx.graphics.getDeltaTime();
    
    // Change direction randomly every DIRECTION_CHANGE_INTERVAL seconds
    if (randomMovementTimer >= DIRECTION_CHANGE_INTERVAL) {
        randomMovementTimer = 0;
        // Reset all movement flags
        entity.rightMove = false;
        entity.leftMove = false;
        entity.topMove = false;
        entity.bottomMove = false;
        
        // Randomly choose a direction
        int direction = (int)(Math.random() * 4);
        switch (direction) {
            case 0:
                entity.rightMove = true;
                break;
            case 1:
                entity.leftMove = true;
                break;
            case 2:
                entity.topMove = true;
                break;
            case 3:
                entity.bottomMove = true;
                break;
        }
    }
    
    // Movement logic (same as before)
    if (entity.rightMove) {
        entity.setX(entity.getX() + entity.getVelocity().x * Gdx.graphics.getDeltaTime());
        if (this.isBlocked(this.getCell("right", entity))) {
            entity.setX(entity.getX() - cellWith);
            // Change direction immediately if blocked
            randomMovementTimer = DIRECTION_CHANGE_INTERVAL;
        }
    }
    if (entity.leftMove) {
        entity.setX(entity.getX() - entity.getVelocity().x * Gdx.graphics.getDeltaTime());
        if (this.isBlocked(this.getCell("left", entity))) {
            entity.setX(entity.getX() + cellWith);
            randomMovementTimer = DIRECTION_CHANGE_INTERVAL;
        }
    }
    if (entity.topMove) {
        entity.setY(entity.getY() + entity.getVelocity().y * Gdx.graphics.getDeltaTime());
        if (this.isBlocked(this.getCell("up", entity))) {
            entity.setY(entity.getY() - cellHeight);
            randomMovementTimer = DIRECTION_CHANGE_INTERVAL;
        }
    }
    if (entity.bottomMove) {
        entity.setY(entity.getY() - entity.getVelocity().y * Gdx.graphics.getDeltaTime());
        if (this.isBlocked(this.getCell("down", entity))) {
            entity.setY(entity.getY() + cellHeight);
            randomMovementTimer = DIRECTION_CHANGE_INTERVAL;
        }
    }
    
    // Set the entity to moving state while any direction is active
    entity.setIsMoving(entity.rightMove || entity.leftMove || entity.topMove || entity.bottomMove);
}

    public void updateCameraPosition(Entity entity) {
        camera.position.set(
                Math.min(maxCameraX, Math.max(entity.getX(), minCameraX)),
                Math.min(maxCameraY, Math.max(entity.getY(), minCameraY)),
                0);
    }

    private boolean isBlocked(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            TiledMapTile tile = cell.getTile();
            MapProperties tileProbs = tile.getProperties();
            return tileProbs.containsKey("blocked");
        }
        return false;
    }

    private TiledMapTileLayer.Cell getCell(String direction, Entity entity) {
        // (player pos + (tile size / 2)) / tile size
        switch (direction) {
            case "right":
                return this.collisionLayer.getCell(Math.round(entity.getX() + entity.getWidth() / 2) / 16, Math.round(entity.getY() / 16));
            case "left":
                return this.collisionLayer.getCell(Math.round(entity.getX()) / 16, Math.round(entity.getY() / 16));
            case "up":
                return this.collisionLayer.getCell(Math.round(entity.getX() / 16), Math.round(entity.getY() + entity.getWidth() / 2) / 16);
            case "down":
                return this.collisionLayer.getCell(Math.round(entity.getX() / 16), Math.round(entity.getY()) / 16);
            default:
                return null;
        }

    }
}
