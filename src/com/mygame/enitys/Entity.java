package com.mygame.enitys;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Martin Pommerening
 */
public abstract class Entity extends Rectangle{
    
    private static final long serialVersionUID = 1L;
    protected static boolean collisionX = false, collisionY = false;
    protected static Vector2 velocity = null;
    private static TiledMapTileLayer collisionLayer = null;
    private static TiledMapTileLayer walkableLayer = null;
    private static final String blockedKey = "blocked";
    private static final String doorKey = "door";
    private static double movementTimer = 0.0;
    private static int moveDirection = 0; 
    
    public Entity(){
    	velocity = new Vector2();
    }
}
