package com.alexdiru.redleaf;

import com.alexdiru.redleaf.screens.ScoreScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class RedLeafEngine implements Disposable {

	private MusicManager mMusicManager;
	private DataPlayer mPlayer;
	private DataSong mSong;
	private DataCountdownTimer mCountdownTimer;
	private GameState mGameState;
	private UserInterface mUserInterface;
	private RedLeafGame mGame;
	
	public RedLeafEngine(RedLeafGame game) {
		mGame = game;
		game.setEngine(this);
	}
	
	@Override
	public void dispose() {
		mMusicManager.dispose();
		mPlayer.dispose();
		mSong.dispose();
		mCountdownTimer.dispose();
		mUserInterface.dispose();
	}
	
	public void setup() {
		//ColourSchemeAssets.initialise(new ColourScheme(ColourScheme.ThemeType.DISCOVERY), 200);
		
		mGameState = new GameState();
		mMusicManager = new MusicManager();
		mPlayer = new DataPlayer();
		mCountdownTimer = new DataCountdownTimer();
		mUserInterface = new UserInterface();
		
		mSong = Utils.getCurrentSong();
		mSong.mMusicManager = mMusicManager;
		
		
		
		Gdx.input.setInputProcessor(new GameInputProcessor(mPlayer, mMusicManager, mGame));
	}
	
	public void update(long elapsedTime) {
		switch (mGameState.get()) {
		case GameState.STATE_GAME:
			mSong.updateNotes(mMusicManager.getPlayPosition(), 1280, elapsedTime);
			mPlayer.update(mMusicManager.getPlayPosition());
			break;
			
		case GameState.STATE_COUNTDOWN:
			mSong.updateNotes(0, 1280, elapsedTime);
			mCountdownTimer.update();
			
			if (mCountdownTimer.hasFinished()) {
				mGameState.set(GameState.STATE_GAME);
				mMusicManager.play();
			}
			break;
		}
	}
	
	public void renderBackgroundLayer(SpriteBatch spriteBatch) {
		mPlayer.render(spriteBatch);
	}
	
	public void renderNoteLayer(SpriteBatch spriteBatch) {
		mSong.render(spriteBatch);
	}
	
	public void renderUserInterfaceLayer(SpriteBatch spriteBatch) {
		
		mUserInterface.render(spriteBatch, mPlayer, mGameState);
		
		switch (mGameState.get()) {
		case GameState.STATE_COUNTDOWN:
			mCountdownTimer.render(spriteBatch);
			break;
		}
	}
	

	public void renderUserInterfaceLayer(ShapeRenderer shapeRenderer) {
		mUserInterface.renderBackground(shapeRenderer);
	}
	
	public void renderBackgroundLayer(ShapeRenderer shapeRenderer) {
		mPlayer.render(shapeRenderer);
	}

	public MusicManager getMusicManager() {
		return mMusicManager;
	}
	
	public DataPlayer getPlayer() {
		return mPlayer;
	}
	
	public DataSong getSong() {
		return mSong;
	}

	public void inGamePause() {
		mGameState.set(GameState.STATE_PAUSED);
		mMusicManager.pause();
	}

	public GameState getState() {
		return mGameState;
	}

	public void inGameResume() {
		mMusicManager.play();
		mGameState.set(GameState.STATE_GAME);
	}
}
