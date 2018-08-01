package com.sust.bomberman.graphics;

import java.util.Random;

import com.sust.bomberman.entity.mob.Player;
import com.sust.bomberman.level.tile.Tile;

public class Screen {
	
	public int width,height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_MASK = MAP_SIZE - 1;
	public int xOffset,yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();
	
	public Screen(int width,int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		
		for(int i=0;i < tiles.length;++i) {
			tiles[i]= random.nextInt(0xffffff);
		}
	}
	
	public Screen(int width,int height,int color) {
		this.width=width;
		this.height=height;
		pixels=new int[width*height];
		color=0xffff00ff;
		for(int i=0;i<tiles.length;i++) {
			tiles[i]=color;
		}
	}
	
//	public void render(int xOffset,int yOffset) {
//		for(int y=0;y< height;y++) {
//			int yp = y + yOffset;
//			if(yp<0||yp>=height) continue;
//			for(int x=0;x<width;x++) {
//				int xp = x + xOffset;
//				if(xp <0 || xp >= width) continue;
//				pixels[xp + yp*width] = Sprite.grass.pixels[(x &15) + (y&15)* Sprite.grass.SIZE];
//			}
//		}
//	}
//	
	public void clear() {
		for(int i = 0;i < pixels.length;++i) {
			pixels[i]=0;
		}
	}
	
	public void renderTile(int xp,int yp,Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y=0;y< tile.sprite.SIZE;y++) {
			int ya = y + yp;
			for(int x=0;x < tile.sprite.SIZE;x++) {
				int xa=x + xp;
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; 
				if(xa < 0) xa = 0;
				int col = tile.sprite.pixels[x + y* tile.sprite.SIZE];
				if(col != 0xff000000)pixels[xa+ya*width] = col;
			}
		}
	}
	public void renderPlayer(int xp, int yp, Sprite sprite,int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			if(flip == 2 || flip == 3) {
				ys = 31 - y;
			}
			for(int x = 0;x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if(flip == 1|| flip ==3) {
					xs = 31 - x;
				}
				if(xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0; 
				int col = sprite.pixels[xs + ys * 32];
				if(col != 0xff000000) pixels[xa + ya * width] = col;
			}
		}
	}
	public void setOffset(int xOffset,int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
