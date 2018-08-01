package com.sust.bomberman.gui.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.sust.bomberman.Game;
import com.sust.bomberman.gui.TileMap.Background;
import com.sust.bomberman.level.Level;

public class LevelState extends GameState{
	
	private Background bg;
	private int currentChoice=0;
	private String[] options= {
		"LEVEL 1","LEVEL 2","LEVEL 3"	
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	public static int level;
	
	public LevelState(GameStateManager gsm) {
		this.gsm=gsm;
		
		try {
			bg=new Background("/textures/bg.jpg",1);
			bg.setVector(-0.1,0);
			titleColor=new Color(128,0,0);
			titleFont=new Font("TagsXtreme",Font.BOLD,28);
			font=new Font("Ubuntu",Font.PLAIN,20);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("LEVELS", (Game.width-100)/2, 40);
		
		
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
			level=0;
			Game.play=true;
			Game game=new Game();
		
		}
		
		if(currentChoice==1) {
			level=1;
			Game.play=true;
			Game game=new Game();
		
		}
		
		if(currentChoice==2) {
			level=2;
		
		}

		Game.play=true;
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
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
		if(k==KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
}
