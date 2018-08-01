package com.sust.bomberman.level;

import com.sust.bomberman.entity.Entity;
import com.sust.bomberman.entity.mob.enemy.Enemy;
import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.level.tile.Tile;
import java.util.*;

public class Level {
	
	protected int width,height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static int score = 0;
	public static int highscore = 0;
	public static int timescore = 60;
	
	private List<Entity> entities = new ArrayList<Entity>();
	public static List<Enemy> enemies=new ArrayList<Enemy>();
	
	public static Level spawnLevel = new SpawnLevel("/levels/spawn2.png");
	public static Level spawnLevel1=new SpawnLevel("/levels/spawn3.png");
	public static Level spawnlevel2=new SpawnLevel("/levels/spawn4.png");
	
	public Level(int width,int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
        public void loadEnemies() {
            for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				getMob(x,y);
			}
		}
        }
	
	protected void loadLevel(String path) {
	}

	protected void generateLevel() {
	}
	
	public void update() {
		for(int i = 0;i < entities.size(); ++i) {
			entities.get(i).update();
		}
		for(int i = 0;i < entities.size(); ++i) {
			Entity e = entities.get(i);
			if( ((Entity)e).isRemoved() ) entities.remove(i);
		}
	}
	
	private void time() {
	}
	
	public void render(int xScroll,int yScroll,Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x,y).render(x, y, screen);
			}
		}
		
		for(int i = 0;i < entities.size(); ++i) {
			entities.get(i).render(screen);
		}
		
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.spawn_water;
//		if(tiles[x+y*width] == 0xff00ff00) return Tile.grass;
//		if(tiles[x+y*width] == 0xffffff00) return Tile.flower;
//		if(tiles[x+y*width] == 0xff757f00) return Tile.rock;
//		
		if(tiles[x+y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if(tiles[x+y*width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if(tiles[x+y*width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
                if(tiles[x+y*width] == Tile.col_spawn_wall3) return Tile.spawn_wall3;
		if(tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water;
		if(tiles[x+y*width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if(tiles[x+y*width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if(tiles[x+y*width]==0xffff03fe) return Tile.spawn_floor;
		return Tile.spawn_water;
	}
	
	public void updateBombs() {
		Iterator<Entity> itr = entities.iterator();
		
		while(itr.hasNext())
			itr.next().update();
	}
	
	public void getMob(int x,int y) {
		if(x<0||x>=width||y<0||y>=height) return;
		TileCoordinate coordinate = new TileCoordinate(x, y);
		if(tiles[x+y*width]==0xffff03fe) enemies.add(new Enemy(coordinate.x(),coordinate.y()));
	}
}
