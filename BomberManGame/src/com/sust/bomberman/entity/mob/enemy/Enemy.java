package com.sust.bomberman.entity.mob.enemy;

import java.util.Random;

import com.sust.bomberman.entity.mob.Mob;
import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.graphics.Sprite;
import com.sust.bomberman.input.Keyboard;

public class Enemy extends Mob{

	private Sprite sprite;
	private int anim = 0;
	private Keyboard input;
	private Random random;
	private boolean walking = false;
	private int time;
	private int direction;
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.enemy_forward;
		
		random = new Random();
		time = random.nextInt(100);
		direction = random.nextInt(4);
	}
	
	public void update() {
		int xa = 0, ya = 0;
		if(anim< 7500) anim++;
		else anim = 0;
		if(time == 0) {
			time = random.nextInt(100);
			direction = random.nextInt(4);
		}
		
		if(direction == 0) ya--;
		if(direction == 1) ya++;
		if(direction == 2) xa--;
		if(direction == 3) xa++;
//		if(input.up) direction = 3;
//		if(input.down) direction = 2;
//		if(input.left) direction = 1;
//		if(input.right) direction = 0;
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false; 
		}
		
		if(collision(xa,ya)) {
			direction = random.nextInt(4);
		}
		time--;
	}

	public void render(Screen screen) {
		int flip = 0;
		if(dir == 0) { 
			sprite = Sprite.enemy_forward;
			if(walking) {
				if(anim%20>10) {
					sprite = Sprite.enemy_forward_1;
				} else {
					sprite = Sprite.enemy_forward_2;
				}
			}
		}
		if(dir == 1 || dir == 3) {
			sprite = Sprite.enemy_side;
			if(walking) {
				if(anim%20 > 10) {
					sprite = Sprite.enemy_side_1;
				} else {
					sprite = Sprite.enemy_side_2;
				}
			}
		}
		if(dir == 2) {
			sprite = Sprite.enemy_back;
			if(walking) {
				if(anim%20 > 10) {
					sprite = Sprite.enemy_back_1;
				} else {
					sprite = Sprite.enemy_back_2;
				}
			}
		}
		if(dir == 3) {
			flip = 1; 
		}
		screen.renderPlayer(x - 16, y - 16, sprite,flip);
		}

}
