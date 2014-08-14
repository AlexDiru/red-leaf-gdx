package com.alexdiru.redleaf;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;

public class DataNoteHoldLine {

	private DataNote mParentNote;
	private int mY1Position;
	private int mY2Position;
	private int mChunks = -1;
	private int mOffset;
	private int mHeight;


	public DataNoteHoldLine(DataNote parent) {
		mParentNote = parent;
	}

	public void update(float songSpeed, int currentTime) {
		//Update the position of the hold line according to the song
		mY1Position = UtilsScreenSize.scaleY(DataPlayer.mUnscaledTapBoxY - (int) ((mParentNote.getEndTime() - currentTime) * songSpeed));
		mY2Position = mParentNote.getScaledYPosition() + DataNote.mNotePixelHeight / 2;
		
		
		//Chunks can only be calculated once, because sometimes, at certain resolutions, the tip of the note trails will flash as there is a different offset (or something...)
		if (mChunks == -1) {
			//Height of hold line
			mHeight = Math.abs(mY1Position - mY2Position);

			//Round off the height of the hold note so it doens't look stretched (HEIGHTpx * n)
			mOffset = mHeight % DataNote.mNotePixelHeight;
			mHeight -= mOffset;
			mChunks = mHeight/DataNote.mNotePixelHeight;
		}
	}
	

	public void render(SpriteBatch spriteBatch) {

		Texture texture = mParentNote.isHeld() ? ColourSchemeAssets.getHoldLineHeldPaint(mParentNote.getPosition()) : ColourSchemeAssets.getHoldLineUnheldPaint(
				mParentNote.getPosition(), mParentNote.isStarNote());
		int x1 =  mParentNote.getScaledXPosition();
		
		//Draw each chunk of the hold line
		for (int chunk = 0; chunk < mChunks; chunk++)
			UtilsDraw.draw(spriteBatch, texture, x1,mY1Position + mOffset + (int)((chunk * DataNote.mNotePixelHeight)), DataNote.mNotePixelHeight, DataNote.mNotePixelHeight);
	}

}
