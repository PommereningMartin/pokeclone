package com.mygame.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.engine.InputHandler;
import com.mygame.enitys.Player;
import com.mygame.screens.WorldScreen;

public class Main extends Game implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	static InputHandler inputHandler = null; 
	Player player = null;
	@Override
	public void create () {
        player = new Player();
        inputHandler = new InputHandler();
        Gdx.input.setInputProcessor(inputHandler);
        this.setScreen(new WorldScreen());
        System.out.println("pokeclone.org.gameloop.Gameloop.create()");
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		super.render();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
