package dev.pawelczyk.tilegame.states;

import java.awt.Graphics;

import dev.pawelczyk.tilegame.Game;
import dev.pawelczyk.tilegame.entities.creatures.Player;
import dev.pawelczyk.tilegame.worlds.World;

public class GameState extends State {
	
	private Player player;
	private World world;
	
	public GameState(Game game) {
		super(game);
		world = new World("res/worlds/world1.txt");
		player = new Player(game, world.getSpawnX(), world.getSpawnY());
	}

	@Override
	public void tick() {
		world.tick();
		player.tick();		
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}
}
