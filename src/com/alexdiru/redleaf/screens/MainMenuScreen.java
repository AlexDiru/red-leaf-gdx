package com.alexdiru.redleaf.screens;

import java.util.concurrent.Callable;

import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.UtilsSettings;
import com.alexdiru.redleaf.screens.Button.ButtonType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MainMenuScreen extends MenuScreen {
	
	private Button mPlayButton;
	private Button mCreditsButton;
	private Button mOptionsButton;
	private Button mExitButton;
	private Button mLanguageButton;
	
	public MainMenuScreen(final RedLeafGame game) {
		super(game, false);
		
		final Screen screen = this;
		
		mPlayButton = new Button(ButtonType.PLAY, new Callable<Void>() {
			public Void call() {
				game.setScreen(new SongChoiceScreen(game));
				return null;
			}
		});
		
		mCreditsButton = new Button(ButtonType.CREDITS, new Callable<Void>() {
			public Void call() {
				game.setScreen(new CreditsScreen(game));
				return null;
			}
		});
		
		mOptionsButton = new Button(ButtonType.OPTIONS, new Callable<Void>() {
			public Void call() {
				game.setScreen(new OptionsScreen(game));
				return null;
			}
		});
		
		mExitButton = new Button(ButtonType.EXIT, new Callable<Void>() {
			public Void call() {
				Gdx.app.exit();	
				return null;
			}
		});
		
		mLanguageButton = new Button(ButtonType.LANGUAGE, new Callable<Void>() {
			public Void call() {
				Gdx.app.log("", "from: " + UtilsSettings.mLanguage);
				UtilsSettings.switchLanguage();
				Gdx.app.log("", "to: " + UtilsSettings.mLanguage);
				reloadButtonTextures(); //Reload Textures
				return null;
			}
		});

		mButtons.add(mPlayButton);
		mButtons.add(mCreditsButton);
		mButtons.add(mOptionsButton);
		mButtons.add(mExitButton);
		mButtons.add(mLanguageButton);
	}
	
	public void render(float delta) {
		mBatch.begin();
		super.render(delta);
		mPlayButton.render(mBatch,Gdx.graphics.getHeight() - mPlayButton.getHeight() );
		mCreditsButton.render(mBatch,Gdx.graphics.getHeight() - mPlayButton.getHeight() - mCreditsButton.getHeight());
		mOptionsButton.render(mBatch, Gdx.graphics.getHeight() - mPlayButton.getHeight() - mCreditsButton.getHeight() - mOptionsButton.getHeight());
		mExitButton.render(mBatch, Gdx.graphics.getHeight() - mPlayButton.getHeight() - mCreditsButton.getHeight() - mOptionsButton.getHeight() - mExitButton.getHeight());
		mLanguageButton.render(mBatch, Gdx.graphics.getWidth() - mLanguageButton.getWidth(), 0);
		
		
		mBatch.end();
	}
	
}
