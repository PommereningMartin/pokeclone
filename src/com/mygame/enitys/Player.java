package com.mygame.enitys;

import com.badlogic.gdx.Gdx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Martin
 */
public class Player extends Entity implements java.io.Serializable {

	/**
	 * 
	 */
	enum Velocity {
		x, y
	};

	private static final long serialVersionUID = 1L;
	private Texture playerTexture;
	private int health;
	private String playerName;

	public Player() {
		super();
		playerTexture = new Texture(Gdx.files.internal("Java2D_Player_Sprite.png"));
	}

	public void draw(SpriteBatch _spriteBatch) {
		_spriteBatch.draw(playerTexture, 192f, 224f, 32, 32);
	}

	public void draw() {

	}

	public void update(float deltaTime) {

	}

	public String getPlayerName() {
		return this.playerName;
	}

	public int getHealth() {
		return this.health;
	}

	public final Vector2 getVelocity() {
		return velocity;
	}
	
	public final void setVelocity(float xy) {
		velocity.x = xy;
		velocity.y = xy;
	}

	public final void setVelocityY(float _y) {
		velocity.y = _y;
	}

	public final void setVelocityX(float _x) {
		velocity.x = _x;
	}

	public final void setTexture(String _pathToTexture) {
		playerTexture = new Texture(Gdx.files.internal(_pathToTexture));
	}

	public final Texture getTexture() {
		return playerTexture;
	}
}
