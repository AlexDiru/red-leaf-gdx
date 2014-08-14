package com.alexdiru.redleaf.screens;

import com.alexdiru.redleaf.BitmapFontLoader;
import com.alexdiru.redleaf.UtilsScreenSize;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

public abstract class MenuAssets {
	
	public static boolean mSetup = false;
	private static TextButtonStyle mTextButtonStyle;
	
	private static TextButtonStyle mMainMenuPlayButtonStyle;
	private static TextButtonStyle mMainMenuCreditsButtonStyle;
	private static TextButtonStyle mMainMenuOptionsButtonStyle;
	private static TextButtonStyle mMainMenuExitButtonStyle;
	
	private static Texture mMenuBackground;
	private static Texture mCreditsBackground;
	
	public static void setup() {
		//mTextButtonStyle = new TextButtonStyle();
		//mTextButtonStyle.font = BitmapFontLoader.loadFont("whitesilverarial");
		//mTextButtonStyle.up = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("note.png"))));
		//mTextButtonStyle.down = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("note2.png"))));
		
		mMainMenuPlayButtonStyle = new TextButtonStyle();
		//Texture mainMenuTexture = new Texture(Gdx.files.internal("menu_background.jpg"));
		//mMainMenuPlayButtonStyle.font = BitmapFontLoader.loadFont("whitesilverarial");
		mMainMenuPlayButtonStyle.up = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/play.png"))));
		mMainMenuPlayButtonStyle.down = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/playdown.png"))));
	
		mMainMenuCreditsButtonStyle = new TextButtonStyle();
		mMainMenuCreditsButtonStyle.font = BitmapFontLoader.loadFont("whitesilverarial");
		mMainMenuCreditsButtonStyle.up = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/credits.png"))));
		mMainMenuCreditsButtonStyle.down = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/creditsdown.png"))));

		mMainMenuOptionsButtonStyle = new TextButtonStyle();
		mMainMenuOptionsButtonStyle.font = BitmapFontLoader.loadFont("whitesilverarial");
		mMainMenuOptionsButtonStyle.up = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/options.png"))));
		mMainMenuOptionsButtonStyle.down = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/optionsdown.png"))));
		
		mMainMenuExitButtonStyle = new TextButtonStyle();
		mMainMenuExitButtonStyle.font = BitmapFontLoader.loadFont("whitesilverarial");
		mMainMenuExitButtonStyle.up = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/exit.png"))));
		mMainMenuExitButtonStyle.down = new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("menu/exitdown.png"))));

		mMenuBackground = new Texture(Gdx.files.internal("menu/background.jpg"));
		mCreditsBackground = new Texture(Gdx.files.internal("menu/backgroundcredits.jpg"));
		
		////DO NOT REMOVE////////DO NOT REMOVE////
		/*DO NOT REMOVE*/mSetup = true;///////////
		/////////////////DO NOT REMOVE////////////
	}
	
	public static void dispose() {
		
	}
	
	private static void checkSetup() {
		if (!mSetup)
			setup();
	}
	
	public static Texture getMenuBackground() {
		checkSetup();
		
		return mMenuBackground;
	}

	
	public static Texture getCreditsBackground() {
		checkSetup();
		return mCreditsBackground;
	}
	
	public static TextButtonStyle getTextButtonStyle() {
		checkSetup();		
		return mTextButtonStyle;
	}
	
	public static TextButtonStyle getMainMenuPlayButtonStyle() {
		checkSetup();
		return mMainMenuPlayButtonStyle;
	}
	
	public static TextButtonStyle getMainMenuCreditsButtonStyle() {
		checkSetup();
		return mMainMenuCreditsButtonStyle;
	}
	
	public static TextButtonStyle getMainMenuOptionsButtonStyle() {
		checkSetup();
		return mMainMenuOptionsButtonStyle;
	}
	
	public static TextButtonStyle getMainMenuExitButtonStyle() {
		checkSetup();
		return mMainMenuExitButtonStyle;
	}
}