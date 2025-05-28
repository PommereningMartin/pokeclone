package com.mapo.pokeclone.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mapo.pokeclone.engine.InputHandler;
import com.mapo.pokeclone.entitys.Player;
import com.mapo.pokeclone.entitys.Enemy;
import com.mapo.pokeclone.screens.GameScreen;

public class PokecloneGame extends Game implements ApplicationListener {

	private GameScreen gameScreen;

    @Override
	public void create () {
		Player player = new Player();
		player.init();
		Enemy enemy = new Enemy();
		enemy.init();
        InputHandler inputHandler = new InputHandler();
		this.gameScreen = new GameScreen(this, player, enemy, inputHandler);
		Gdx.input.setInputProcessor(inputHandler);
		this.setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		this.gameScreen.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
