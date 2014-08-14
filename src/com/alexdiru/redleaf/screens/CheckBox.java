package com.alexdiru.redleaf.screens;

import java.util.concurrent.Callable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CheckBox extends Button {

	private boolean mChecked = false;
	private Texture mCrossedUp;
	private Texture mCrossedDown;
	
	public CheckBox() {
		super(ButtonType.CHECKBOX, null, true);
		
		//Load textures
		mCrossedUp = new Texture(Gdx.files.internal("menu/checkboxcrossed.png"));
		mCrossedDown = new Texture(Gdx.files.internal("menu/checkboxcrosseddown.png"));
		
		mEvent = new Callable<Void>() {
			public Void call() {
				mChecked = !mChecked;
				return null;
			}
		};
	}

	@Override
	public void render(SpriteBatch batch, int x, int y) {
		mX = x;
		mY = y;
		if (!mChecked){
			if (mHeld)
				batch.draw(mDown, mX,mY, mWidth, mHeight);
			else
				batch.draw(mUp, mX, mY, mWidth, mHeight);
		} else {
			if (mHeld)
				batch.draw(mCrossedDown, mX,mY, mWidth, mHeight);
			else
				batch.draw(mCrossedUp, mX, mY, mWidth, mHeight);
		}
	}

	public boolean isChecked() {
		return mChecked;
	}

	public void setChecked(boolean checked) {
		mChecked = checked;
	}
}
