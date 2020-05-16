package dev.pawelczyk.tilegame.worlds;

import java.awt.Graphics;

import dev.pawelczyk.tilegame.tiles.Tile;
import dev.pawelczyk.tilegame.utils.Utils;

public class World {
	private int width, height; // width and height in terms of tiles (i.e., 5x5 tile world)
	private int spawnX, spawnY;
	private int[][] tiles;
	
	public World(String path) {
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
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
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); // +4 because we manually set first 4 elements
			}
		}
		
		
		
		
		
	}

}
