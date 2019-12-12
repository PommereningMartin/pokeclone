package com.mygame.engine;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;


public class InputHandler implements InputProcessor{
	
	public InputHandler(){}

	@Override
    public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
        case Keys.A:
        	CalculatePositions.setLeftMove(true);
            break;
        case Keys.D:
        	CalculatePositions.setRightMove(true);
            break;
        case Keys.W:
        	CalculatePositions.setTopMove(true);
        	break;
        case Keys.S:
        	CalculatePositions.setBottomMove(true);
        	break;
        }
        return true;
    }
    @Override
    public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
        case Keys.A:
        	CalculatePositions.setLeftMove(false);
            break;
        case Keys.D:
        	CalculatePositions.setRightMove(false);
            break;
        case Keys.W:
        	CalculatePositions.setTopMove(false);
        	break;
        case Keys.S:
        	CalculatePositions.setBottomMove(false);
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
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
