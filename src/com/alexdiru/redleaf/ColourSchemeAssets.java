package com.alexdiru.redleaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/** Stores assets dependent on the colour scheme chosen
 * 
 * @author Alex */
public class ColourSchemeAssets {

	// Tapbox Textures
	private static Texture[] LOST;
	private static Texture[] LOSTInverse;

	// Note Texture
	private static Texture[] mNoteTexture;

	private static Texture[] mNoteStarTexture;

	private static Texture[] mNoteHeldTexture;

	// Background
	private static Texture mBackgroundTexture;
	private static Texture mBackgroundStarTexture;

	private static Texture[] mHoldLineUnheldPaint;
	private static Texture[] mHoldLineHeldPaint;
	private static Texture[] mHoldLineStarUnheldPaint;

	private static Texture mStarPowerTexture;
	
	private static Texture[] mCountdownTimerTexture;

	private static Texture mNoteTappedTexture;
	
	private static BitmapFont mUserInterfaceFont;
	
	private static BitmapFont mSpeedChangeFont;
	
	public static void dispose() {
		LOST = LOSTInverse = mNoteTexture = mNoteStarTexture = mNoteHeldTexture = null;
		mBackgroundTexture = mBackgroundStarTexture = mStarPowerTexture = mNoteTappedTexture = null;
		mHoldLineUnheldPaint = mHoldLineHeldPaint = mHoldLineStarUnheldPaint = mCountdownTimerTexture = null;
		mUserInterfaceFont = mSpeedChangeFont = null;
	}
	
	public static void initialise(ColourScheme colourScheme, int tapBoxHeight) {
		
		mCountdownTimerTexture = new Texture[3];
		mHoldLineStarUnheldPaint = new Texture[4];
		mHoldLineHeldPaint = new Texture[4];
		mHoldLineUnheldPaint = new Texture[4];
		LOST = new Texture[4];
		LOSTInverse = new Texture[4];
		mNoteTexture = new Texture[4];
		mNoteStarTexture = new Texture[4];
		mNoteHeldTexture = new Texture[4];
		
		mUserInterfaceFont = BitmapFontLoader.loadFont(colourScheme.mUserInterfaceFont);
		mUserInterfaceFont.setColor(1f, 1f, 1f, 0.8f);

		mSpeedChangeFont = BitmapFontLoader.loadFont(colourScheme.mSpeedChangeFont);
		
		// Tapboxes
		for (int i = 0; i < 4; i++) {
			LOST[i] = new Texture(Gdx.files.internal(colourScheme.mTap[i]));
			LOSTInverse[i] = new Texture(Gdx.files.internal(colourScheme.mTapHold[i]));
		}
		
		mNoteTappedTexture = new Texture(Gdx.files.internal(colourScheme.mNoteTapped));

		// Adjust the width of the tapboxes to maintain the same ratio as used
		// in the image that represents them

		Gdx.app.log("px", UtilsScreenSize.scaleY(DataPlayer.getUnscaledTapBoxHeight()) + "|");
		int scaledTapBoxWidth = (int)(UtilsScreenSize.scaleY(DataPlayer.getUnscaledTapBoxHeight()) * 0.7f);
		int scaledNoteWidth = (int)(scaledTapBoxWidth * 0.85f);
		DataPlayer.setScaledTapBoxWidth(scaledTapBoxWidth);
		DataNote.setNotePixelHeight(scaledNoteWidth);

		// New the width has been implemented we can calculate the gap
		DataPlayer.setScaledTapBoxGap((UtilsScreenSize.getScreenWidth() - (DataPlayer.getScaledTapBoxWidth() * 4)) / 5);

		mBackgroundTexture = new Texture(Gdx.files.internal(colourScheme.mBackground));
		mBackgroundStarTexture = new Texture(Gdx.files.internal(colourScheme.mBackgroundStar));
		
			Gdx.app.log("star", colourScheme.mBackground + " & " + colourScheme.mBackgroundStar);
		
		mStarPowerTexture = new Texture(Gdx.files.internal(colourScheme.mStarPower));

		// Load all of the tap box Textures (non-hold and hold) and note Textures
		for (int i = 0; i < 4; i++) {
			mNoteTexture[i] = new Texture(Gdx.files.internal(colourScheme.mNote[i]));
			//mNoteTexture[i].setFilter( TextureFilter.Linear, TextureFilter.Linear );
			
			mNoteHeldTexture[i] = new Texture(Gdx.files.internal(colourScheme.mNoteHeld[i]));
			mNoteStarTexture[i] = new Texture(Gdx.files.internal(colourScheme.mNoteStar[i]));

			// Load the held and unheld notes
			mHoldLineHeldPaint[i] = new Texture(Gdx.files.internal(colourScheme.mNoteStreamHeld[i]));
			mHoldLineStarUnheldPaint[i] = new Texture(Gdx.files.internal(colourScheme.mNoteStreamUnheldStar[i]));
			mHoldLineUnheldPaint[i] = new Texture(Gdx.files.internal(colourScheme.mNoteStreamUnheld[i]));
		}

		for (int i = 0; i < 3; i++)
			mCountdownTimerTexture[i] = new Texture(Gdx.files.internal(colourScheme.mCountdownTimer[i]));
		
		colourScheme.dispose();
	}

	/** Gets the background which will be drawn
	 * 
	 * @param starPowerActive If the player has activated their star power
	 * @return The background */
	public static Texture getBackground(boolean starPowerActive) {
		if (starPowerActive) 
			return mBackgroundStarTexture;
		
		return mBackgroundTexture;
	}

	public static Texture getNote(int position) {
		return mNoteTexture[position];
	}

	public static Texture getNoteStar(int position) {
		return mNoteStarTexture[position];
	}

	public static Texture getTapBox(int position) {
		return LOST[position];
	}

	public static Texture getTapBoxHeld(int position) {
		return LOSTInverse[position];
	}

	public static Texture getHoldLineUnheldPaint(int position, boolean isStarNote) {
		if (isStarNote)
			return mHoldLineStarUnheldPaint[position];
		return mHoldLineUnheldPaint[position];
	}

	public static Texture getHoldLineHeldPaint(int position) {
		return mHoldLineHeldPaint[position];
	}

	public static Texture getNoteHeld(int position) {
		return mNoteHeldTexture[position];
	}

	public static Texture getCountdownTexture(int time) {
		return mCountdownTimerTexture[time];
	}


	public static Texture getStarPower() {
		return mStarPowerTexture;
	}

	public static Texture getNoteTapped() {
		return mNoteTappedTexture;
	}

	public static BitmapFont getUserInterfaceFont() {
		return mUserInterfaceFont;
	}

	public static BitmapFont getSpeedChangeFont() {
		return mSpeedChangeFont;
	}
}
