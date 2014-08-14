package com.alexdiru.redleaf;

import java.awt.Paint;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Disposable;

/** Represents a bounding box which can be touched They can also be drawn to the screen
 * @author Alex */
public class DataBoundingBox implements Disposable {

	/** Used for the coordinates of the bounding box */
	private Rect mRect = new Rect();
	
	protected Texture mRenderTexture;
	private int mRectangleWidth = 0;
	
	public DataBoundingBox() {
	}
	
	public DataBoundingBox(Texture Texture) {
		mRenderTexture = Texture;
	}
	
	public DataBoundingBox(Texture Texture, Paint paint) {
		this(Texture);
	}

	/** Updates the position of the bounding box */
	public void update(int x1, int y1, int x2, int y2) {
		mRect.left = x1;
		mRect.right = x2;
		mRect.top = y1;
		mRect.bottom = y2;
	}
	
	public boolean isTouched(int x, int y) {
		return isTouched(x,y,0,0);
	}

	/** Whether the entered coordinates are inside the bounding box
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param extensionX An X extension on the bounding box, applied to both directions
	 * @param extensionY An Y extension on the bounding box, applied to both directions
	 * @return Whether the bounding box was touched */
	public boolean isTouched(int x, int y, int extensionX, int extensionY) {
		return (x >= mRect.left - extensionX && x <= mRect.right + extensionX && y >= mRect.top - extensionY && y <= mRect.bottom + extensionY);
	}
	
	public void setRenderTexture(Texture Texture) {
		mRenderTexture = Texture;
	}
	
	public void setRectangleWidth(int width) {
		//Odd width, make even
		if (width % 2 != 0)
			width++;
		mRectangleWidth = width;
	}


	public void render(SpriteBatch spriteBatch) {
		UtilsDraw.draw(spriteBatch, mRenderTexture, mRect.left, mRect.top,mRect.getWidth(), mRect.getHeight());
	}
	
	public void render(ShapeRenderer shapeRenderer, Color colour) {
		//Left
		UtilsDraw.filledRect(shapeRenderer, colour, mRect.left - mRectangleWidth, mRect.top, mRectangleWidth, mRect.getHeight());
		//Right
		UtilsDraw.filledRect(shapeRenderer, colour, mRect.right, mRect.top, mRectangleWidth, mRect.getHeight());
		//Top
		UtilsDraw.filledRect(shapeRenderer, colour, mRect.left - mRectangleWidth, mRect.top - mRectangleWidth, mRect.getWidth() + mRectangleWidth * 2, mRectangleWidth);
		//Bottom
		UtilsDraw.filledRect(shapeRenderer, colour, mRect.left - mRectangleWidth, mRect.bottom, mRect.getWidth() + mRectangleWidth * 2, mRectangleWidth);
	}

	/** Gets the top of the bounding box
	 * @return The top of the bounding box */
	public int getTop() {
		return mRect.top;
	}

	/** Gets the bottom of the bounding box
	 * @return The bottom of the bounding box */
	public int getBottom() {
		return mRect.bottom;
	}

	/** Gets the left of the bounding box
	 * @return The left of the bounding box */
	public int getLeft() {
		return mRect.left;
	}

	@Override
	public void dispose() {
		if (mRenderTexture != null)
			mRenderTexture.dispose();
	}
}
