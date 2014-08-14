package com.alexdiru.redleaf;

import java.util.ArrayList;

public abstract class UtilsSettings {

	private static final String ENGLISH_CODE = "ENG";
	private static final String JAPANESE_CODE = "JP";
	
	public static boolean mUseVibrate = true;
	public static boolean mMuteSound = false;
	
	public static boolean mDebug = false;
	
	public static String mLanguage = ENGLISH_CODE; //or "JP"
	
	public static void save() {
		//Save to binary strings
		String data = "";
		data += boolToString(mUseVibrate);
		data += boolToString(mMuteSound);
		data += "\n";
		data += mLanguage;
		
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(data);
		
		UtilsFileIO.writeAllLines("settings.txt", dataList);
	}

	public static boolean isEnglish() {
		return mLanguage.equals(ENGLISH_CODE);
	}
	
	public static boolean isJapanese() {
		return mLanguage.equals(JAPANESE_CODE);
	}
	
	public static void switchLanguage() {
		if (mLanguage.equals(ENGLISH_CODE))
			mLanguage = JAPANESE_CODE;
		else
			mLanguage = ENGLISH_CODE;
		save();
		load();
	}
	
	public static void load() {
		//TODO load settings from file
		try {
			ArrayList<String> dataList = UtilsFileIO.readAllLines("settings.txt", false);
			String data = dataList.get(0);
			mUseVibrate = charToBool(data.charAt(0));
			mMuteSound = charToBool(data.charAt(1));
			mLanguage = dataList.get(1);
		} catch (Exception ex) {
			
		}
	}
	
	private static String boolToString(boolean b) {
		if (b)
			return "1";
		return "0";
	}
	
	private static boolean charToBool(char s) {
		if (s == '1')
			return true;
		return false;
	}
}
