package com.mapo.pokeclone.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mapo.pokeclone.entitys.Player;
import com.mapo.pokeclone.entitys.Enemy;
import com.mapo.pokeclone.screens.GameScreen;

import java.util.ArrayList;

public class CalculatePositions {
    private static OrthographicCamera camera = null;
    public static Player player = null;
    private static float minCameraX = 0.0f;
    private static float maxCameraX = 0.0f;
    private static float minCameraY = 0.0f;
    private static float maxCameraY = 0.0f;
    private static boolean rightMove = false;
    private static boolean leftMove = false;
    private static boolean topMove = false;
    private static boolean bottomMove = false;
    private final TiledMapTileLayer collisionLayer;
    private final float cellWith;
    private final float cellHeight;

    public CalculatePositions(GameScreen screen, TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
        camera = screen.getCamera();
        player = screen.getPlayer();
        int worldSizeX = GameScreen.getWorldSizeX();
        int worldSizeY = GameScreen.getWorldSizeX();
        ArrayList<Enemy> enemyList = GameScreen.getAlleEnemys();

        minCameraX = camera.zoom * (camera.viewportWidth / 2);
        maxCameraX = worldSizeX - minCameraX;
        minCameraY = camera.zoom * (camera.viewportHeight / 2);
        maxCameraY = worldSizeY - minCameraY;
        cellWith = collisionLayer.getWidth() / 16f;
        cellHeight = collisionLayer.getHeight() / 16f;
    }

    public void calc() {
        if (rightMove) {
            player.setX(player.getX() + player.getVelocity().x * Gdx.graphics.getDeltaTime());
            player.setAnimation("right");
            TiledMapTileLayer.Cell cell = this.getCell("right");
            if ((this.isBlocked(cell))) {
                    player.setX(player.getX() - cellWith);
            }
        }
        if (leftMove) {
            player.setX(player.getX() - player.getVelocity().x * Gdx.graphics.getDeltaTime());
            player.setAnimation("left");
            if ((this.isBlocked(this.getCell("left")))) {
                player.setX(player.getX() + cellWith);
            }
        }
        if (topMove) {
            player.setY(player.getY() + player.getVelocity().y * Gdx.graphics.getDeltaTime());
            player.setAnimation("up");
            if ((this.isBlocked(this.getCell("up")))) {
                player.setY(player.getY() - cellHeight);
            }
        }
        if (bottomMove) {
            player.setY(player.getY() - player.getVelocity().y * Gdx.graphics.getDeltaTime());
            player.setAnimation("down");
            if ((this.isBlocked(this.getCell("down")))) {
                player.setY(player.getY() + cellHeight);
            }
        }

        camera.position.set(
                Math.min(maxCameraX, Math.max(player.getX(), minCameraX)),
                Math.min(maxCameraY, Math.max(player.getY(), minCameraY)),
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

    private TiledMapTileLayer.Cell getCell(String direction) {
        // (player pos + (tile size / 2)) / tile size
        switch (direction) {
            case "right":
                return this.collisionLayer.getCell(Math.round(player.getX() + player.getWidth() / 2) / 16, Math.round(player.getY() / 16));
            case "left":
                return this.collisionLayer.getCell(Math.round(player.getX()) / 16, Math.round(player.getY() / 16));
            case "up":
                return this.collisionLayer.getCell(Math.round(player.getX() / 16), Math.round(player.getY() + player.getWidth() / 2) / 16);
            case "down":
                return this.collisionLayer.getCell(Math.round(player.getX() / 16), Math.round(player.getY()) / 16);
            default:
                return null;
        }

    }

    public static void setLeftMove(boolean _b) {
        if (rightMove && _b) rightMove = false;
        leftMove = _b;
    }

    public static void setRightMove(boolean _b) {
        if (leftMove && _b) leftMove = false;
        rightMove = _b;
    }

    public static void setTopMove(boolean _b) {
        if (bottomMove && _b) bottomMove = false;
        topMove = _b;
    }

    public static void setBottomMove(boolean _b) {
        if (topMove && _b) topMove = false;
        bottomMove = _b;
    }
}
