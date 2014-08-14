package com.alexdiru.redleaf;

//Replicates Android API's Rect class (at least the required functionality)
public class Rect {
	public int left, right, top, bottom;
	
	public Rect() {
	}

	public int getHeight() {
		return Math.abs(bottom - top);
	}
	
	public int getWidth() {
		return Math.abs(right - left);
	}
}