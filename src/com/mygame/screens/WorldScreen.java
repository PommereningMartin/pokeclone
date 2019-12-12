package com.mygame.screens;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.engine.CalculatePositions;
import com.mygame.enitys.Player;
import com.mygame.entitys.Enemy;

/**
 *
 * @author Martin
 */
public class WorldScreen implements com.badlogic.gdx.Screen {

	private static OrthographicCamera camera = null;
	private static TiledMap map = null;
	private static OrthogonalTiledMapRenderer renderer = null;
	private static Player player = null;
	private static MapProperties prop = null;
	private int mapWidth = 0;
	private int mapHeight = 0;
	private int tilePixelWidth = 0;
	private int tilePixelHeight = 0;
	private static int worldSizeX = 0;
	private static int worldSizeY = 0;
	private static CalculatePositions calculatePosition = null;

	private static final int rows = 4;
	private static final int cols = 4;

	private static Animation walkAnimation;
//	private static Animation walkLeftAnimation;
//	private static Animation walkRightAnimation;
//	private static Animation walkDownAnimation;

	// private static Texture playerTexture = new
	// Texture("entity/Java2D_Player_Sprite.png");
	private TextureRegion[] walkFrames = null;
	private TextureRegion currentFrame = null;

	private float stateTime = 0.0f;

	public WorldScreen() {
		System.out.println("WorldScreen");
		// ToDo Timer für die automatische speicherung des Spielers
		// safePlayerTimer.start();

		// erzeugen aller erforderlichen Objekte
		map = new TmxMapLoader().load("pokemap.tmx");
		camera = new OrthographicCamera();
		renderer = new OrthogonalTiledMapRenderer(map);

		// setting up opbjects
		camera.zoom = 0.6f;
		camera.setToOrtho(false);
		// camera.update();

		// setting the view to the renderer
		renderer.setView(camera);

		// initialize MapProperties
		prop = renderer.getMap().getProperties();
		mapWidth = prop.get("width", Integer.class); // how many tiles in map
		mapHeight = prop.get("height", Integer.class);
		tilePixelWidth = prop.get("tilewidth", Integer.class); // size of each tile
		tilePixelHeight = prop.get("tileheight", Integer.class);
		worldSizeX = mapWidth * tilePixelWidth;
		worldSizeY = mapHeight * tilePixelHeight;

		player = new Player();
		player.setTexture("cat.png");
		player.setPosition(192f, 224f);
		player.setVelocity(20.0f);

		calculatePosition = new CalculatePositions();
		calculatePosition.start();

//        TextureRegion[][] tmp = TextureRegion.split(playerTexture, playerTexture.getWidth() / cols, playerTexture.getHeight() / rows);
//        walkFrames = new TextureRegion[(cols * rows)];
//        int index = 0;
//
//        for (int i = 0; i < rows; i++) {
//                for (int j = 0; j < cols; j++) {
//                        walkFrames[index++] = tmp[3][j];
//                }
//
//        }
//
//        walkAnimation = new Animation(0.220f, walkFrames);
	}

	@Override
	public void render(float f) {
		// clearing up the screen an make a black background
		Gdx.gl.glClearColor(0, 0, 0, 1);
		 //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		 //World.step(timeStep, velocityIterations, positionIterations);

		// set the camera to either the player or the min/max of the camera based on
		// player position

		camera.update();
		renderer.setView(camera);
		renderer.getBatch().setProjectionMatrix(camera.combined);
		renderer.render();
		//renderer.render();
//		renderer.getBatch().begin();
//		renderer.getBatch().draw(new Texture(Gdx.files.internal("cat.png")), 60.0f, 40.0f);
//		renderer.getBatch().end();
		renderer.getBatch().begin();
		renderer.getBatch().draw(player.getTexture(),player.getX(), player.getY());
		renderer.getBatch().end();

		// renderer.getBatch().draw(new Texture(50, 50, Pixmap.Format.Alpha), 0.0f,
		// 0.0f);
		renderer.getBatch().setProjectionMatrix(camera.combined);

	}

	@Override
	public void resize(int i, int i1) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void pause() {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void resume() {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void hide() {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void dispose() {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void show() {
	}

	public static final int getWorldSizeX()
	{return worldSizeX;}
	
	public static final int getWorldSizeY()
	{return worldSizeY;}

	public static OrthographicCamera getCamera() {
		// TODO Auto-generated method stub
		return camera;
	}

	public static Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	public static Enemy getEnemy() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Enemy> getAlleEnemys() {
		// TODO Auto-generated method stub
		return null;
	}

}
