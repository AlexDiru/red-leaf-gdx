
package com.alexdiru.redleaf;

import com.alexdiru.redleaf.screens.DifficultyChoiceScreen;
import com.alexdiru.redleaf.screens.ScoreScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

	private DataPlayer mPlayer;
	private MusicManager mMusicManager;
	private RedLeafGame mGame;
	
	public static final int MAX_TOUCHES = 4;
	public static int mTouchUpCounter;
	
	public GameInputProcessor(DataPlayer player, MusicManager musicManager, RedLeafGame game) {
		mPlayer = player;
		mMusicManager = musicManager;
		mGame = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			if (mGame.getEngine().getState().get() == GameState.STATE_GAME)
				mGame.getEngine().inGamePause();
			else if (mGame.getEngine().getState().get() == GameState.STATE_PAUSED)
				mGame.setCurrentScreen(new DifficultyChoiceScreen(mGame, mGame.getSongName()));
		}
		
		
		if (mGame.getEngine().getState().get() == GameState.STATE_GAME)
			mPlayer.handleKeyDown(keycode, mMusicManager.getPlayPosition());
		
		return false;
	}
	
	private void togglePause() {
		if (mGame.getEngine().getState().get() == GameState.STATE_GAME)
			mGame.getEngine().inGamePause();
		else if (mGame.getEngine().getState().get() == GameState.STATE_PAUSED)
			mGame.getEngine().inGameResume();
	}

	@Override
	public boolean keyUp(int keycode) {

		if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
			mPlayer.unhold(0);
		else if (keycode == Input.Keys.UP || keycode == Input.Keys.D)
			mPlayer.unhold(2);
		else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
			mPlayer.unhold(1);
		else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.F)
			mPlayer.unhold(3);
		else if (keycode == Input.Keys.BACKSPACE)
			mGame.setScreen(new ScoreScreen(mGame, 1, "A", mGame.getEngine().getSong().mSongName));
		else if (keycode == Input.Keys.P) 
			togglePause();
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	
		if (mGame.getEngine().getState().get() == GameState.STATE_GAME)
			mPlayer.handleTouchDown(screenX, screenY, pointer, mMusicManager.getPlayPosition());
		
		if (screenY < UtilsScreenSize.getScreenHeight() >> 1)
			togglePause();
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		Gdx.app.log("touch", "touchUp " + pointer + " (" + screenX + ", " + screenY + ")");
		mPlayer.handleTouchUp(pointer);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

//		Gdx.app.log("touch", "touchDragged " + pointer + " (" + screenX + ", " + screenY + ")");
		return true;
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
