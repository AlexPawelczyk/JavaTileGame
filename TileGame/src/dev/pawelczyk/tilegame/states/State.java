package dev.pawelczyk.tilegame.states;

import java.awt.Graphics;

public abstract class State {
	
	
	// GAME STATE MANAGER
	private static State currentState = null;	// curr state we want to tick and render
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	
	
	// STATE CLASS
	public abstract void tick();
	
	public abstract void render(Graphics g);

}
