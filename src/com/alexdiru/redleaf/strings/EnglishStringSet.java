package com.alexdiru.redleaf.strings;

public class EnglishStringSet implements IStringSet {

	private static final String mMainMenuPlay = "Play";
	private static final String mMainMenuCredits = "Credits";
	
	private static final String mGameScorePrefix = "Score: ";
	private static final String mGameComboPrefix = "Combo: ";
	private static final String mGameMultiplierSuffix = "x";
	private static final String mGameSpeedUp = "Speed Up";
	private static final String mGameSlowDown = "Slow Down";
	
	private static final String mEasyDifficulty = "Easy";
	private static final String mMediumDifficulty = "Medium";
	private static final String mHardDifficulty = "Hard";

	private static final String mScoreMenuReturnToMainMenu = "Return to Main Menu";
	private static final String mScoreMenuReturnToSongChoiceMenu = "Pick Another Song";
	private static final String mScoreMenuGradePrefix = "Grade: ";
	
	private static final String mGamePaused = "Paused";

	private static final String mCreditsMenuAlex = "Alex Spedding - Programmer";
	private static final String mCreditsMenuLOST = "LOST - Music/Graphics";
	
	@Override
	public String getMainMenuPlay() {
		return mMainMenuPlay;
	}
	
	@Override
	public String getGameScorePrefix() {
		return mGameScorePrefix;
	}
	
	@Override
	public String getGameMultiplierSuffix() {
		return mGameMultiplierSuffix;
	}

	@Override
	public String getGameComboPrefix() {
		return mGameComboPrefix;
	}

	@Override
	public String getDifficulty(int difficulty) {
		switch (difficulty) {
			default:
			case 0:
				return mEasyDifficulty;
			case 1:
				return mMediumDifficulty;
			case 2:
				return mHardDifficulty;
		}
	}

	@Override
	public String getEasyDifficulty() {
		return mEasyDifficulty;
	}

	@Override
	public String getMediumDifficulty() {
		return mMediumDifficulty;
	}

	@Override
	public String getHardDifficulty() {
		return mHardDifficulty;
	}

	@Override
	public String getMainMenuCredits() {
		return mMainMenuCredits;
	}

	@Override
	public String getScoreMenuReturnToMainMenu() {
		return mScoreMenuReturnToMainMenu;
	}

	@Override
	public String getScoreMenuReturnToSongChoiceMenu() {
		return mScoreMenuReturnToSongChoiceMenu;
	}

	@Override
	public String getScoreMenuGradePrefix() {
		return mScoreMenuGradePrefix;
	}

	@Override
	public String getGameSpeedUp() {
		return mGameSpeedUp;
	}

	@Override
	public String getGameSlowDown() {
		return mGameSlowDown;
	}

	@Override
	public Object getGamePaused() {
		return mGamePaused;
	}

	@Override
	public String getCreditsMenuAlex() {
		return mCreditsMenuAlex;
	}
	
	@Override
	public String getCreditsMenuLOST() {
		return mCreditsMenuLOST;
	}
}
