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
    public boolean rightMove = false;
    public boolean leftMove = false;
    public boolean topMove = false;
    public boolean bottomMove = false;
    private Animation<TextureRegion> animation;
    private float movementTimer = 0.0f;
    protected Vector2 velocity = new Vector2();
    protected Texture texture;
    private TextureRegion[][] tmp;
    private static final Integer IDLE_ROW_INDEX = -1;
    private static final Integer DOWN_ROW_INDEX = 0;
    private static final Integer UP_ROW_INDEX = 1;
    private static final Integer RIGHT_ROW_INDEX = 2;
    private static final Integer LEFT_ROW_INDEX = 3;
    private boolean isMoving = false;
    // store the animations for each direction
    private Animation<TextureRegion> topAnimation = null;
    private Animation<TextureRegion> downAnimation = null;
    private Animation<TextureRegion> leftAnimation = null;
    private Animation<TextureRegion> rightAnimation = null;
    private Animation<TextureRegion> idleAnimation = null;

    public Entity(){
        super();;
    }

    public void init() {
        this.tmp = TextureRegion.split(this.texture, 16, 16);
        topAnimation = this.buildAnimation(UP_ROW_INDEX);
        downAnimation = this.buildAnimation(DOWN_ROW_INDEX);
        leftAnimation = this.buildAnimation(LEFT_ROW_INDEX);
        rightAnimation = this.buildAnimation(RIGHT_ROW_INDEX);
        idleAnimation = this.buildAnimation(IDLE_ROW_INDEX);
        this.animation = this.idleAnimation; // default animation
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
        return new Animation<>(0.225f, walkFrames);
    }

    public void setAnimation(String direction) {
        switch (direction) {
            case "up":
                this.animation = this.topAnimation;
                break;
            case "down":
                this.animation = this.downAnimation;
                break;
            case "left":
                this.animation = this.leftAnimation;
                break;
            case "right":
                this.animation = this.rightAnimation;
                break;
            default:
                this.animation = this.idleAnimation;
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

    public Vector2 getVelocity() {
        return velocity;
    }

    public final void setVelocity(float xy) {
        velocity.x = xy;
        velocity.y = xy;
    }
}
