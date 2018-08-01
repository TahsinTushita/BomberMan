package com.sust.bomberman.level;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import com.sust.bomberman.level.tile.GrassTile;
import com.sust.bomberman.level.tile.Tile;

public class SpawnLevel extends Level {

	private String[] lineTiles;
	
	public SpawnLevel(String path) {
		super(path);
	}
	
	@Override
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width  =  image.getWidth();
			int h = height =  image.getHeight();
			tiles= new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			System.out.println("Sorry Level file not loaded");
		}

	}
		
	protected void generateLevel() {
//		System.out.println("Tiles: " + tiles[0]);
	}
}
