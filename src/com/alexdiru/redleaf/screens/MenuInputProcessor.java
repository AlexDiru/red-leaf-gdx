package com.alexdiru.redleaf.screens;

import java.util.ArrayList;

import com.alexdiru.redleaf.Utils;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class MenuInputProcessor implements InputProcessor {

	private ArrayList<Button> mButtons; 
	
	public MenuInputProcessor(ArrayList<Button> buttons) {
		mButtons = buttons;
	}

	public void addButton(Button button) {
		mButtons.add(button);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
	       if (Utils.getCurrentScreen() instanceof MenuScreen) {
	    	   if (Utils.getCurrentScreen() instanceof MainMenuScreen) {
	    		   Gdx.app.exit();
	    	   }
	    	   else {
	    		   ((MenuScreen)Utils.getCurrentScreen()).goBack((MenuScreen)Utils.getCurrentScreen());
	    	   }
	       }
	    }
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		for (int x = 0; x < mButtons.size(); x++) {
			if (mButtons.get(x).contains(screenX,screenY))
				mButtons.get(x).toggleHoldOn();
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		for (int x = 0; x < mButtons.size(); x++) {
			if (mButtons.get(x).isHeld()) {
				mButtons.get(x).toggleHoldOff();
				mButtons.get(x).activate();
			}
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for (int x = 0; x < mButtons.size(); x++) {
			if (mButtons.get(x).isHeld())
				if (!mButtons.get(x).contains(screenX,screenY))
					mButtons.get(x).toggleHoldOff();
		}

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
