package com.sust.bomberman.entity.bomb;

import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.level.Level;
import com.sust.bomberman.level.tile.Tile;

public class Explosion {
	
	public int x,y;
	
	public Explosion(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
	public void render(Screen screen) {
		 System.out.println(x/16 + " " + y/16);
		 screen.renderTile(x << 4, y << 4, Tile.rock);
	}
	
}
