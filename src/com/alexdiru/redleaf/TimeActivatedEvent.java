package com.alexdiru.redleaf;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class TimeActivatedEvent {

	protected int mActivationTime;
	protected boolean mActivated;
	
	public TimeActivatedEvent(int activationTimeMS) {
		mActivationTime = activationTimeMS;
		mActivated = false;
	}
	
	public void update(int songTime) {
		if (!mActivated && songTime >= mActivationTime){
			mActivated = true;
			onActivation();
		}
	}
	
	public void render(SpriteBatch spriteBatch) {
		if (mActivated)
			onRender(spriteBatch);
	}
	
	protected abstract void onActivation();
	
	protected abstract void onRender(SpriteBatch spriteBatch);
}
