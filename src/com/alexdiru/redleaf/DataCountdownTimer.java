package com.alexdiru.redleaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class DataCountdownTimer implements Disposable{
	
	private int mTime;
	private BitmapFont mFont;
	private long mPreviousTime;
	private long mCurrentTime;
	
	public DataCountdownTimer() {
		mFont = new BitmapFont();
		mFont.setScale(8f, 8f);
		mFont.setColor(Color.MAGENTA);
		mTime = 2999;
		
		mPreviousTime = mCurrentTime = System.nanoTime()/1000000;
	}
	
	@Override 
	public void dispose() {
		mFont.dispose();
	}
	
	public void update() {
		update(mCurrentTime - mPreviousTime);
		mPreviousTime = mCurrentTime;
		mCurrentTime = System.nanoTime()/1000000;
	}
	
	private void update(long elapsedTime) {
		mTime -= (int)elapsedTime;
	}

	public void render(SpriteBatch spriteBatch) {
		Texture texture = ColourSchemeAssets.getCountdownTexture(mTime/1000);
		int scaledWidth = UtilsScreenSize.scaleX(texture.getWidth());
		int x = (Gdx.graphics.getWidth() - scaledWidth) >> 1;
		int scaledHeight = UtilsScreenSize.scaleX(texture.getHeight());
		int y = (Gdx.graphics.getHeight() - scaledHeight) >> 1;
		UtilsDraw.draw(spriteBatch, texture, x, y, scaledWidth, scaledHeight);
	}
	
	public boolean hasFinished() {
		return mTime <= 0;
	}
}
