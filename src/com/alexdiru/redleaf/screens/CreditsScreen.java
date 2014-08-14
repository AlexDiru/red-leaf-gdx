package com.alexdiru.redleaf.screens;

import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.UtilsScreenSize;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class CreditsScreen extends MenuScreen {
	public CreditsScreen(final RedLeafGame game) {
		super(game, true);
		
		/*
		final int buttonX = (UtilsScreenSize.getScreenWidth() - getButtonWidth()) / 2;
		int currentY = UtilsScreenSize.getScreenHeight() - getButtonHeight() - getButtonSpacing() * 3;
		Gdx.app.log(currentY + "","");
		
		stage.addActor(createButton(Utils.getStrings().getCreditsMenuAlex(), buttonX, currentY, getButtonWidth(), getButtonHeight(), new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
			};
		}, MenuAssets.getTextButtonStyle()));
		
		currentY -= (getButtonHeight() + getButtonSpacing());
		
		stage.addActor(createButton("Grant Norris - Art", buttonX, currentY, getButtonWidth(), getButtonHeight(), new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			};
		}, MenuAssets.getTextButtonStyle()));

		currentY -= (getButtonHeight() + getButtonSpacing());
		
		stage.addActor(createButton(Utils.getStrings().getCreditsMenuLOST(), buttonX, currentY, getButtonWidth(), getButtonHeight(), new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			};
		}, MenuAssets.getTextButtonStyle()));
		*/
	}
	
	public void render(float delta) {
		mBatch.begin();
		super.render(delta);
		float xRatio = (float)Gdx.graphics.getWidth() / MenuAssets.getMenuBackground().getWidth();
		float yRatio = (float)Gdx.graphics.getHeight() / MenuAssets.getMenuBackground().getHeight();
		int height = (int)(UtilsScreenSize.scaleY(MenuAssets.getCreditsBackground().getHeight()) * xRatio);
		mBatch.draw(MenuAssets.getCreditsBackground(), 0, Gdx.graphics.getHeight() - height, MenuAssets.getCreditsBackground().getWidth()*xRatio, height);		
		mBatch.end();
	}
}
