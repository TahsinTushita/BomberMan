package com.sust.bomberman.entity.bomb;

import java.util.List;
import java.util.ArrayList;

import com.sust.bomberman.entity.AnimatedEntity;
import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;
import com.sust.bomberman.level.tile.Tile;
import com.sust.bomberman.sound.SoundPlayer;

public class Bomb extends AnimatedEntity{

	private Screen screen;
	protected double timeToExplode = 130;
	public int timeAfter = 20;
	private Explosion explosion;
	protected boolean allowedToPassThru = true;
	protected boolean exploded = false;
	private SoundPlayer explosion_sound = new SoundPlayer("/sounds/explosion.mp3");
	private int x,y;
	private Sprite sprite;
	public static List<Explosion> explosions = new ArrayList<Explosion>();
	public Bomb(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		if(timeToExplode > 0) 
			timeToExplode--;
		else {
			if(!exploded)
				explode();
			if(timeAfter > 0)
				timeAfter--;
			else {
				remove();
				clearExplosions();
                                
				}
			}	
		animate();
	}
	
	private void clearExplosions() {
		explosions.clear();
	}

	public void render(Screen screen) {
		if(timeToExplode > 0) {
		if(animate%20>10) {
			sprite = Sprite.bomb;
		} else {
			sprite = Sprite.bomb_1;
		}
		screen.renderPlayer(x, y, sprite ,0);
		}
		else {
			sprite = Sprite.explosion;
//			screen.renderTile(x, y, Tile.bomb);
//			screen.renderTile(x-16, y, Tile.bomb);
//			screen.renderTile(x+16, y, Tile.bomb);
//			screen.renderTile(x, y+16, Tile.bomb);
//			screen.renderTile(x, y-16, Tile.bomb);

			screen.renderPlayer(x-16, y, sprite ,0);
			screen.renderPlayer(x+16, y, sprite ,0);
			screen.renderPlayer(x, y-16, sprite ,0);
			screen.renderPlayer(x, y+16, sprite ,0);
			screen.renderPlayer(x, y, Sprite.explosion_center ,0);
		}
		
	}
	
	public void renderExplosions(Screen screen) {
		
	}
	
	public void updateExplosions() {
		if(explosions.size() < 4) {
			explosions.add(new Explosion(x, y));
			explosions.add(new Explosion(x+16, y));
			explosions.add(new Explosion(x-16, y));
			explosions.add(new Explosion(x, y+16));
			explosions.add(new Explosion(x, y-16));
		}
	}
	
	public void explode() {
		exploded = true;
                explosion_sound.play();
		updateExplosions();
	}
	
	protected void explosion() {
		
	}
	
//	public Explosion explosionAt(int x, int y) {
//		
//	}
	
	public boolean isExploded() {
		return exploded;
	}
}
