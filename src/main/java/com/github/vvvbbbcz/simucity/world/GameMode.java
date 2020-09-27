package com.github.vvvbbbcz.simucity.world;

public enum GameMode {
	NOT_ASKED(-1, "notAsked"),
	NORMAL(0, "normal"),
	CREATIVE(1, "creative"),
	HARDCORE(2, "hardcore"),
	DO_NOT_RUN(3, "do_not_run");

	private final int id;
	private final String name;

	GameMode(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
}
