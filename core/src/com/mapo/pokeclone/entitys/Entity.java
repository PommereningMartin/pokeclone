package com.mapo.pokeclone.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.io.Serializable;

public abstract class Entity extends Actor implements Serializable {
    private static final int cols = 4, rows = 4;

    private Animation<TextureRegion> animation;
    protected static boolean collisionX = false, collisionY = false;
    protected static Vector2 velocity = null;
    private static final TiledMapTileLayer collisionLayer = null;
    private static final TiledMapTileLayer walkableLayer = null;
    private static final String blockedKey = "blocked";
    private static final String doorKey = "door";
    private float movementTimer = 0.0f;
    private static final int moveDirection = 0;
    protected Texture texture;
    private TextureRegion[][] tmp;
    private static final Integer IDLE_ROW_INDEX = -1;
    private static final Integer DOWN_ROW_INDEX = 0;
    private static final Integer UP_ROW_INDEX = 1;
    private static final Integer RIGHT_ROW_INDEX = 2;
    private static final Integer LEFT_ROW_INDEX = 3;
    private static final Integer animationTextureRegionSize = 4;
    private boolean isMoving = false;

    public Entity(){
        super();
    	velocity = new Vector2();
    }

    public void init() {
        this.tmp = TextureRegion.split(this.texture, 16, 16);
        this.animation = this.idleAnimation();
    }

    private Animation<TextureRegion> idleAnimation(){
        return this.buildAnimation(IDLE_ROW_INDEX);
    }

    private Animation<TextureRegion> buildAnimation(Integer startRowIndex) {
        TextureRegion[] walkFrames = new TextureRegion[4];
        int index = 0;
        for (int j = 0; j < cols; j++) {
            if (startRowIndex < 0) {
                walkFrames[index++] = this.tmp[0][0];
                continue;
            }
            walkFrames[index++] = this.tmp[startRowIndex][j];
        }
        // System.out.println("walkFrames: " + Arrays.toString(walkFrames));
        return new Animation<>(0.225f, walkFrames);
    }

    private Animation<TextureRegion> rightAnimation() {
        return this.buildAnimation(RIGHT_ROW_INDEX);
    }

    private Animation<TextureRegion> leftAnimation() {
        return this.buildAnimation(LEFT_ROW_INDEX);
    }

    private Animation<TextureRegion> downAnimation() {
        return this.buildAnimation(DOWN_ROW_INDEX);
    }

    private Animation<TextureRegion> upAnimation() {
        return this.buildAnimation(UP_ROW_INDEX);
    }

    public void setAnimation(String direction) {
        switch (direction) {
            case "up":
                this.animation = upAnimation();
                break;
            case "down":
                this.animation = downAnimation();
                break;
            case "left":
                this.animation = leftAnimation();
                break;
            case "right":
                this.animation = rightAnimation();
                break;
            default:
                this.animation = idleAnimation();
                break;
        }
    }

    public void settMovementTimer() {
        this.movementTimer += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getKeyFrame() {
        return this.animation.getKeyFrame(this.movementTimer, this.isMoving);
    }

    public void setIsMoving(boolean _b) {
        this.isMoving = _b;
    }
}
