package com.sust.bomberman.level.tile;

import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(int x,int y,Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}
