/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.bomberman.level.tile.spawnlevel;

import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;
import com.sust.bomberman.level.tile.Tile;

/**
 *
 * @author ishan
 */
public class SpawnWall3Tile extends Tile{
    
    public SpawnWall3Tile(Sprite sprite) {
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
