package com.sust.bomberman.entity;

import java.util.Random;

import com.sust.bomberman.entity.mob.Player;
import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.level.Level;

public abstract class Entity {
	public int x,y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	
	public void update() {
		
	}
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
		Player.BOMBS_NOW--;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	public int[] getPos() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
}
