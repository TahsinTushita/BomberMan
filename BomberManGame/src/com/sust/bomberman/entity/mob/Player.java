package com.sust.bomberman.entity.mob;

import java.util.List;
import java.util.ArrayList;

import com.sust.bomberman.Game;
import com.sust.bomberman.entity.bomb.Bomb;
import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;
import com.sust.bomberman.input.Keyboard;
import com.sust.bomberman.level.TileCoordinate;

public class Player extends Mob{
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	public static int BOMBS_NOW = 0;
	
	protected List<Bomb> bombs = new ArrayList<Bomb>();
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
	}
	public Player(int x, int y,Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		if(anim< 7500) anim++;
		else anim = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false; 
		}

		updateBomb();
	}
	public void setPos(int x,int y) {
            this.x = x;
            this.y = y;
        }
	private void updateBomb() {
		if(input.space) dropBomb(x, y);
	}
	public void render(Screen screen) {
		int flip = 0;
		if(dir == 0) { 
			sprite = Sprite.player_forward;
			if(walking) {
				if(anim%20>10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if(dir == 1 || dir == 3) {
			sprite = Sprite.player_side;
			if(walking) {
				if(anim%20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
		}
		if(dir == 2) {
			sprite = Sprite.player_back;
			if(walking) {
				if(anim%20 > 10) {
					sprite = Sprite.player_back_1;
				} else {
					sprite = Sprite.player_back_2;
				}
			}
		}
		if(dir == 3) {
			flip = 1; 
		}
		screen.renderPlayer(x - 16, y - 16, sprite,flip);
		}
	
	public void dropBomb(int x,int y) {
		Bomb b = new Bomb(x-16,y-16);
		if(BOMBS_NOW < Game.BOMBS_CNT) {
			level.add(b);
			BOMBS_NOW++;
		}
	}
}
