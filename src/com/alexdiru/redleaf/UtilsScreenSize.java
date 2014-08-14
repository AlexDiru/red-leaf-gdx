package com.alexdiru.redleaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class UtilsScreenSize {

	private static int mScreenHeight;
	private static int mScreenWidth;
	private static float mScaleX;
	private static float mScaleY;

	@SuppressWarnings("deprecation")
	public static void initialise() {

		mScreenHeight = Gdx.graphics.getHeight();
		mScreenWidth = Gdx.graphics.getWidth();

		mScaleX = (float) mScreenWidth / 720f;
		mScaleY = (float) mScreenHeight / 1280f;
	}

	public static int getScreenHeight() {
		return mScreenHeight;
	}

	public static int getScreenWidth() {
		return mScreenWidth;
	}

	public static float getScaleX() {
		return mScaleX;
	}

	public static float getScaleY() {
		return mScaleY;
	}

	public static int scaleX(int px) {
		return (int) (px * mScaleX);
	}

	public static int scaleY(int px) {
		return (int) (px * mScaleY);
	}

	public static int scaleX(float px) {
		return (int) (px * mScaleX);
	}

	public static int scaleY(float py) {
		return (int) (py * mScaleY);
	}

	public static int scaleFontSize(int sz) {
		return (int) (sz * mScaleY);
	}

	public static int getHeightInRatio(int newWidth, int originalWidth, int originalHeight) {
		return (int) ((float) newWidth * (originalHeight / (float) originalWidth));
	}

	public static int getWidthInRatio(int newHeight, int originalWidth, int originalHeight) {
		return (int) ((float) newHeight * (originalWidth / (float) originalHeight));
	}

	public static Sprite loadBitmapInRatioFromWidth(String assetFile, int newWidth, int originalWidth, int originalHeight) {

		Sprite s = new Sprite(new Texture(Gdx.files.internal(assetFile)));
		int newHeight = getHeightInRatio(newWidth, originalWidth, originalHeight);
		s.setSize(newWidth, newHeight);
		return s;
	}

	public static Sprite loadBitmapInRatioFromHeight(String assetFile, int newHeight, int originalWidth, int originalHeight) {

		Sprite s = new Sprite(new Texture(Gdx.files.internal(assetFile)));
		int newWidth = getWidthInRatio(newHeight, originalWidth, originalHeight);
		s.setSize(newWidth, newHeight);
		return s;
	}
	
	
	
}