package com.alexdiru.redleaf.screens;


import java.util.concurrent.Callable;

import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.UtilsDraw;
import com.alexdiru.redleaf.UtilsScreenSize;
import com.alexdiru.redleaf.UtilsSettings;
import com.alexdiru.redleaf.screens.Button.ButtonType;

public class OptionsScreen extends MenuScreen {

	private Button mOptionsTitle = new Button(ButtonType.OPTIONS, null, false);
	private CheckBox mUseVibrateCheckbox = new CheckBox();
	private CheckBox mMuteCheckbox = new CheckBox();
	private Button mApply;
	
	protected OptionsScreen(RedLeafGame game) {
		super(game, true);
		
		mApply = new Button(ButtonType.APPLY, new Callable<Void>() {
			public Void call() {
				UtilsSettings.mUseVibrate = mUseVibrateCheckbox.isChecked();
				UtilsSettings.mMuteSound = mMuteCheckbox.isChecked();
				UtilsSettings.save();
				return null;
			}
		});

		mUseVibrateCheckbox.setChecked(UtilsSettings.mUseVibrate);
		mMuteCheckbox.setChecked(UtilsSettings.mMuteSound);
		
		mButtons.add(mUseVibrateCheckbox);
		mButtons.add(mMuteCheckbox);
		mButtons.add(mApply);
	}
	
	@Override
	public void render(float delta) {
		mBatch.begin();
		super.render(delta);
		int y = UtilsScreenSize.getScreenHeight() - mOptionsTitle.getHeight();
		mOptionsTitle.render(mBatch, y);
		y -= UtilsScreenSize.scaleY(70);
		UtilsDraw.drawFont(mBatch, getFont(), "Vibrate", UtilsScreenSize.scaleX(10), UtilsScreenSize.getScreenHeight() - y);
		y -= UtilsScreenSize.scaleY(80);
		mUseVibrateCheckbox.render(mBatch, UtilsScreenSize.getScreenWidth() - mUseVibrateCheckbox.getWidth(), y);
		y -= UtilsScreenSize.scaleY(40);
		UtilsDraw.drawFont(mBatch, getFont(), "Mute", UtilsScreenSize.scaleX(10), UtilsScreenSize.getScreenHeight() - y);
		y -= UtilsScreenSize.scaleY(80);
		mMuteCheckbox.render(mBatch, UtilsScreenSize.getScreenWidth() - mMuteCheckbox.getWidth(), y);
		y -= UtilsScreenSize.scaleY(600);
		mApply.render(mBatch, y);
		mBatch.end();
	}

}