package com.mapo.pokeclone.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Entity {
	public Enemy(){
		super();
		this.texture = new Texture(Gdx.files.internal("cat.png"));
		setBounds(192,224,16,16);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public Texture getTexture() {
		return this.texture;
	}

}
