package com.alexdiru.redleaf.screens;

import java.util.concurrent.Callable;

import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.screens.Button.ButtonType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class MenuScreen extends IScreen {
	
	private Button mBackButton = null;
	
	protected MenuScreen(final RedLeafGame game, boolean useBackButton) {
		super(game); 
		
		final MenuScreen screen = this;
		
		if (useBackButton) {
			mBackButton = new Button(ButtonType.BACK, new Callable<Void>() {
				public Void call() {
					goBack(screen);
					return null;
				}
			});
			mButtons.add(mBackButton);
		}
	}

	public void goBack(MenuScreen screen) {
		//Set the back screen according to current screen
		if (screen instanceof CreditsScreen)
			game.setCurrentScreen(new MainMenuScreen(game));
		else if (screen instanceof SongChoiceScreen || screen instanceof OptionsScreen)
			game.setCurrentScreen(new MainMenuScreen(game));
		else if (screen instanceof DifficultyChoiceScreen)
			game.setCurrentScreen(new SongChoiceScreen(game));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f,0f,0.5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
		
		if (mBackButton != null) {
			mBackButton.render(mBatch, 0, 0);
		}
	}
}
