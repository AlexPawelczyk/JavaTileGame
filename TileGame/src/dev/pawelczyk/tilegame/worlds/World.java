package dev.pawelczyk.tilegame.worlds;

import java.awt.Graphics;

import dev.pawelczyk.tilegame.Game;
import dev.pawelczyk.tilegame.tiles.Tile;
import dev.pawelczyk.tilegame.utils.Utils;

public class World {
	private Game game;
	private int width, height; // width and height in terms of tiles (i.e., 5x5 tile world)
	private int spawnX, spawnY;
	private int[][] tiles;
	
	public World(Game game, String path) {
		this.game = game;
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, game.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (game.getGameCamera().getxOffset() + game.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, game.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (game.getGameCamera().getyOffset() + game.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, 
						(int) (x * Tile.TILE_WIDTH - game.getGameCamera().getxOffset()), 
						(int) (y * Tile.TILE_HEIGHT - game.getGameCamera().getyOffset()));
			}
		}	
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[this.tiles[x][y]];
		
		if(t == null) {
			return Tile.dirtTile;
		}
	
		return t;
	}
	
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+"); // splits every number from txt file into separate string
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		this.setSpawnX(Utils.parseInt(tokens[2]));
		this.setSpawnY(Utils.parseInt(tokens[3]));
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); // +4 because we manually set first 4 elements
			}
		}
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

}
