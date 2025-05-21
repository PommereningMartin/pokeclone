package com.mapo.pokeclone.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mapo.pokeclone.enitys.Player;
import com.mapo.pokeclone.entitys.Enemy;
import com.mapo.pokeclone.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;

public class CalculatePositions {

    private static OrthographicCamera camera = null;
    public static Player player = null;

    // calc min/max camera points inside the map
    private static float minCameraX = 0.0f;
    private static float maxCameraX = 0.0f;
    private static float minCameraY = 0.0f;
    private static float maxCameraY = 0.0f;

    private static boolean rightMove = false;
    private static boolean leftMove = false;
    private static boolean topMove = false;
    private static boolean bottomMove = false;
    private final TiledMapTileLayer collisionLayer;

    public CalculatePositions(GameScreen screen, TiledMapTileLayer collisionLayer) {
        int worldSizeX = GameScreen.getWorldSizeX();
        int worldSizeY = GameScreen.getWorldSizeX();
        camera = screen.getCamera();
        player = screen.getPlayer();
        ArrayList<Enemy> enemyList = GameScreen.getAlleEnemys();

        minCameraX = camera.zoom * (camera.viewportWidth / 2);
        maxCameraX = worldSizeX - minCameraX;
        minCameraY = camera.zoom * (camera.viewportHeight / 2);
        maxCameraY = worldSizeY - minCameraY;

        this.collisionLayer = collisionLayer;
    }

    public void run() {
        if (rightMove) {
            player.setX(player.getX() + player.getVelocity().x * Gdx.graphics.getDeltaTime());
            player.setAnimation("right");
            TiledMapTileLayer.Cell cell = this.getLayerCell();
            if (cell != null) {
                MapProperties cellProps = cell.getTile().getProperties();
                boolean isBlocked = cellProps.containsKey("blocked");
                System.out.println(isBlocked);
                if (isBlocked) {
                    player.setVelocity(0.0f);
                }

            }
        }
        if (leftMove) {
            player.setX(player.getX() - player.getVelocity().x * Gdx.graphics.getDeltaTime());
            player.setAnimation("left");
        }
        if (topMove) {
            player.setY(player.getY() + player.getVelocity().y * Gdx.graphics.getDeltaTime());
            player.setAnimation("up");
        }
        if (bottomMove) {
            player.setY(player.getY() - player.getVelocity().y * Gdx.graphics.getDeltaTime());
            player.setAnimation("down");
        }

        camera.position.set(
                Math.min(maxCameraX, Math.max(player.getX(), minCameraX)),
                Math.min(maxCameraY, Math.max(player.getY(), minCameraY)),
                0);
    }

    private TiledMapTileLayer.Cell getLayerCell() {
        int playerX = (int) (player.getX() / 16);
        int playerY = (int) (99 - (player.getY() / 16));
        System.out.println("playerX: " + playerX + ", playerY: " + playerY);
        System.out.println(collisionLayer.getProperties());
        return this.collisionLayer.getCell(playerX, playerY);
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