package com.sust.bomberman.level.tile.spawnlevel;

import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;
import com.sust.bomberman.level.tile.Tile;

public class SpawnHedgeTile extends Tile {

	public SpawnHedgeTile(Sprite sprite) {
		super(sprite);
	}
	public boolean solid() {
		return true;
	}
	@Override
	public void render(int x,int y,Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
