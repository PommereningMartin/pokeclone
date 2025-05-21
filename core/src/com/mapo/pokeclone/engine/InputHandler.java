package com.mapo.pokeclone.engine;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;


public class InputHandler extends InputAdapter {


    public InputHandler() {
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.A:
                CalculatePositions.setLeftMove(true);
                CalculatePositions.player.setIsMoving(true);
                return true;
            case Keys.D:
                CalculatePositions.setRightMove(true);
                CalculatePositions.player.setIsMoving(true);
				return true;
            case Keys.W:
                CalculatePositions.setTopMove(true);
                CalculatePositions.player.setIsMoving(true);
				return true;
            case Keys.S:
                CalculatePositions.setBottomMove(true);
                CalculatePositions.player.setIsMoving(true);
				return true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.A:
                CalculatePositions.setLeftMove(false);
                CalculatePositions.player.setIsMoving(false);
                break;
            case Keys.D:
                CalculatePositions.setRightMove(false);
                CalculatePositions.player.setIsMoving(false);
                break;
            case Keys.W:
                CalculatePositions.setTopMove(false);
                CalculatePositions.player.setIsMoving(false);
                break;
            case Keys.S:
                CalculatePositions.setBottomMove(false);
                CalculatePositions.player.setIsMoving(false);
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
