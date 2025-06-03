package com.mapo.pokeclone.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mapo.pokeclone.engine.CalculatePositions;
import com.mapo.pokeclone.engine.InputHandler;
import com.mapo.pokeclone.entitys.Player;
import com.mapo.pokeclone.entitys.Enemy;
import com.mapo.pokeclone.game.PokecloneGame;
import java.util.ArrayList;

public class GameScreen implements Screen {

    private OrthographicCamera camera = null;
    private OrthogonalTiledMapRenderer renderer = null;
    private final Player player;
    private final Enemy enemy;
    private static MapProperties prop = null;
    private static int worldSizeX = 0;
    private static int worldSizeY = 0;
    private static CalculatePositions calculatePosition = null;

    private static final int rows = 4;
    private static final int cols = 4;
    int[] backgroundLayers = { 0, 1 }; // don't allocate every frame!
    int[] foregroundLayers = { 2 }; // don't allocate every frame!
    final PokecloneGame game;

    public GameScreen(PokecloneGame game, Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        TiledMap map = new TmxMapLoader().load("pokemap.tmx");
        MapLayer collisionLayer = map.getLayers().get("collision");
        this.camera = new OrthographicCamera();
        this.renderer = new OrthogonalTiledMapRenderer(map);

        this.camera.zoom = 0.4f;
        this.camera.setToOrtho(false);
        this.renderer.setView(camera);

        prop = map.getProperties();
        int mapWidth = prop.get("width", Integer.class); // how many tiles in map
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class); // size of each tile
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        worldSizeX = mapWidth * tilePixelWidth;
        worldSizeY = mapHeight * tilePixelHeight;

        calculatePosition = new CalculatePositions(camera, (TiledMapTileLayer) collisionLayer);
        this.game = game;
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.settMovementTimer();
        camera.update();
        renderer.setView(camera);
        renderer.getBatch().setProjectionMatrix(camera.combined);
        calculatePosition.calculate(player);
        calculatePosition.updateCameraPosition(player);
        calculatePosition.calculateRandomMovement(enemy);
        renderer.render(backgroundLayers);
        renderer.render(foregroundLayers);
        renderer.getBatch().begin();
        renderer.getBatch().draw(player.getKeyFrame(), player.getX(), player.getY());
        renderer.getBatch().draw(enemy.getTexture(), enemy.getX(), enemy.getY(), 16, 16);
        renderer.getBatch().end();
    }

    @Override
    public void resize(int i, int i1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change
    }

    @Override
    public void dispose() {
        player.getTexture().dispose();
    }

    @Override
    public void show() {
    }

    public static int getWorldSizeX() {
        return worldSizeX;
    }

    public OrthographicCamera getCamera() {
        // TODO Auto-generated method stub
        return this.camera;
    }

    public Player getPlayer() {
        // TODO Auto-generated method stub
        return this.player;
    }

    public static ArrayList<Enemy> getAlleEnemys() {
        // TODO Auto-generated method stub
        return null;
    }

}
