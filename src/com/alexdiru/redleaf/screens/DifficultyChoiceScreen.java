package com.alexdiru.redleaf.screens;

import java.util.concurrent.Callable;

import com.alexdiru.redleaf.DataSong;
import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.Utils;
import com.alexdiru.redleaf.UtilsScreenSize;
import com.alexdiru.redleaf.UtilsSettings;
import com.alexdiru.redleaf.screens.Button.ButtonType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class DifficultyChoiceScreen extends MenuScreen {

	private Button mTrackSelect;
	private Button mEasyButton;
	private Button mMediumButton;
	private Button mHardButton;
	
	public DifficultyChoiceScreen(final RedLeafGame game, final String songName) {
		super(game, true);

		mTrackSelect = new Button(ButtonType.TRACKSELECT, null, false);
		
		mEasyButton = new Button(ButtonType.EASY, new Callable<Void>() {
			public Void call() {
				Utils.setDifficulty(0);
				Utils.setCurrentSong(new DataSong(songName + ".rl", Utils.getDifficulty()));
				game.setScreen(new GameScreen(game, songName));
				return null;
			}
		});
		
		mMediumButton = new Button(ButtonType.MEDIUM, new Callable<Void>() {
			public Void call() {
				Utils.setDifficulty(1);
				Utils.setCurrentSong(new DataSong(songName + ".rl", Utils.getDifficulty()));
				game.setScreen(new GameScreen(game, songName));
				return null;
			}
		});

		mHardButton = new Button(ButtonType.HARD, new Callable<Void>() {
			public Void call() {
				Utils.setDifficulty(2);
				Utils.setCurrentSong(new DataSong(songName + ".rl", Utils.getDifficulty()));
				game.setScreen(new GameScreen(game, songName));
				return null;
			}
		});

		if (UtilsSettings.mDebug)
			mButtons.add(mEasyButton);
		mButtons.add(mMediumButton);
		mButtons.add(mHardButton);
	}

	public void render(float delta) {
		mBatch.begin();
		super.render(delta);
		
		mTrackSelect.render(mBatch, Gdx.graphics.getHeight() - mTrackSelect.getHeight());

		if (UtilsSettings.mDebug)
			mEasyButton.render(mBatch,
					Gdx.graphics.getHeight() - UtilsScreenSize.scaleY(350) - mEasyButton.getHeight());
		mMediumButton.render(mBatch,
				Gdx.graphics.getHeight() - UtilsScreenSize.scaleY(420) - mEasyButton.getHeight()
						- mMediumButton.getHeight());
		mHardButton.render(
				mBatch,
				Gdx.graphics.getHeight() - UtilsScreenSize.scaleY(490) - mEasyButton.getHeight()
						- mMediumButton.getHeight()
						- mHardButton.getHeight());
		mBatch.end();
	}
}
