package dev.pawelczyk.tilegame.states;

import java.awt.Graphics;

import dev.pawelczyk.tilegame.Game;

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
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

}
