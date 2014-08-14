package com.alexdiru.redleaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public abstract class BitmapFontLoader {

	//UI font is size 74
	//Menu font is size 55
	public static BitmapFont loadFont(String fontName) {
		BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("fonts/" + fontName + ".fnt"), Gdx.files.internal("fonts/" + fontName + ".png"), false);
		bitmapFont.setScale(UtilsScreenSize.getScaleX());
		return bitmapFont;
	}
	
}
