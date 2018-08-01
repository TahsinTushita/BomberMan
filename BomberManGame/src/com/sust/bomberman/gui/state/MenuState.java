package com.sust.bomberman.gui.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.sust.bomberman.Game;
import com.sust.bomberman.gui.TileMap.Background;
import com.sust.bomberman.gui.menu.Help;
import com.sust.bomberman.sound.SoundPlayer;

import java.awt.*;

public class MenuState extends GameState {
	
	private Background bg;
	private int currentChoice=0;
	private String[] options= {
		"PLAY","HOW TO PLAY","QUIT"	
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private SoundPlayer bgm;
	
	public MenuState(GameStateManager gsm) {
		this.gsm=gsm;
		
		try {
                        bgm = new SoundPlayer("/sounds/Kimi no Na wa OST - Zen Zen Zense (piano)  Emotional.mp3");
                        bgm.play();
			bg=new Background("/textures/bg.jpg",1);
			bg.setVector(-0.1,0);
			titleColor=new Color(128,0,0);
			titleFont=new Font("Century Gothic",Font.BOLD,28);
			font=new Font("Arial",Font.PLAIN,20);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("BOMBERMAN", (Game.width-200)/2, 40);
		
		
		g.setFont(font);
		for(int i=0;i<options.length;i++) {
			if(i==currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i],(Game.width-60)/2, Game.height/2-40+i*40);
		}
	}
	
	public void select() {
		if(currentChoice==0) {
//			Game.play = true;
//			Game game = new Game();
			
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if(currentChoice==1) {
			gsm.setState(GameStateManager.HELPSTATE);
					Help help=new Help(gsm);
				}
		if(currentChoice==2) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_ENTER) {
			select();
		}
		
		if(k==KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice==-1) {
				currentChoice=options.length-1;
			}
		}
		
		if(k==KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice==options.length) {
				currentChoice=0;
			}
		}
		
	}

	@Override
	public void keyReleased(int k) {
	}

}
