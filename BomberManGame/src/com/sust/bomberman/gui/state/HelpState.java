package com.sust.bomberman.gui.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.sust.bomberman.Game;
import com.sust.bomberman.gui.TileMap.Background;

public class HelpState extends GameState{
	
	private Background bg;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private String[] lines={"MOVEMENT: ","Up: UP,W","Down: DOWN,S","Left: LEFT,A","Right: RIGHT,D",
                                    "Place Bomb: Space","Kill all the enemies in the","limited time to win",
                                        "Don't collide with them"};
	
	public HelpState(GameStateManager gsm) {
		this.gsm=gsm;
		
		try {
			bg=new Background("/textures/helpbg.png",1);
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
		g.drawString("HOW TO PLAY", (Game.width-200)/2, 40);
		
		g.setFont(font);
		
                for(int i=0;i<lines.length;i++) {
		 g.drawString(lines[i], (Game.width-60)/4, Game.height-200+i*20);
		}
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k==KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
