package com.alexdiru.redleaf.screens;

import java.util.concurrent.Callable;

import com.alexdiru.redleaf.ColourSchemeAssets;
import com.alexdiru.redleaf.DataSong;
import com.alexdiru.redleaf.RedLeafGame;
import com.alexdiru.redleaf.UtilsDraw;
import com.alexdiru.redleaf.UtilsScreenSize;
import com.alexdiru.redleaf.screens.Button.ButtonType;
import com.badlogic.gdx.Gdx;

public class ScoreScreen extends MenuScreen {

	private Button mSongTitle;
	private Button mSongDifficulty;
	private Button mGrade;
	private Button mReturnToMainMenu;
	private int mScore;
	private int mHit;
	private int mMissed;
	
	public ScoreScreen(final RedLeafGame game, int score, String grade, String songName) {
		super(game, false);
		mScore = score;
		
		songName = songName.toLowerCase();

		//Render song title
		if (songName.equals("perfection"))
			mSongTitle = new Button(ButtonType.PERFECTION, null, false);
		else if (songName.equals("world is mad shepherd and we are sheep"))
			mSongTitle = new Button(ButtonType.WORLDISMAD, null, false);
		
		//Render difficulty title
		if (game.getEngine().getSong().mDifficulty == DataSong.DataSongDifficulty.EASY)
			mSongDifficulty = new Button(ButtonType.EASY, null, false);
		else if (game.getEngine().getSong().mDifficulty == DataSong.DataSongDifficulty.MEDIUM)
			mSongDifficulty = new Button(ButtonType.MEDIUM, null, false);
		else
			mSongDifficulty = new Button(ButtonType.HARD, null, false);
		
		//Grade
		if (grade.equals("S"))
			mGrade = new Button(ButtonType.GRADES, null, false);
		else if (grade.equals("AA"))
			mGrade = new Button(ButtonType.GRADEAA, null, false);
		else if (grade.equals("A"))
			mGrade = new Button(ButtonType.GRADEA, null, false);
		else if (grade.equals("B"))
			mGrade = new Button(ButtonType.GRADEB, null, false);
		else if (grade.equals("C"))
			mGrade = new Button(ButtonType.GRADEC, null, false);
		else if (grade.equals("D"))
			mGrade = new Button(ButtonType.GRADED, null, false);
		else if (grade.equals("E"))
			mGrade = new Button(ButtonType.GRADEE, null, false);

		mHit = game.getEngine().getPlayer().getTappedCount();
		mMissed = game.getEngine().getPlayer().getMissedCount();
		
		//Return to main menu
		mReturnToMainMenu = new Button(ButtonType.BACK, new Callable<Void>() {
			public Void call() {
				game.setScreen(new MainMenuScreen(game));
				return null;
			}
		});
		
		mButtons.add(mReturnToMainMenu);
	}
	

	public void render(float delta) {
		mBatch.begin();
		super.render(delta);
		int y = Gdx.graphics.getHeight() - mSongTitle.getHeight();
		mSongTitle.render(mBatch, y);
		y = y - mSongDifficulty.getHeight();
		mSongDifficulty.render(mBatch,y);
		y -=  mGrade.getHeight();
		mGrade.render(mBatch, y);
		y -= UtilsScreenSize.scaleY(30);
		UtilsDraw.drawCentredFontOnlyHorizontal(mBatch, getFont(), "Score: " + mScore, UtilsScreenSize.getScreenWidth()/2,  UtilsScreenSize.getScreenHeight() - y);
		y -= UtilsScreenSize.scaleY(70);
		UtilsDraw.drawCentredFontOnlyHorizontal(mBatch, getFont(), "Hit: " + mHit,UtilsScreenSize.getScreenWidth()/2,  UtilsScreenSize.getScreenHeight() - y);
		y -= UtilsScreenSize.scaleY(70);
		UtilsDraw.drawCentredFontOnlyHorizontal(mBatch, getFont(), "Missed: " + mMissed,UtilsScreenSize.getScreenWidth()/2,  UtilsScreenSize.getScreenHeight() - y);
		mReturnToMainMenu.render(mBatch, 0, 0);
		mBatch.end();
	}
}