package com.alexdiru.redleaf;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class UserInterface implements Disposable {
	
	private static float BACKGROUND_HEIGHT_PERCENTAGE = 0.11f;
	
	public UserInterface() {
	}
	
	public void render(SpriteBatch spriteBatch, DataPlayer player, GameState gameState) {
		
		if (gameState.get() == GameState.STATE_GAME || gameState.get() == GameState.STATE_COUNTDOWN) {
			// Score
			StringBuilder sb = UtilsString.getStringBuilder();
			sb.append(Utils.getStrings().getGameScorePrefix());
			UtilsString.appendInteger(player.getScore());
			UtilsDraw.drawCentredFontOnlyHorizontal(spriteBatch, ColourSchemeAssets.getUserInterfaceFont(), sb, UtilsScreenSize.getScreenWidth() >> 1, 0);
			
			// Combo
			sb = UtilsString.getStringBuilder();
			sb.append(Utils.getStrings().getGameComboPrefix());
			UtilsString.appendInteger(player.getStreak());
			UtilsDraw.drawFont(spriteBatch, ColourSchemeAssets.getUserInterfaceFont(), sb, 0, UtilsScreenSize.scaleY(65));
			
			// Multiplier
			int multiplier = player.isStarPowerActive() ? 16 : player.getMultiplier();
			sb = UtilsString.getStringBuilder();
			UtilsString.appendInteger(multiplier);
			sb.append(Utils.getStrings().getGameMultiplierSuffix());
			UtilsDraw.drawRightAlignedFont(spriteBatch, ColourSchemeAssets.getUserInterfaceFont(), sb, UtilsScreenSize.getScreenWidth(), UtilsScreenSize.scaleY(65));
		} else if (gameState.get() == GameState.STATE_PAUSED) {
			StringBuilder sb = UtilsString.getStringBuilder();
			sb.append(Utils.getStrings().getGamePaused());
			UtilsDraw.drawCentredFontOnlyHorizontal(spriteBatch, ColourSchemeAssets.getUserInterfaceFont(), sb, UtilsScreenSize.getScreenWidth() >> 1, 0);
		}
	}

	@Override
	public void dispose() {
	}

	public void renderBackground(ShapeRenderer shapeRenderer) {
		Color color = new Color();
		color.set(0.15f,0.15f,0.15f,0.68f);
		UtilsDraw.filledRect(shapeRenderer, color, 0, 0, UtilsScreenSize.getScreenWidth(), (int)(UtilsScreenSize.getScreenHeight()*BACKGROUND_HEIGHT_PERCENTAGE));
	}
}
