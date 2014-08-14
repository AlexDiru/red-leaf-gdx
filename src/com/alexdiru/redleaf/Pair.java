package com.alexdiru.redleaf;

public class Pair<L, R> {

	private L mLeft;
	private R mRight;
	
	public Pair(L left, R right) {
		mLeft = left;
		mRight = right;
	}
	
	public L getLeft() {
		return mLeft;
	}
	
	public R getRight() {
		return mRight;
	}
}
