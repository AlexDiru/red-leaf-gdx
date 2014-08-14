package com.alexdiru.redleaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class UtilsDraw {

	public static void draw(SpriteBatch spriteBatch, Texture texture, int x, int y, int width, int height) {
		spriteBatch.draw(texture, x, UtilsScreenSize.getScreenHeight() - y - height, width, height);
	}
	
	public static void draw(SpriteBatch spriteBatch, NinePatch ninePatch, int x, int y, int width, int height) {
		ninePatch.draw(spriteBatch, x, UtilsScreenSize.getScreenHeight() - y - height, width, height);
	}


	public static void draw(SpriteBatch spriteBatch, TextureRegion texture, int x, int y, int width, int height) {
		spriteBatch.draw(texture, x, UtilsScreenSize.getScreenHeight() - y - height, width, height);
	}

	public static void filledRect(ShapeRenderer shapeRenderer, int x, int y, int width, int height) {
		shapeRenderer.filledRect(x, UtilsScreenSize.getScreenHeight() - y - height, width, height);
	}
	
	public static void filledRect(ShapeRenderer shapeRenderer, Color colour, int x, int y, int width, int height) {
		shapeRenderer.filledRect(x, UtilsScreenSize.getScreenHeight() - y - height, width, height, colour, colour,  colour, colour);
	}
	
	public static void drawFont(SpriteBatch spriteBatch, BitmapFont font, CharSequence text, int x, int y) {
		drawFont(spriteBatch, font, text, x, y, 0);
	}
	
	private static void drawFont(SpriteBatch spriteBatch, BitmapFont font, CharSequence text, int x, int y, int height) {
		font.draw(spriteBatch, text, x, UtilsScreenSize.getScreenHeight() - y - height);
	}
	
	public static void drawRightAlignedFont(SpriteBatch spriteBatch, BitmapFont font, CharSequence text, int x, int y) {
		TextBounds tb = font.getBounds(text);
		drawFont(spriteBatch, font, text, x - (int)tb.width, y, 0);
	}
	
	public static void drawCentredFont(SpriteBatch spriteBatch, BitmapFont font, CharSequence text, int x, int y) {
		TextBounds tb = font.getBounds(text);
		int offsetX = (int)tb.width >> 1;
		int height =  (int)tb.height;
		int offsetY = height >> 1;
		drawFont(spriteBatch, font, text, x - offsetX, y, offsetY-height);
	}

	public static void drawCentredFontOnlyHorizontal(SpriteBatch spriteBatch, BitmapFont font, CharSequence text, int x, int y) {
		TextBounds tb = font.getBounds(text);
		int offsetX = (int)tb.width >> 1;
		int height =  (int)tb.height;
		drawFont(spriteBatch, font, text, x - offsetX, y, 0);	
	}
}
