package com.alexdiru.redleaf;

public abstract class UtilsGradeCalculator {
	
	/** Calculates a grade based on how well the player played a song
	 * 
	 * @param song The song that has been played
	 * @param misses The number of notes the player has missed
	 * @param mistaps The number of mistaps the player has made
	 * @return The grade */
	public static String calculateGrade(DataSong song, DataPlayer player) {

		int total = player.getMissedCount() + player.getMistaps();
		float notes = (float) song.mNotes.size();

		if (total == 0)
			return "S";
		else if (total < 0.01 * notes)
			return "AA";
		else if (total < 0.03 * notes)
			return "A";
		else if (total < 0.06 * notes)
			return "B";
		else if (total < 0.10 * notes)
			return "C";
		else if (total < 0.16 * notes)
			return "D";
		return "E";
	}
}
