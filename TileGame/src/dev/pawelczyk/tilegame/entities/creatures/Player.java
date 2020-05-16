package dev.pawelczyk.tilegame.entities.creatures;

import java.awt.Graphics;

import dev.pawelczyk.tilegame.gfx.Assets;

public class Player extends Creature {

	public Player(float x, float y) {
		super(x, y);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, null);
		
	}

}
