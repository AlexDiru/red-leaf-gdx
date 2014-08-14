package com.alexdiru.redleaf;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/** Represents a song the player can play As such it stores all the notes which
 * will fall down the screen (and provide methods to update and render these
 * notes)
 * 
 * @author Alex */
public class DataSong implements Disposable {

	/** The maximum number of notes that can be rendered */
	private static final int MAX_NOTES_ON_SCREEN = 20;

	/** Represents the levels of difficulty of the song */
	public enum DataSongDifficulty {
		EASY, MEDIUM, HARD
	}

	/** Speed at which notes fall */
	public static float mSongSpeedSlow;
	public static float mSongSpeedRegular;
	public static float mSongSpeedFast;
	
	private static int mCurrentSpeed = 1;

	/** Artist of the song */
	public String mArtistName = "LOST";

	/** Album the song belongs to */
	public String mAlbumName = "DISCOVERY";

	/** Name of the song being played */
	public String mSongName = "";

	/** Current difficulty of the song */
	public DataSongDifficulty mDifficulty;

	/** Points to the file the music for the song is stored in */
	public String mMusicFile;

	/** Stores the notes of the songs, must be kept sorted according to the start
	 * time */
	public ArrayList<DataNote> mNotes;


	/** Manages the playing track for the song */
	public MusicManager mMusicManager;

	/** Manages the gameplay for the user */
	private DataPlayer mTapAreas;

	/** Index of the next note to check to be rendered */
	private int mNoteIndex = 0;

	// Current notes being rendered
	public ArrayList<DataNote> mRenderNotes = new ArrayList<DataNote>(MAX_NOTES_ON_SCREEN);
	
	/** Stores the animations which appear when notes are tapped */
	public ArrayList<NoteTappedAnimation> mNoteAnimations = new ArrayList<NoteTappedAnimation>(MAX_NOTES_ON_SCREEN);
	
	private ArrayList<TimeActivatedEvent> mEvents = new ArrayList<TimeActivatedEvent>();
	
	public Sprite[] mRenderSprites = new Sprite[MAX_NOTES_ON_SCREEN];

	/** The notes which are held by the player at each position */
	private DataNote[] mHeldNotes = new DataNote[4];

	public DataSong() {
		Arrays.fill(mHeldNotes, null);
		Arrays.fill(mRenderSprites, new Sprite());
		
		for (int i = 0; i < MAX_NOTES_ON_SCREEN; i++)
			mNoteAnimations.add(new NoteTappedAnimation());
	}

	public DataSong(String filePath, int difficulty) {
		this();

		// Parse the file
		parse(filePath, difficulty);

	}
	
	@Override
	public void dispose() {
	}
	
	public void spawnAnimation(int position) {
		NoteTappedAnimation animation = getInactiveAnimation();
		if (animation == null)
			return;
		animation.spawn(position);
	}
	
	private NoteTappedAnimation getInactiveAnimation() {
		for (int i = 0; i < mNoteAnimations.size(); i++)
			if (!mNoteAnimations.get(i).isActive())
				return mNoteAnimations.get(i);
		
		return null;
	}

	/** Parse a data file to extract the song data of the specified difficulty
	 * 
	 * @param filePath The file path of the *.rl file
	 * @param currentDifficulty The difficulty level to extract the notes of */
	private void parse(String filePath, int currentDifficulty) {
		// Adjust speed according to difficulty
		switch (currentDifficulty) {
		default:
		case 0:
			mSongSpeedSlow = 0.5f;
			mSongSpeedRegular = 0.65f;
			mSongSpeedFast = 0.72f;
			break;
		case 1:
			mSongSpeedSlow = 0.6f;
			mSongSpeedRegular = 0.75f;
			mSongSpeedFast = 0.9f;
			break;
		case 2:
			mSongSpeedSlow = 0.7f;
			mSongSpeedRegular = 0.85f;
			mSongSpeedFast = 1f;
			break; 
		}
		
		// Read all the lines from the file
		ArrayList<String> lines = UtilsFileIO.readAllLines(filePath);
		boolean parseNotes = false;

		mNotes = new ArrayList<DataNote>();
		mDifficulty = DataSongDifficulty.values()[currentDifficulty];

		for (String line : lines) {
			try {
				String[] fields = UtilsArray.trimAll(line.split("\\ "));

				if (fields[0].equals("artist"))
					mArtistName = UtilsArray.concatArray(fields, " ", 1);
				else if (fields[0].equals("album"))
					mAlbumName = UtilsArray.concatArray(fields, " ", 1);
				else if (fields[0].equals("song"))
					mSongName = UtilsArray.concatArray(fields, " ", 1);
				else if (fields[0].equals("mp3"))
					mMusicFile = UtilsArray.concatArray(fields, " ", 1);
				else if (fields[0].equals("begindifficulty") && fields[1].equals(String.valueOf(currentDifficulty)))
					parseNotes = true;
				else if (parseNotes && fields[0].equals("note"))
					mNotes.add(new DataNote(fields));
				else if (parseNotes && fields[0].equals("event"))
					if (fields[1].equals("speedchange"))
						mEvents.add(new SpeedChangeEvent(fields));
				else if (fields[0].equals("enddifficulty"))
					parseNotes = false;

			} catch (Exception ex) {
			}
		}
	}

	/** Determines which notes are to be rendered
	 * 
	 * @param currentTime The current time of the song being played
	 * @param renderDistance How many milliseconds to look forward in the song
	 *            to select notes */
	public void updateNotes(int currentTime, int renderDistance, long elapsedTime) {

		// Account for song speed (choose minimum speed)
		renderDistance /= mSongSpeedSlow;

		// Check the render list to remove any tapped or off screen notes
		// Note: must use iterator to delete otherwise MASSIVE speed drop (very
		// noticable jitter)
		DataNote current = null;
		for (int i = 0; i < mRenderNotes.size(); i++) {

			current = mRenderNotes.get(i);

			// (Applies to tap notes) If the note has been fallen off the screen
			// remove it from the render list
			boolean tapFallen = current.getStartTime() < currentTime - mTapAreas.getTapWindow();

			// (Applies to hold notes) If the note hasn't been tapped and is
			// below the tapbox
			boolean heldInitialFallen = (current.getStartTime() < currentTime - mTapAreas.getTapWindow()) && current.isHoldNote() && !current.hasBeenTapped();

			// (Applies to hold notes) If the note has been held and the line
			// has fallen off screen
			boolean heldFallen = current.hasBeenTapped() && !current.isHeld() && current.isHoldNote();

			// If a regular note has been tapped or fallen off the screen
			if ((current.hasBeenTapped() && current.isTapNote()) || (tapFallen && current.isTapNote()) || heldInitialFallen || heldFallen) {

				// Get score from hold note
				if (current.isHoldNote() && current.hasBeenTapped()) {
					int smallerTime = Math.min(currentTime, current.getEndTime());
					mTapAreas.increaseScore(smallerTime - current.getStartTime());
				}

				if ((tapFallen && !current.hasBeenTapped()) || heldInitialFallen)
					mTapAreas.miss();

				mRenderNotes.remove(i);
				i--;

				continue;
			}
			// Else no chance of any other notes being tapped
			else if (current.getStartTime() > currentTime + mTapAreas.getTapWindow()) {
				break;
			}
		}

		// Too many notes on screen, no need to add more
		if (mRenderNotes.size() >= MAX_NOTES_ON_SCREEN)
			return;

		// First index will be the index of the note we haven't yet rendered
		// This will be the note which is due to next fall onto the screen
		int startIndex = mNoteIndex;

		// Limit the amount of notes which can be displayed on screen
		// In the case of a stress test being done with ~29k notes
		// StartIndex -> mNotes.size() will be a lot of notes
		// Therefore only lookahead a certain amount
		int endIndex = startIndex + MAX_NOTES_ON_SCREEN;
		if (endIndex > mNotes.size())
			endIndex = mNotes.size();

		DataNote note = null;
		for (int i = startIndex; i < endIndex; i++) {
			note = mNotes.get(i);
			startIndex++;
			if (note.getStartTime() - currentTime < renderDistance) {
	 			mRenderNotes.add(note);
	 			note.setSpeed();
				mNoteIndex = startIndex;
			} else
				break;

			if (mRenderNotes.size() >= MAX_NOTES_ON_SCREEN)
				break;
		}
		
		for (int i = 0; i < mRenderNotes.size(); i++)
			mRenderNotes.get(i).update(mTapAreas, currentTime, elapsedTime);
		
		//Update animations
		for (int i = 0; i < mNoteAnimations.size(); i++)
			if (mNoteAnimations.get(i).isActive())
				mNoteAnimations.get(i).update(elapsedTime);
		
		//Update events
		for (int i  = 0; i < mEvents.size(); i++)
			mEvents.get(i).update(currentTime);
	}

	public void render(SpriteBatch spriteBatch) {
		for (int i = mRenderNotes.size() - 1; i >= 0; i--)
			mRenderNotes.get(i).render(spriteBatch);
		
		for (int i = 0; i < mNoteAnimations.size(); i++)
			if (mNoteAnimations.get(i).isActive())
				mNoteAnimations.get(i).render(spriteBatch, mTapAreas);

		for (int i  = 0; i < mEvents.size(); i++)
			mEvents.get(i).render(spriteBatch);
	}

	/** Given a position, this will return the next note which will be tappable
	 * in that position
	 * 
	 * @param position The tapbox position
	 * @return The note, or null if no notes found */
	private DataNote getNextTappableNoteInPosition(int position, int currentTime, int tapWindow) {
		DataNote note = null;
		for (int i = 0; i < mRenderNotes.size(); i++) {
			note = mRenderNotes.get(i);
			if (!note.hasBeenTapped())
				if (note.getPosition() == position)
					return note;
			
			if (note.getStartTime() > currentTime + tapWindow)
				return null;
		}
		return null;
	}

	/** Called when the player makes a tap on a tapbox, handles all the tapping
	 * of the notes
	 * 
	 * @param position The position of the tapbox which the player tapped
	 * @param currentTime The current time of the song */
	public void tap(int position, int currentTime, int tapWindow) {
		
		DataNote note = getNextTappableNoteInPosition(position,currentTime,tapWindow);

		if (note == null) {
			mTapAreas.mistap();
			return;
		}
			
		if (note.isHit(currentTime, mTapAreas.getTapWindow())) {

			note.setTapped(true);

			if (note.isHoldNote()) {
				note.setHeld(true);
				mHeldNotes[position] = note;
			}

			mTapAreas.successfulTap(note);
		} else
			mTapAreas.mistap();
	}

	/** Called when the player lifts a finger up, makes sure any held notes are
	 * unheld */
	public void unhold(int position) {
		if (mHeldNotes[position] != null) {
			mHeldNotes[position].setHeld(false);
			mHeldNotes[position] = null;
		}
	}

	/** Forces every tapbox to be unheld */
	public void unholdAll() {
		for (int i = 0; i < mHeldNotes.length; i++)
			unhold(i);
	}

	/** Sets the player of the song */
	public void setPlayer(DataPlayer player) {
		mTapAreas = player;
	}

	public static boolean setCurrentSpeed(int speed) {
		boolean speedIncreased = speed > mCurrentSpeed;
		mCurrentSpeed = speed;
		return speedIncreased;
	}
	
	public static float getCurrentSpeed() {
		switch (mCurrentSpeed) {
		case 0:
			return mSongSpeedSlow;
		default:
		case 1:
			return mSongSpeedRegular;
		case 2:
			return mSongSpeedFast;
		}
	}
}
