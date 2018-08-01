package com.sust.bomberman.gui.menu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JPanel;

import com.sust.bomberman.Game;
import com.sust.bomberman.gui.state.GameState;
import com.sust.bomberman.gui.state.GameStateManager;

public class Levels extends JPanel implements KeyListener{
	
	private BufferedImage image;
	private Graphics2D g;
	private boolean running=false;
	private GameStateManager gsm;
	
	private int FPS=60;
	private long targetTime=1000/FPS;
	
	
	public Levels() {
		super();
		setPreferredSize(new Dimension(Game.width*Game.scale,Game.height*Game.scale));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		init();
	}
	
	
	
	public void init() {
		image=new BufferedImage(Game.width,Game.height,BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D) image.getGraphics();
		running=true;
		gsm=new GameStateManager();
	}
	
	public void run() {
		long start;
		long elapsed;
		long wait;
		
		while(running) {
			start=System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed=System.nanoTime()-start;
			wait=targetTime-elapsed/1000000;
			
			if(Game.play == true) break;
		}
	}
	
	
	public void update() {
		gsm.update();
	}
	
	public void draw() {
		gsm.draw(g);
	}
	
	public void drawToScreen() {
		Graphics g2=getGraphics();
		g2.drawImage(image,0,0,Game.width*Game.scale,Game.height*Game.scale,null);
		g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
