package com.alexdiru.redleaf;

import com.alexdiru.redleaf.screens.IScreen;
import com.alexdiru.redleaf.strings.EnglishStringSet;
import com.alexdiru.redleaf.strings.IStringSet;

public abstract class Utils {

	private static DataSong mCurrentSong;
	private static int mCurrentDifficulty;
	private static IStringSet mStringSet = new EnglishStringSet();
	private static IScreen mCurrentScreen;
	
	public static void setCurrentSong(DataSong dataSong) {
		mCurrentSong = dataSong;
	}
	
	public static DataSong getCurrentSong() {
		return mCurrentSong;
	}
	
	public static IStringSet getStrings() {
		return mStringSet;
	}

	public static void setDifficulty(int difficulty) {
		mCurrentDifficulty = difficulty;
	}
	
	public static int getDifficulty() {
		return mCurrentDifficulty;
	}

	public static IScreen getCurrentScreen() {
		return mCurrentScreen;
	}
	
	public static void setCurrentScreen(IScreen screen) {
		mCurrentScreen = screen;
	}
	
}
