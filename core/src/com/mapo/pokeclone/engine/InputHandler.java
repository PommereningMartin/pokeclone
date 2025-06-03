package com.mapo.pokeclone.engine;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.mapo.pokeclone.entitys.Entity;


public class InputHandler extends InputAdapter {
    private boolean keyPressed;
    private Entity thingToControll = null;

    public InputHandler(Entity entity) {
        keyPressed = false;
        thingToControll = entity;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!keyPressed) {
            switch (keycode) {
                case Keys.A:
                    thingToControll.leftMove = true;
                    thingToControll.setIsMoving(true);
                    keyPressed = true;
                    return true;
                case Keys.D:
                    thingToControll.rightMove = true;
                    thingToControll.setIsMoving(true);
                    keyPressed = true;
                    return true;
                case Keys.W:
                    thingToControll.topMove = true;
                    thingToControll.setIsMoving(true);
                    keyPressed = true;
                    return true;
                case Keys.S:
                    thingToControll.bottomMove = true;
                    thingToControll.setIsMoving(true);
                    keyPressed = true;
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.A:
                thingToControll.leftMove = false;
                thingToControll.setIsMoving(false);
                keyPressed = false;
                break;
            case Keys.D:
                thingToControll.rightMove = false;
                thingToControll.setIsMoving(false);
                keyPressed = false;
                break;
            case Keys.W:
                thingToControll.topMove = false;
                thingToControll.setIsMoving(false);
                keyPressed = false;
                break;
            case Keys.S:
                thingToControll.bottomMove = false;
                thingToControll.setIsMoving(false);
                keyPressed = false;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
