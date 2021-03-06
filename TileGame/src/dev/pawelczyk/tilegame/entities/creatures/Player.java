package dev.pawelczyk.tilegame.entities.creatures;

import java.awt.Graphics;

import dev.pawelczyk.tilegame.Game;
import dev.pawelczyk.tilegame.gfx.Assets;

public class Player extends Creature {

	public Player(Game game, float x, float y) {
		super(game, x * Creature.DEFAULT_CREATURE_WIDTH, y * Creature.DEFAULT_CREATURE_HEIGHT, 
				Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	}

	@Override
	public void tick() {
		getInput();
		move(); // from Creature class
		game.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up) {
			yMove = -speed;
		}
		if(game.getKeyManager().down) {
			yMove = speed;
		}
		if(game.getKeyManager().left) {
			xMove = -speed;
		}
		if(game.getKeyManager().right) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) (x  - game.getGameCamera().getxOffset()), 
				(int) (y - game.getGameCamera().getyOffset()), width, height, null);
		
	}

}
