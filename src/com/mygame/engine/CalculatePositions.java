package com.mygame.engine;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygame.enitys.Player;
import com.mygame.screens.WorldScreen;

///////////////////////////////////////////
//ToDo
//Threding mit libgdx und wo Inputabfrage


public class CalculatePositions extends Thread{
	
	private static OrthographicCamera camera	= null;
	private static int worldSizeX				= 0;
	private static int worldSizeY				= 0;	
	private static Player player				= null;
	private static com.mygame.entitys.Enemy enemy					= null;
	private static ArrayList<com.mygame.entitys.Enemy> enemyList	= null;
	
	 // calc min/max camera points inside the map
    private static float minCameraX 			= 0.0f; 
    private static float maxCameraX 			= 0.0f;
    private static float minCameraY 			= 0.0f;
    private static float maxCameraY 			= 0.0f;
    
    private static boolean rightMove			= false;
    private static boolean leftMove				= false;
    private static boolean topMove				= false;
    private static boolean bottomMove			= false;
	
	public CalculatePositions(){
		worldSizeX 		= WorldScreen.getWorldSizeX();
		worldSizeY 		= WorldScreen.getWorldSizeX();
		camera 			= WorldScreen.getCamera();
		player 			= WorldScreen.getPlayer();
		enemy 			= WorldScreen.getEnemy();
		enemyList  		= WorldScreen.getAlleEnemys();
		
		minCameraX 		= camera.zoom * (camera.viewportWidth / 2); 
		maxCameraX 		= worldSizeX - minCameraX;
		minCameraY 		= camera.zoom * (camera.viewportHeight / 2);
		maxCameraY 		= worldSizeY - minCameraY;
	}
	
	public void run(){
		while(true){			
			//enemy.update();
//			// das wird nur ausgeführt wenn es mehr als einen Gegner im Spiel gibt
//			if (enemyList != null)
//			for (com.mygame.entitys.Enemy e : enemyList) {
//				e.update();
//			}
			
			
			if(rightMove){
				player.setX(player.getX() + player.getVelocity().x * Gdx.graphics.getDeltaTime());
//				if(player.collidaesRight() ){
//					player.setX(player.getX()-player.getWidth() / 8);
//				}else{ 
//				}
			}
			if(leftMove){
				player.setX(player.getX() - player.getVelocity().x * Gdx.graphics.getDeltaTime());
//				if(player.collidesLeft() ){
//					player.setX(player.getX() + player.getWidth() / 8);
//				}else{
//				}
			}
			if(topMove){
				player.setY(player.getY() + player.getVelocity().y * Gdx.graphics.getDeltaTime());
//				if(player.collidesLeft() ){
//					player.setY(player.getY());
//				}else if(player.isCellDoor(player.getX(), player.getY())){
////					ScreenManager.getInstance().dispose(Screen.WORLD);
////					ScreenManager.getInstance().show(Screen.POKECENTER_INDOR);
//				}else
			}
			if(bottomMove){
				player.setY(player.getY() - player.getVelocity().y * Gdx.graphics.getDeltaTime());
//				if(player.collidesBottom() ){
//					player.setY(player.getY() + player.getHeight() / 8);
//				}else{ 
//				}
			}
			
			camera.position.set(
		            Math.min(maxCameraX, Math.max(player.getX(), minCameraX)),
		            Math.min(maxCameraY, Math.max(player.getY(), minCameraY)),
		            0);
			try {
				sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void pause(){
		
	}

	public static void setLeftMove(boolean _b) {
		if(rightMove && _b) rightMove = false;
        leftMove = _b;
        System.out.println("Ich will nach links laufen");
	}

	public static void setRightMove(boolean _b) {
		if(leftMove && _b) leftMove = false;
		rightMove = _b;
		System.out.println("Ich will nach rechts laufen");
	}

	public static void setTopMove(boolean _b) {
		if(bottomMove && _b) bottomMove = false;
		topMove = _b;
		System.out.println("Ich will nach oben laufen");
	}

	public static void setBottomMove(boolean _b) {
		if(topMove && _b) topMove = false;
		bottomMove = _b;
		System.out.println("Ich will nach unten laufen");
	}
}