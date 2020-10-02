package com.github.vvvbbbcz.simucity.world.storage;

public class PlayerCredits {
	private int credits;

	public PlayerCredits() {
		this.credits = 10;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits, boolean isAdd) {
		if (isAdd) {
			this.credits += credits;
		} else {
			this.credits = credits;
		}
	}
}
