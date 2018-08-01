package com.sust.bomberman.level.tile;

import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;
import com.sust.bomberman.level.tile.spawnlevel.SpawnFloorTile;
import com.sust.bomberman.level.tile.spawnlevel.SpawnGrassTile;
import com.sust.bomberman.level.tile.spawnlevel.SpawnHedgeTile;
import com.sust.bomberman.level.tile.spawnlevel.SpawnWall1Tile;
import com.sust.bomberman.level.tile.spawnlevel.SpawnWall2Tile;
import com.sust.bomberman.level.tile.spawnlevel.SpawnWall3Tile;
import com.sust.bomberman.level.tile.spawnlevel.SpawnWaterTile;

public class Tile {
	
	public int x,y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower= new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile bomb = new BombTile(Sprite.bomb);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_wall1 = new SpawnWall1Tile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWall2Tile(Sprite.spawn_wall2);
        public static Tile spawn_wall3 = new SpawnWall3Tile(Sprite.spawn_wall3); 
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	
	public static final int col_spawn_grass = 0xff00ff00;
	public static final int col_spawn_wall1 = 0xff808080;
	public static final int col_spawn_wall2 = 0xff404040;
        public static final int col_spawn_wall3 = 0xff8080ec;
	public static final int col_spawn_floor = 0xffff6a00;
	public static final int col_spawn_hedge = 0;
	public static final int col_spawn_water = 0xff00ffff;
	
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x,int y,Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}
	
}
