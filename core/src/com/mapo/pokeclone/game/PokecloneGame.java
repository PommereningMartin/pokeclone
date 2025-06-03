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
		player.setPosition(192f, 224f);
		player.setVelocity(40.0f);

		Enemy enemy = new Enemy();
		enemy.init();
		enemy.setVelocity(40.0f);
        InputHandler inputHandler = new InputHandler(player);
		this.gameScreen = new GameScreen(this, player, enemy);
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
