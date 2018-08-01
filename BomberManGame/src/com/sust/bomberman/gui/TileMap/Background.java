package com.sust.bomberman.gui.TileMap;

import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

import com.sust.bomberman.Game;

public class Background {
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	
	public Background(String s,double ms) {
		try {
			image=ImageIO.read(getClass().getResourceAsStream(s));
			moveScale=ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x,double y) {
		this.x=(x*moveScale)%Game.width;
		this.y=(y*moveScale)%Game.height;
	}
	
	public void setVector(double dx,double dy) {
		this.dx=dx;
		this.dy=dy;
		
	}
	
	public void update() {
//		x+=dx;
//		y+=dy;
	}
	
	public void draw(Graphics2D g) {
		
		if(g==null) {
			System.out.println("g null");
			System.exit(0);
		}
		
		if(image==null) {
			System.out.println("image null");
			System.exit(0);
		}
		
		g.drawImage(image,(int)x,(int)y,null);
		if(x<0) {
			g.drawImage(image, (int)x+Game.width,(int) y, null);
		}
		if(x>0) {
			g.drawImage(image,(int)x-Game.width,(int)y,null);
		}
		if(y<0) {
			g.drawImage(image, (int)x,(int) y+Game.height, null);
		}
		if(y>0) {
			g.drawImage(image,(int)x,(int)y-Game.height,null);
		}
	}
}
