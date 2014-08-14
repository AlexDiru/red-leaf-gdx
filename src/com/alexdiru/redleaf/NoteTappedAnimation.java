package com.alexdiru.redleaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NoteTappedAnimation {

	private static final int MS_PER_FRAME = 15;
	private static final int FRAME_NUMBER = 30;
	private static final float SCALE_PER_FRAME = 0.033f;
	
	private int mFrame = 0;
	private int mCurrentFrameAnimationMS = 0;
	private int mPosition = 0;
	
	private boolean mActive = false;
	
	public NoteTappedAnimation() {
		kill();
	}
	
	public void spawn(int position) {
		mPosition = position;
		mActive = true;
	}
	
	public void kill() {
		mActive = false;
		mFrame = 0;
		mCurrentFrameAnimationMS = 0;
	}
	
	public void update(long elapsedTime) {
		if (!mActive)
			return;
		
		mCurrentFrameAnimationMS += elapsedTime;
		if (mCurrentFrameAnimationMS > MS_PER_FRAME) {
			mFrame++;
			mCurrentFrameAnimationMS -= MS_PER_FRAME;
		}
		
		//Check 
		if (mFrame > FRAME_NUMBER)
			kill();
	}
	
	public void render(SpriteBatch spriteBatch, DataPlayer player) {
		float S = (1f + (float)SCALE_PER_FRAME * (float)mFrame);
		int W = (int)(S * DataNote.getNotePixelHeight());
		float u = (W - DataNote.getNotePixelHeight()) >> 1;
		int x = player.getBoundingBoxLeft(mPosition) + ((DataPlayer.getScaledTapBoxWidth() - DataNote.getNotePixelHeight()) >> 1);
		UtilsDraw.draw(spriteBatch, ColourSchemeAssets.getNoteTapped(), x - (int)u, UtilsScreenSize.scaleY(DataPlayer.mUnscaledTapBoxY) - (int)u, W,W);
	}

	public boolean isActive() {
		return mActive;
	}
}
