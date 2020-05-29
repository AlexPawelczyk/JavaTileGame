/*
 * Main class of game
 */
package dev.pawelczyk.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.pawelczyk.tilegame.display.Display;
import dev.pawelczyk.tilegame.gfx.Assets;
import dev.pawelczyk.tilegame.gfx.GameCamera;
import dev.pawelczyk.tilegame.input.KeyManager;
import dev.pawelczyk.tilegame.states.GameState;
import dev.pawelczyk.tilegame.states.MenuState;
import dev.pawelczyk.tilegame.states.State;

public class Game implements Runnable {
	// defining instance variables
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States
	private State gameState;
	private State menuState;
	
	// Input
	private KeyManager keyManager;
	
	// Camera
	private GameCamera gameCamera;
	
	// constructor for Game
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	// initialize display and graphics for game
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		gameCamera = new GameCamera(this, 0, 0);
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}
	
	private void render() {
		// bufferstrategy => way for computer to draw things to screen
		// buffer => a "hidden" computer screen within your computer
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		// g => graphics object (a magical paint brush for drawing images to canvas)
		g = bs.getDrawGraphics();
		
		// clear screen
		g.clearRect(0, 0, width, height);
		
		// start drawing
		if(State.getState() != null) {
			State.getState().render(g);
		}

		
		// end drawing
		bs.show();
		g.dispose();	
	}
	
	private void tick() {
		keyManager.tick();
		
		// we have a current state that exists
		if(State.getState() != null) {
			State.getState().tick();
		}
		
	}
	
	public void run() {
		init();
		
		int fps = 60; 							// frames per sec (# of times we want to call tick and render methods every second)
		double timePerTick = 1000000000 / fps;	// max amount of time (in nanoseconds) that we have to execute tick and render methods
		double delta = 0;						// amount of time we have till we have to call tick and render methods again
		long now;								// current time of our computer
		long lastTime = System.nanoTime();		// previous time of computer in nanosecs
		long timer = 0;							// time until we get to one second (for bug checking)
		int ticks = 0;							// # of times our tick and render methods have been called
		
		// game loop
		while(running) {
			now = System.nanoTime();
			// (now - lastTime) => amount of time since we last called this line of code
			// delta => how much time till we have to call tick and render methods
			delta += (now - lastTime) / timePerTick;	// division keeps delta between 0 and 1
			timer += now - lastTime;					// update the timer
			lastTime = now;								// last time that above block of code was run
			
			// have to tick and render when delta >= 1 or we won't achieve our target fps
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			// if timer has been running for 1 second
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}	
		}
		stop();	
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running) return;			// if game is already running, exit this method
		
		running = true;				// indicate that the game is now running
		thread = new Thread(this);	// initialize new thread for Game class
		thread.start();				// calls run method	
	}
	
	public synchronized void stop() {
		if(!running) return;		// if game is not running, exit this method
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

}
