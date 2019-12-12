package com.mygame.enitys;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Martin
 */
public class Player extends Entity{
    
    /**
	 * 
	 */
	enum Velocity {
		x, y
	};
	private static final long serialVersionUID = 1L;
	private Rectangle playerImage;
    
    public Player() {
        this.playerImage = new Rectangle();
        playerImage.setSize(50.0f, 50.0f);
    }
    
    public void render() {
    }

	public boolean collidesLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean collidesRight() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean collidesBottom() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCellDoor(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	public Velocity getVelocity() {
		// TODO Auto-generated method stub
		return Velocity.x;
	}
    
}
