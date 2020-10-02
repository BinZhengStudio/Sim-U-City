package com.github.vvvbbbcz.simucity.world;

public enum GameMode {
	NOT_ASKED(-1),
	NORMAL(0),
	CREATIVE(1),
	DIFFICULT(2),
	DO_NOT_RUN(3);

	private final int id;

	GameMode(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static GameMode getModeFromId(int id) {
		if (id == -1) {
			return NOT_ASKED;
		} else if (id == 0) {
			return NORMAL;
		} else if (id == 1) {
			return CREATIVE;
		} else if (id == 2) {
			return DIFFICULT;
		} else if (id == 3) {
			return DO_NOT_RUN;
		}
		return NOT_ASKED;
	}
}
