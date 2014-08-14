package com.alexdiru.redleaf.screens;

import java.util.ArrayList;

import com.alexdiru.redleaf.BitmapFontLoader;
import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//http://steigert.blogspot.co.uk/2012/02/2-libgdx-tutorial-game-screens.html
public abstract class IScreen implements Screen {
	protected final RedLeafGame game;
	protected final Stage stage;

	private BitmapFont mFont;
	protected SpriteBatch mBatch;
	private Skin mSkin;
	
	protected ArrayList<Button> mButtons = new ArrayList<Button>();
	
	protected void reloadButtonTextures() {
		for (Button button : mButtons) 
			button.reloadTexture();
	}

	public IScreen(RedLeafGame game) {
		Utils.setCurrentScreen(this);
		//Gdx.input.setCatchBackKey(true);
		
		this.game = game;
		this.stage = new Stage(0, 0, true);
		game.setCurrentScreen(this);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		stage.setViewport(w,h, true);
		
		//Force Sprite Batch creation
		getBatch();
		
		if (!isGameScreen())
			Gdx.input.setInputProcessor(new MenuInputProcessor(mButtons));
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	public BitmapFont getFont() {
		if (mFont == null) {
			mFont = BitmapFontLoader.loadFont("hi");
			mFont.setColor(1f, 1f, 1f, 0.8f);
		}
		return mFont;
	}

	public SpriteBatch getBatch() {
		if (mBatch == null) {
			mBatch = new SpriteBatch();
		}
		return mBatch;
	}

	protected Skin getSkin() {
		 if( mSkin == null ) {
			 mSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
		 }
		 return mSkin;
	}

	// Screen implementation

	@Override
	public void show() {
		// Gdx.app.log(Tyrian.LOG, "Showing screen: " + getName() );

		// set the input processor
	}

	@Override
	public void resize(int width, int height) {
		//Gdx.app.log(Tyrian.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);

		// resize and clear the stage
		//stage.clear();
	}

	@Override
	public void render(float delta) {
		mBatch.draw(MenuAssets.getMenuBackground(), 0	,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		if (mFont != null)
			mFont.dispose();
		if (mBatch != null)
			mBatch.dispose();
		if (mSkin != null)
			mSkin.dispose();
	}

	public boolean isGameScreen() {
		return false;
	}
}
