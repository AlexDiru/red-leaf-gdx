package com.alexdiru.redleaf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
	
	private static final int MAX_SCORES_KEPT_TRACK_OF = 10;
	private static final String FILE_NAME = "scores/";
	
	/*
	 * Maps song name to a list of the scores and the name that got them
	 * Score Map's RHS must be kept sorted by score
	 */
	private Map<String, ArrayList<Pair<String, Integer>>> mScoreMap;
	
	private String mCurrentSong;
	private int mCurrentDifficulty;
	
	public Scoreboard() {
		mScoreMap = new HashMap<String, ArrayList<Pair<String, Integer>>>();
	}
	
	public void addScore(String songName, String name, int score) {
		ArrayList<Pair<String, Integer>> scoreEntries = mScoreMap.get(songName);
		
		if (scoreEntries == null) {
			scoreEntries = new ArrayList<Pair<String, Integer>>();
			mScoreMap.put(songName, scoreEntries);
		}
		
		int insertIndex = -1;
		
		if (scoreEntries.size() == 0) {
			scoreEntries.add(new Pair<String, Integer>(name, score));
			save(songName);
			return;
		}
			
		for (int i = 0; i < scoreEntries.size(); i++) {
			// We have found our entry
			if (score > scoreEntries.get(i).getRight()) {
				insertIndex = i;
				break;
			}
		}

		if (insertIndex == -1 && scoreEntries.size() >= MAX_SCORES_KEPT_TRACK_OF)
			return;
		else if (insertIndex == -1)
			insertIndex = scoreEntries.size() - 1;
		
		//Now we need to push all the lower scores in the list down the scoreboard
		if (scoreEntries.size() < MAX_SCORES_KEPT_TRACK_OF) 
			scoreEntries.add(null);
		
		for (int i = scoreEntries.size() - 2; i >= insertIndex; i--) {
			scoreEntries.set(i + 1, scoreEntries.get(i));
		
			if (i == insertIndex)
				scoreEntries.set(i, new Pair<String, Integer>(name, score));
		}
		
		save(songName);
	}
	
	public ArrayList<Pair<String, Integer>> getScores(String songName) {
		return mScoreMap.get(songName);
	}
	
	public void load(String songName, int currentDifficulty) {
		mCurrentSong = songName;
		mCurrentDifficulty = currentDifficulty;
		
		ArrayList<Pair<String, Integer>> scoreEntries = new ArrayList<Pair<String, Integer>>();
		ArrayList<String> lines = UtilsFileIO.readAllLines(FILE_NAME + songName + mCurrentDifficulty + ".txt", false);
		
		if (lines == null)
			return;
		
		for (int i = 0; i < lines.size(); i++){
			String[] fields = lines.get(i).split("\\,");
			scoreEntries.add(new Pair<String, Integer>(fields[0], Integer.parseInt(fields[1])));
		}
		
		mScoreMap.put(songName, scoreEntries);
	}
	
	public void save(String songName) {
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<Pair<String, Integer>> scoreEntries = mScoreMap.get(songName);
		
		for (int i = 0; i < scoreEntries.size(); i++)
			lines.add(scoreEntries.get(i).getLeft() + "," + scoreEntries.get(i).getRight());
		
		UtilsFileIO.writeAllLines(FILE_NAME + songName + mCurrentDifficulty + ".txt", lines);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Pair<String, Integer>> scoreEntries = mScoreMap.get(mCurrentSong);
		
		for (int i = 0; i < scoreEntries.size(); i++)
			sb.append((i+1) + ". " + scoreEntries.get(i).getRight() + " " + scoreEntries.get(i).getLeft());
		
		return sb.toString();
	}
}
