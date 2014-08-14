package com.alexdiru.redleaf;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class DataTapBox extends DataBoundingBox implements Disposable {

	private Texture mUnheldTexture;
	private Texture mHeldTexture;
	private boolean mIsHeld = false;
	
	public void setUnheldTexture(Texture Texture) {
		mUnheldTexture = Texture;
	}
	
	public void setHeldTexture(Texture Texture) {
		mHeldTexture = Texture;
	}
	
	public void hold() {
		mIsHeld = true;
	}
	
	public void unhold() {
		mIsHeld = false;
	}
	
	public void render(SpriteBatch spriteBatch) {
		if (mIsHeld)
			setRenderTexture(mHeldTexture);
		else 
			setRenderTexture(mUnheldTexture);
		
		super.render(spriteBatch);
	}
	
	public void update(int x1, int y1) {
		super.update(x1, y1, x1 + DataPlayer.getScaledTapBoxWidth(), y1 + DataPlayer.getScaledTapBoxWidth());
	}
	
	@Override
	public void dispose() {
		super.dispose();
		mUnheldTexture.dispose();
		mHeldTexture.dispose();
	}
}
