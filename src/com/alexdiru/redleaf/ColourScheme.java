package com.alexdiru.redleaf;


/** Represents a colour scheme for the song Different assets will be loaded for the graphics
 * depending on the theme chosen
 * @author Alex */
public class ColourScheme  {

	/** The themes the song can have */
	public enum ThemeType {
		DISCOVERY, MONSOON, MAYDIE
	}

	/** The bitmaps for the tapbox */
	public String[] mTap = new String[4];

	/** The bitmaps for star notes */
	public String[] mNoteStar = new String[4];

	/** The bitmaps for when a tapbox is held */
	public String[] mTapHold = new String[4];

	/** Bitmaps used when a note is unheld/tap note */
	public String[] mNote = new String[4];

	/** Bitmaps used when a note is held */
	public String[] mNoteHeld = new String[4];

	/** Bitmaps used for a hold note trail when the note is held down */
	public String[] mNoteStreamHeld = new String[4];

	/** Bitmaps used for a hold note trail when the note is not held down */
	public String[] mNoteStreamUnheld = new String[4];
	
	/** Bitmaps used for a hold note trail when the note is a star note */
	public String[] mNoteStreamUnheldStar = new String[4];

	/** Bitmap used for the background */
	public String mBackground;
	
	/** Bitmap used for the background when star power is active */
	public String mBackgroundStar;

	public String mStarPower;

	public String[] mCountdownTimer = new String[3];
	
	public String mNoteTapped;
	
	public String mUserInterfaceFont;
	public String mSpeedChangeFont;

	/** Initialises all the file paths with the theme given
	 * @param themeType The theme to base the files on */
	public ColourScheme(ThemeType themeType) {

		switch (themeType) {
		case DISCOVERY:
		default:
			mBackground = "ui/new.jpg";
			mBackgroundStar = "ui/new.jpg";
			
			mNoteTapped = "notetapped.png";

			mTap[0] = "L.png";
			mTap[1] = "O.png";
			mTap[2] = "S.png";
			mTap[3] = "T.png";

			mTapHold[0] = "Li.png";
			mTapHold[1] = "Oi.png";
			mTapHold[2] = "Si.png";
			mTapHold[3] = "Ti.png";

			mNote[0] = "reddown.png";
			mNote[1] = "reddown.png";
			mNote[2] = "reddown.png";
			mNote[3] = "reddown.png";

			mNoteStar[0] = "redleft.png";
			mNoteStar[1] = "redleft.png";
			mNoteStar[2] = "redleft.png";
			mNoteStar[3] = "redleft.png";

			mNoteHeld[0] = "bluedown.png";
			mNoteHeld[1] = "bluedown.png";
			mNoteHeld[2] = "bluedown.png";
			mNoteHeld[3] = "bluedown.png";

			mNoteStreamHeld[0] = "bluedowntrail.png";
			mNoteStreamHeld[1] = "bluedowntrail.png";
			mNoteStreamHeld[2] = "bluedowntrail.png";
			mNoteStreamHeld[3] = "bluedowntrail.png";

			mNoteStreamUnheld[0] = "reddowntrail.png";
			mNoteStreamUnheld[1] = "reddowntrail.png";
			mNoteStreamUnheld[2] = "reddowntrail.png";
			mNoteStreamUnheld[3] = "reddowntrail.png";

			mNoteStreamUnheldStar[0] = "yellowdowntrail.png";
			mNoteStreamUnheldStar[1] = "yellowdowntrail.png";
			mNoteStreamUnheldStar[2] = "yellowdowntrail.png";
			mNoteStreamUnheldStar[3] = "yellowdowntrail.png";

			mStarPower = "silverflame.png";

			mCountdownTimer[0] = "1.png";
			mCountdownTimer[1] = "2.png";
			mCountdownTimer[2] = "3.png";
			
			mUserInterfaceFont = "hi";
			mSpeedChangeFont = "speedchange";
			
			break;
		}
	}

	public void dispose() {
		mTap = null;
		mNoteStar = null;
		mTapHold = null;
		mNote = null;
		mNoteHeld = null;
		mNoteStreamHeld = null;
		mNoteStreamUnheld = null;
		mNoteStreamUnheldStar = null;
		mBackground = null;
		mBackgroundStar = null;
		mStarPower = null;	
	}
}
