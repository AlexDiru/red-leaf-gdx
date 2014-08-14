package com.alexdiru.redleaf.screens;

import com.alexdiru.redleaf.GameState;
import com.alexdiru.redleaf.RedLeafEngine;
import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.UtilsGradeCalculator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen extends IScreen {

	private SpriteBatch batch;
	private ShapeRenderer mShapeRenderer;
	private RedLeafEngine mGameEngine;
	private OrthographicCamera camera;
	private RedLeafGame mGame;
	
	private String mSongName;

	/** Current time that the song is at, assigned in the update method, used in the draw method */
	private int mCurrentTime;

	// / Debug Variables ///
	// Variables for recording the FPS
	private long mPreviousTime = 0, mCurrentTimeFPS = 0, mFPS, mTotalFPS = 0, mLoopCount = 0;
	
	public void inGamePause() {
		Gdx.app.log("lifeccc", "pausing");
		mGameEngine.inGamePause();
	}

	public void inGameResume() {
		Gdx.app.log("lifeccc", "resuming");
		//mGameEngine.inGameResume();
	}

	public String getSongName() {
		return mSongName;
	}
	
	public GameScreen(RedLeafGame game, String songName) {
		super(game);
		
		mSongName = songName;
		mGame = game;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w,h);
        camera.position.set(w/2,h/2, 0);
        camera.update();
		
        batch = new SpriteBatch();
        mShapeRenderer = new ShapeRenderer();
		mGameEngine = new RedLeafEngine(game);
		mGameEngine.setup();
	}
	
	@Override
	public boolean isGameScreen() {
		return true;
	}


	@Override
	public void dispose() {
		batch.dispose();
		mShapeRenderer.dispose();
		mGameEngine.dispose();
	}
	
	@Override
	public void render(float delta) {
		try {
			render();
		} catch (IllegalStateException ex) {
			batch.end();
		} catch (NullPointerException ex) {
			//When back is pressed from pause menu a render can happen after the textures have been disposed
		}
	}
	
	private void render() {
		
		
		mPreviousTime = mCurrentTimeFPS;
		mCurrentTimeFPS = System.nanoTime()/1000000;
		
		if (mPreviousTime != 0) {
			mFPS = (int) (1000 * ((float) 1 / (float) (mCurrentTimeFPS - mPreviousTime)));
			mTotalFPS += mFPS;
			mLoopCount++;
		}
		

		//Update
		mGameEngine.update(mCurrentTimeFPS - mPreviousTime);
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		// Background Layer
		batch.begin();
		mGameEngine.renderBackgroundLayer(batch);
		batch.end();

		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		mShapeRenderer.setProjectionMatrix(camera.combined);
		mShapeRenderer.begin(ShapeType.FilledRectangle);
		mGameEngine.renderBackgroundLayer(mShapeRenderer);
		mShapeRenderer.end();

		// Note Layer
		batch.begin();
		mGameEngine.renderNoteLayer(batch);
		batch.end();
		

		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		// UI Layer
		mShapeRenderer.begin(ShapeType.FilledRectangle);
		mGameEngine.renderUserInterfaceLayer(mShapeRenderer);
		mShapeRenderer.end();
		
		batch.begin();
		mGameEngine.renderUserInterfaceLayer(batch);
		batch.end();

		Gdx.gl.glDisable(GL10.GL_BLEND);
		
		if (mGameEngine.getMusicManager().isSongOver())
			mGame.setScreen(new ScoreScreen(mGame, mGameEngine.getPlayer().getScore(), UtilsGradeCalculator.calculateGrade(mGameEngine.getSong(), mGameEngine.getPlayer()), mGameEngine.getSong().mSongName));
	}
	
}
