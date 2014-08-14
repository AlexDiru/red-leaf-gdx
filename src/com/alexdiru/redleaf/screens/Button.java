package com.alexdiru.redleaf.screens;

import java.util.concurrent.Callable;

import com.alexdiru.redleaf.UtilsScreenSize;
import com.alexdiru.redleaf.UtilsSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {

	protected Texture mUp;
	protected Texture mDown;
	protected Callable<Void> mEvent;
	private boolean mClickable;
	private ButtonType mButtonType;
	
	protected int mWidth, mHeight;
	protected boolean mHeld = false;
	protected int mX = -1;
	protected int mY = -1;
	
	public enum ButtonType {
		PLAY,
		CREDITS,
		OPTIONS,
		EXIT,
		PERFECTION,
		WORLDISMAD,
		EASY,
		MEDIUM,
		HARD, TRACKSELECT,
		BACK, GRADES, GRADEAA, GRADEA, GRADEB,  GRADEC,GRADED, GRADEE, CHECKBOX, APPLY,
		LANGUAGE
	}
	

	public Button(ButtonType buttonType, Callable<Void> event) {
		this(buttonType, event, true);
	}

	
	public Button(ButtonType buttonType, Callable<Void> event, boolean clickable) {
		mClickable = clickable;
		mEvent = event;
		mButtonType = buttonType;
		
		loadTexture(buttonType);
	}
	
	public void reloadTexture() {
		loadTexture(mButtonType);
	}
	
	private void loadTexture(ButtonType buttonType) {
		if (UtilsSettings.isEnglish()) {
			switch (buttonType) {
			case PLAY:
				mUp = new Texture(Gdx.files.internal("menu/play.png"));
				mDown = new Texture(Gdx.files.internal("menu/playdown.png"));
				break;
			case CREDITS:
				mUp = new Texture(Gdx.files.internal("menu/credits.png"));
				mDown = new Texture(Gdx.files.internal("menu/creditsdown.png"));
				break;
			case OPTIONS:
				mUp = new Texture(Gdx.files.internal("menu/options.png"));
				mDown = new Texture(Gdx.files.internal("menu/optionsdown.png"));
				break;
			case EXIT:
				mUp = new Texture(Gdx.files.internal("menu/exit.png"));
				mDown = new Texture(Gdx.files.internal("menu/exitdown.png"));
				break;
			case PERFECTION:
				mUp = new Texture(Gdx.files.internal("menu/perfection.png"));
				mDown = new Texture(Gdx.files.internal("menu/perfectiondown.png"));
				break;
			case WORLDISMAD:
				mUp = new Texture(Gdx.files.internal("menu/worldismad.png"));
				mDown = new Texture(Gdx.files.internal("menu/worldismaddown.png"));
				break;
			case EASY:
				mUp = new Texture(Gdx.files.internal("menu/easy.png"));
				mDown = new Texture(Gdx.files.internal("menu/easydown.png"));
				break;
			case MEDIUM:
				mUp = new Texture(Gdx.files.internal("menu/medium.png"));
				mDown = new Texture(Gdx.files.internal("menu/mediumdown.png"));
				break;
			case HARD:
				mUp = new Texture(Gdx.files.internal("menu/hard.png"));
				mDown = new Texture(Gdx.files.internal("menu/harddown.png"));
				break;
			case TRACKSELECT:
				mUp = new Texture(Gdx.files.internal("menu/title.png"));
				mDown = new Texture(Gdx.files.internal("menu/title.png"));
				break;
			case BACK:
				mUp = new Texture(Gdx.files.internal("menu/back.png"));
				mDown = new Texture(Gdx.files.internal("menu/backdown.png"));
				break;
			case GRADES:
				mUp = new Texture(Gdx.files.internal("menu/gradeS.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeS.png"));
				break;
			case GRADEAA:
				mUp = new Texture(Gdx.files.internal("menu/gradeAA.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeAA.png"));
				break;
			case GRADEA:
				mUp = new Texture(Gdx.files.internal("menu/gradeA.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeA.png"));
				break;
			case GRADEB:
				mUp = new Texture(Gdx.files.internal("menu/gradeB.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeB.png"));
				break;
			case GRADEC:
				mUp = new Texture(Gdx.files.internal("menu/gradeC.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeC.png"));
				break;
			case GRADED:
				mUp = new Texture(Gdx.files.internal("menu/gradeD.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeD.png"));
				break;
			case GRADEE:
				mUp = new Texture(Gdx.files.internal("menu/gradeE.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeE.png"));
				break;
			case CHECKBOX:
				mUp = new Texture(Gdx.files.internal("menu/checkbox.png"));
				mDown = new Texture(Gdx.files.internal("menu/checkboxdown.png"));
				break;
			case APPLY:
				mUp = new Texture(Gdx.files.internal("menu/apply.png"));
				mDown = new Texture(Gdx.files.internal("menu/applydown.png"));
				break;
			case LANGUAGE:
				mUp = new Texture(Gdx.files.internal("menu/nihongo.png"));
				mDown = new Texture(Gdx.files.internal("menu/nihongodown.png"));
				break;
			}
		} else {
			//****************
			//JAPANESE BUTTONS
			//****************
			switch (buttonType) {
			case PLAY:
				mUp = new Texture(Gdx.files.internal("menu/playjp.png"));
				mDown = new Texture(Gdx.files.internal("menu/playdownjp.png"));
				break;
			case CREDITS:
				mUp = new Texture(Gdx.files.internal("menu/credits.png"));
				mDown = new Texture(Gdx.files.internal("menu/creditsdown.png"));
				break;
			case OPTIONS:
				mUp = new Texture(Gdx.files.internal("menu/optionsjp.png"));
				mDown = new Texture(Gdx.files.internal("menu/optionsdownjp.png"));
				break;
			case EXIT:
				mUp = new Texture(Gdx.files.internal("menu/exitjp.png"));
				mDown = new Texture(Gdx.files.internal("menu/exitdownjp.png"));
				break;
			case PERFECTION:
				mUp = new Texture(Gdx.files.internal("menu/perfection.png"));
				mDown = new Texture(Gdx.files.internal("menu/perfectiondown.png"));
				break;
			case WORLDISMAD:
				mUp = new Texture(Gdx.files.internal("menu/worldismad.png"));
				mDown = new Texture(Gdx.files.internal("menu/worldismaddown.png"));
				break;
			case EASY:
				mUp = new Texture(Gdx.files.internal("menu/easy.png"));
				mDown = new Texture(Gdx.files.internal("menu/easydown.png"));
				break;
			case MEDIUM:
				mUp = new Texture(Gdx.files.internal("menu/medium.png"));
				mDown = new Texture(Gdx.files.internal("menu/mediumdown.png"));
				break;
			case HARD:
				mUp = new Texture(Gdx.files.internal("menu/hard.png"));
				mDown = new Texture(Gdx.files.internal("menu/harddown.png"));
				break;
			case TRACKSELECT:
				mUp = new Texture(Gdx.files.internal("menu/titlejp.png"));
				mDown = new Texture(Gdx.files.internal("menu/titlejp.png"));
				break;
			case BACK:
				mUp = new Texture(Gdx.files.internal("menu/backjp.png"));
				mDown = new Texture(Gdx.files.internal("menu/backdownjp.png"));
				break;
			case GRADES:
				mUp = new Texture(Gdx.files.internal("menu/gradeS.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeS.png"));
				break;
			case GRADEAA:
				mUp = new Texture(Gdx.files.internal("menu/gradeAA.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeAA.png"));
				break;
			case GRADEA:
				mUp = new Texture(Gdx.files.internal("menu/gradeA.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeA.png"));
				break;
			case GRADEB:
				mUp = new Texture(Gdx.files.internal("menu/gradeB.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeB.png"));
				break;
			case GRADEC:
				mUp = new Texture(Gdx.files.internal("menu/gradeC.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeC.png"));
				break;
			case GRADED:
				mUp = new Texture(Gdx.files.internal("menu/gradeD.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeD.png"));
				break;
			case GRADEE:
				mUp = new Texture(Gdx.files.internal("menu/gradeE.png"));
				mDown = new Texture(Gdx.files.internal("menu/gradeE.png"));
				break;
			case CHECKBOX:
				mUp = new Texture(Gdx.files.internal("menu/checkbox.png"));
				mDown = new Texture(Gdx.files.internal("menu/checkboxdown.png"));
				break;
			case APPLY:
				mUp = new Texture(Gdx.files.internal("menu/apply.png"));
				mDown = new Texture(Gdx.files.internal("menu/applydown.png"));
				break;
			case LANGUAGE:
				mUp = new Texture(Gdx.files.internal("menu/english.png"));
				mDown = new Texture(Gdx.files.internal("menu/englishdown.png"));
				break;
			}
		}
		mWidth = (int)(mUp.getWidth() * UtilsScreenSize.getScaleX()/1.7f );
		mHeight = (int)(mUp.getHeight() * UtilsScreenSize.getScaleY()/1.7f);
	}
	
	public void render(SpriteBatch batch, int y) {
		mX = (Gdx.graphics.getWidth() - mWidth) / 2;
		mY = y;
		render(batch);
	}

	public void render(SpriteBatch batch, int x, int y) {
		mX = x;
		mY = y;
		render(batch);
	}
	
	private void render(SpriteBatch batch) {
		if (mHeld)
			batch.draw(mDown, mX,mY, mWidth, mHeight);
		else
			batch.draw(mUp, mX, mY, mWidth, mHeight);
	}

	public boolean contains(int x, int y) {
		y = Gdx.graphics.getHeight() - y;
		return x >= mX && y >= mY && x <= mX + mWidth && y <= mY + mHeight;
	}

	public void activate() {
		try {
			mEvent.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void toggleHoldOn() {
		if (!mClickable)
			return;
		mHeld = true;
	}

	public void toggleHoldOff() {
		mHeld = false;
	}

	public boolean isHeld() {
		return mHeld;
	}

	public int getHeight() {
		return mHeight;
	}
	
	public int getWidth() {
		return mWidth;
	}
}