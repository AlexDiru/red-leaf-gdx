package com.alexdiru.redleaf;
import com.alexdiru.redleaf.screens.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
public class RedLeafGame extends Game implements ApplicationListener {
	
	private IScreen mCurrentScreen = null;
	private IScreen mPreviousScreen = null;
	private RedLeafEngine mEngine;
		
	@Override
	public void create() {	
		UtilsScreenSize.initialise();
		MenuAssets.setup();
		Gdx.input.setCatchBackKey(true);
		UtilsSettings.load();
		mCurrentScreen = new MainMenuScreen(this);
	}
	
	public void setCurrentScreen(IScreen screen) {
		if (screen instanceof GameScreen)
			MenuAssets.dispose();
		else
			ColourSchemeAssets.dispose();
		mPreviousScreen = mCurrentScreen;
		mCurrentScreen = screen;
	}
	
	
	@Override
	public void render() {
		super.render();
		mCurrentScreen.render(Gdx.graphics.getDeltaTime());
	}


	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		if (mCurrentScreen instanceof GameScreen)
			((GameScreen)mCurrentScreen).inGamePause();
		
	}

	@Override
	public void resume() {
		if (mCurrentScreen instanceof GameScreen)
			((GameScreen)mCurrentScreen).inGameResume();
	}
	
	@Override 
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	public RedLeafEngine getEngine() {
		return mEngine;
	}

	public void setEngine(RedLeafEngine engine) {
		mEngine = engine;
	}

	public String getSongName() {
		if (mCurrentScreen instanceof GameScreen) 
			return ((GameScreen)mCurrentScreen).getSongName();
		
		try {
			throw new Exception("NO SCREEN FOUND");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}

