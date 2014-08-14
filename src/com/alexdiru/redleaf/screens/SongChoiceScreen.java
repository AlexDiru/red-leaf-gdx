package com.alexdiru.redleaf.screens;

import java.util.concurrent.Callable;

import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.UtilsScreenSize;
import com.alexdiru.redleaf.UtilsSettings;
import com.alexdiru.redleaf.screens.Button.ButtonType;
import com.badlogic.gdx.Gdx;

public class SongChoiceScreen extends MenuScreen {

	private Button mTrackSelect;
	private Button mPerfection;
	private Button mWorldIsMad;
	
	public SongChoiceScreen(final RedLeafGame game) {
		super(game, true);
	
		mTrackSelect = new Button(ButtonType.TRACKSELECT, null, false);
		
		mPerfection = new Button(ButtonType.PERFECTION, new Callable<Void>() {
			public Void call() {
				game.setScreen(new DifficultyChoiceScreen(game, "Perfection"));
				return null;
			}
		});
		
		mWorldIsMad = new Button(ButtonType.WORLDISMAD, new Callable<Void>() {
			public Void call() {
				game.setScreen(new DifficultyChoiceScreen(game, "WorldIsMad"));
				return null;
			}
		});
		
		mButtons.add(mPerfection);
		if (UtilsSettings.mDebug)
			mButtons.add(mWorldIsMad);	
	}

	public void render(float delta) {
		mBatch.begin();
		super.render(delta);
		mTrackSelect.render(mBatch, Gdx.graphics.getHeight() - mTrackSelect.getHeight());
		mPerfection.render(mBatch,Gdx.graphics.getHeight() - UtilsScreenSize.scaleY(190) - mPerfection.getHeight());
		if (UtilsSettings.mDebug)
			mWorldIsMad.render(mBatch,Gdx.graphics.getHeight() - UtilsScreenSize.scaleY(190) - mPerfection.getHeight() - mWorldIsMad.getHeight());
		mBatch.end();
	}
}
