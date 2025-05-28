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
import com.mapo.pokeclone.utils.TileCollisionManager;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private OrthographicCamera camera = null;
    private OrthogonalTiledMapRenderer renderer = null;
    private Player player = null;
    private static MapProperties prop = null;
    private static int worldSizeX = 0;
    private static int worldSizeY = 0;
    private static CalculatePositions calculatePosition = null;

    private static final int rows = 4;
    private static final int cols = 4;
    int[] backgroundLayers = { 0, 1 }; // don't allocate every frame!
    int[] foregroundLayers = { 2 }; // don't allocate every frame!
    InputHandler inputHandler = null;
    final PokecloneGame game;

    public GameScreen(PokecloneGame game, Player player, Enemy enemy, InputHandler inputHandler) {
        this.player = player;
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

        this.player.setPosition(192f, 224f);
        this.player.setVelocity(40.0f);

        calculatePosition = new CalculatePositions(this, (TiledMapTileLayer) collisionLayer);
        this.game = game;
        this.inputHandler = new InputHandler();
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.settMovementTimer();
        //World.step(timeStep, velocityIterations, positionIterations);
        camera.update();
        renderer.setView(camera);
        renderer.getBatch().setProjectionMatrix(camera.combined);
        calculatePosition.calc();
        renderer.render(backgroundLayers);
        renderer.render(foregroundLayers);
        renderer.render();
        renderer.getBatch().begin();
        renderer.getBatch().draw(player.getKeyFrame(), player.getX(), player.getY());
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

    public static final int getWorldSizeX() {
        return worldSizeX;
    }

    public static final int getWorldSizeY() {
        return worldSizeY;
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
