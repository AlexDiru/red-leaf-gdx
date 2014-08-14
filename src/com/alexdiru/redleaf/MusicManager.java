package com.alexdiru.redleaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

public class MusicManager implements Disposable {

	public Music mMediaPlayer;
	public long mPauseTime = 0;
	public boolean mStarted;
	private long mSongLength;
	private boolean mPaused = false;

	// Hack a timer because libGDX music timer is awful
	private long mStartOfSong;

	public MusicManager() {

		// TODO
		// String encryptedFilePath = Utils.getCurrentSong().mMusicFile;
		// Utils.getCurrentSong().mMusicManager = this;
		// TODO

		byte[] decryptedMusicBytes;
		// Decrypt the music

		mMediaPlayer = Gdx.audio.newMusic(Gdx.files.internal(Utils.getCurrentSong().mMusicFile));
		mStarted = false;
		mPauseTime = 0;
		if (UtilsSettings.mMuteSound)
			mMediaPlayer.setVolume(0);
	}

	@Override
	public void dispose() {
		mMediaPlayer.dispose();
	}

	
	public int getPlayPosition() {
		return (int) ((System.nanoTime() - mStartOfSong) / 1000000);
	}

	public boolean isSongOver() {
		if (!isPlaying() && !isPaused() && mStarted) {
			mMediaPlayer.stop();
			return true;
		}

		return false;
	}

	public boolean isPlaying() {
		return mMediaPlayer.isPlaying();
	}

	public boolean isPaused() {
		return mPaused;
	}

	public void play() {
		if (!mPaused) {
			mMediaPlayer.play();
			mStarted = true;
			mStartOfSong = System.nanoTime();
		} else {
			mMediaPlayer.play();
			// Need to make sure the pause time is accounted so the notes don't
			// 'skip'
			mStartOfSong += (System.nanoTime() - mPauseTime);
			mPaused = false;
		}
	}

	public void pause() {
		mMediaPlayer.pause();
		mPauseTime = System.nanoTime();
		mPaused = true;
	}

}
