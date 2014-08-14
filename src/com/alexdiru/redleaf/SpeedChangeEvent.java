package com.alexdiru.redleaf;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpeedChangeEvent extends TimeActivatedEvent {

	private static int FONT_DISPLAY_LENGTH = 2250;
	
	private int mSpeedChange;
	private boolean mSpeedIncreased;
	private boolean mDisplayFont;
	
	public SpeedChangeEvent(int activationTime, int speedChange) {
		super(activationTime);
		mSpeedChange = speedChange;
	}
	
	public SpeedChangeEvent(String[] fields) {
		this(Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
	}

	@Override
	public void update(int songTime) {
		super.update(songTime);
		mDisplayFont = mActivated && songTime - mActivationTime < FONT_DISPLAY_LENGTH;
	}

	@Override
	protected void onActivation() {
		mSpeedIncreased = DataSong.setCurrentSpeed(mSpeedChange);
	}

	@Override
	protected void onRender(SpriteBatch spriteBatch) {
		if (mDisplayFont) 
			UtilsDraw.drawCentredFontOnlyHorizontal(spriteBatch, ColourSchemeAssets.getSpeedChangeFont(), mSpeedIncreased ? Utils.getStrings().getGameSpeedUp() : Utils.getStrings().getGameSlowDown(), UtilsScreenSize.getScreenWidth() >> 1, UtilsScreenSize.getScreenHeight() >> 1);
	}
}
