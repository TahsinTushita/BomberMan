package com.sust.bomberman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sust.bomberman.Game;

public class Keyboard implements KeyListener{
	
	public boolean[] keys = new boolean[120];
	public boolean up,down,left,right,space,enter;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_0];
		enter=keys[KeyEvent.VK_ENTER];
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
