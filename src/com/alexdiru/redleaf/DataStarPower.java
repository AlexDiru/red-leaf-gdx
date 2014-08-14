package com.alexdiru.redleaf;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class DataStarPower implements Disposable{

	private static int mDuration = 10000;
	
	private int mNoteStreak = 0;
	private int mNumberAvailable = 0;
	private boolean mActive = false;
	private DataBoundingBox mBoundingBox;
	private int mTimeOfActivation;
	
	private int mTextureHeight;
	private int mTextureWidth;
	
	public DataStarPower() {
		
		mTextureHeight = UtilsScreenSize.scaleY(300);
		mTextureWidth = mTextureHeight;
		
		mBoundingBox = new DataBoundingBox(ColourSchemeAssets.getStarPower());
		mBoundingBox.update(UtilsScreenSize.getScreenWidth() / 2 - mTextureWidth / 2,
				UtilsScreenSize.getScreenHeight() / 2, UtilsScreenSize.getScreenWidth() / 2 + mTextureWidth / 2,
				UtilsScreenSize.getScreenHeight() / 2 + mTextureHeight);
	}
	
	@Override
	public void dispose() {
		mBoundingBox.dispose();
	}

	public void update(int currentTime) {
		if (mActive && mTimeOfActivation + mDuration < currentTime)
			end();
	}
	
	public void activateStarPower(int currentTime) {
		if (mNumberAvailable > 0)
			if (!mActive)
				start(currentTime);
	}

	public void handleTouch(int x, int y, int currentTime) {
		if (mBoundingBox.isTouched(x, y))
			activateStarPower(currentTime);
	}
	

	private void start(int currentTime) {
		mActive = true;
		mNumberAvailable--;
		mTimeOfActivation = currentTime;
	}

	private void end() {
		mActive = false;
	}

	
	public void render(SpriteBatch spriteBatch) {
		if (mNumberAvailable > 0) 
			mBoundingBox.render(spriteBatch);
	}


	public boolean isActive() {
		return mActive;
	}


	public void increaseStreak() {
		mNoteStreak++;
	}
	
	public void increaseNumberAvailable() {
		mNumberAvailable++;
	}


	public int getStreak() {
		return mNoteStreak;
	}


	public void resetStreak() {
		mNoteStreak = 0;
	}



}
