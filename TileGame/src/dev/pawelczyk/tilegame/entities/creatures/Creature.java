package dev.pawelczyk.tilegame.entities.creatures;

import dev.pawelczyk.tilegame.entities.Entity;

public abstract class Creature extends Entity {
	
	protected int health;

	public Creature(float x, float y) {
		super(x, y);
		health = 10;
		// TODO Auto-generated constructor stub
	}

}
