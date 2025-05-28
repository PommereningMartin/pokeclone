package com.mapo.pokeclone.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

	private int health;
	private String playerName;

	public Player() {
		super();
		this.texture = new Texture(Gdx.files.internal("test1.png"));
		setBounds(192,224,16,16);
	}
	public final Vector2 getVelocity() {
		return velocity;
	}
	
	public final void setVelocity(float xy) {
		velocity.x = xy;
		velocity.y = xy;
	}

	public final Texture getTexture() {
		return texture;
	}
}
