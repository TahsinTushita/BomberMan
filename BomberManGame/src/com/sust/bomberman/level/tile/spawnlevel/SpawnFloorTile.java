package com.sust.bomberman.level.tile.spawnlevel;

import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;
import com.sust.bomberman.level.tile.Tile;

public class SpawnFloorTile extends Tile {

	public SpawnFloorTile(Sprite sprite) {
		super(sprite);
	}
	@Override
	public void render(int x,int y,Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
