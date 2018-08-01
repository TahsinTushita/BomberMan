package com.sust.bomberman.level.tile;

import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x,int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
//	@Override
//	public boolean solid() {
//		return true;
//	}
}
