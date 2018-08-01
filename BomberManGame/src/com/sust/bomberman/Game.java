package com.sust.bomberman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.*;

import javax.swing.JFrame;

import com.sust.bomberman.entity.bomb.Bomb;
import com.sust.bomberman.entity.bomb.Explosion;
import com.sust.bomberman.entity.mob.Player;
import com.sust.bomberman.entity.mob.enemy.Enemy;
import com.sust.bomberman.graphics.Screen;
import com.sust.bomberman.gui.menu.Menu;
import com.sust.bomberman.gui.state.LevelState;
import com.sust.bomberman.input.Keyboard;
import com.sust.bomberman.level.Level;
import com.sust.bomberman.level.RandomLevel;
import com.sust.bomberman.level.SpawnLevel;
import com.sust.bomberman.level.TileCoordinate;
import com.sust.bomberman.sound.SoundPlayer;

public class Game extends Canvas implements Runnable {
	public static int width = 500;
	public static int height = (width / 16) * 9;
	public static int scale = 2;
	public static String TITLE = "Bomberman";
	public static int BOMBS_CNT = 1;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Player player;
	private Enemy enemy;
        private int lives = 3;
	private boolean running = false;
	private boolean playerdead = false;
	
	private Screen screen;
	private  Level level;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	private TileCoordinate playerSpawn;
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale); 
		setPreferredSize(size);
		
		screen = new Screen(width,height);
		frame = new JFrame();
		key = new Keyboard();
//                Level.enemies.clear();
////		level=Level.spawnLevel;
//		System.out.println(LevelState.level);
//		
//		playerSpawn = new TileCoordinate(15,10);
//		player = new Player(playerSpawn.x(),playerSpawn.y(),key);
//		player.init(level);
//		for(int i=0;i<Level.enemies.size();i++) {
//			Level.enemies.get(i).init(level);
//		}
//		addKeyListener(key);
	}
	
	public synchronized void start() {

		if(LevelState.level==0) level=Level.spawnLevel;
		if(LevelState.level==1) level=Level.spawnLevel1;
		if(LevelState.level==2) level=Level.spawnlevel2;
                level.loadEnemies();
		playerSpawn = new TileCoordinate(10,10);
		player = new Player(playerSpawn.x(),playerSpawn.y(),key);
		player.init(level);
		for(int i=0;i<Level.enemies.size();i++) {
			Level.enemies.get(i).init(level);
		}
		addKeyListener(key);
		
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
			update();
			updates++;
			delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer >= 1000) {
				if(!playerdead)Level.timescore--;
				timer+=1000;
				frame.setTitle(TITLE + " | " + updates + " ups " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	private void render() {
		
		
		screen.clear();
		int xScroll = player.x - screen.width / 2 ;
		int yScroll = player.y - screen.height / 2;
	
		level.render(xScroll, yScroll, screen);
		if(!playerdead)player.render(screen);
		
		for(int i=0;i<Level.enemies.size();i++) {
			Level.enemies.get(i).render(screen);
		}
		
		
//		screen.render(x,y);
		
		for(int i = 0; i < pixels.length;++i) {
			pixels[i] = screen.pixels[i];
		}
		
		draw();
		
	}
	
	public void draw() {
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		g.setFont(new Font("Times New Roman", 0, 25));
		g.fillRect(0, 0, getWidth(), 30);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + Level.score, 0, 25); 
		g.drawString("Time: " + Level.timescore, width*scale/2-250,25);
                g.drawString("Lives: " + lives, width*scale/2-40,25);
		g.drawString("HighScore: " + Level.highscore,width*scale-300,25);
		g.setFont(new Font("Ariels",2,40));
                if(Level.enemies.size()==0) {
                    playerdead = true;
                    g.drawString("YOU WIN!!", 350, 300);
                }
                else if(playerdead) g.drawString("Game Over!" ,350,300);
		g.dispose();
		bs.show();	
	}
	
	int x = 0,y = 0;
	
	private void update() {
		key.update();
		detectCollision();
		if(!playerdead)player.update();
                if(Level.timescore <= 0) playerdead = true;
		if(playerdead) {
			setHighScore();
		}
		for(int i=0;i<Level.enemies.size();i++) {
			Level.enemies.get(i).update();
		}
		level.update();
		level.updateBombs();
		loadHighScore();
	}
	private void loadHighScore() {
		String path = "gameData.txt";
		File file = new File(path);
		FileReader fr = null;
		BufferedReader br = null;
		if(file.exists()) {
			try {
//				System.out.println("Hiiiii");
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String s = br.readLine();
//				System.out.println(s);
				int n = 0;
				for(int i =0 ;i < s.length();++i) {
					n = n*10 + s.charAt(i)-'0';
				}
//				System.out.println(n);
				Level.highscore = n;
				br.close();
				fr.close();
			} catch (Exception e) {
				// TODO: handle exception
			} 
			
		}
		
	}
	private boolean dataWritten = false;
	private void setHighScore() {
		if(dataWritten == false && 	(Level.highscore < Level.score)) {
		String path = "gameData.txt";
		BufferedWriter bw = null; 
		FileWriter fw = null;
		File file = new File(path);
		if(file.exists() == false) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("File banaite parinai");
			}
		}
		try {
			fw = new FileWriter(path);
			
			bw = new BufferedWriter(fw);
			Integer n = level.score;
			String c = n.toString();
			bw.write(c);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataWritten = true;
		}
	}
	
	
	public void detectCollision() {
		int[] playerPos = player.getPos();
		int px1 = playerPos[0];
		int px2 = px1+16;
		int py1 = playerPos[1];
		int py2 = py1+16;
		for(int i=0;i<Level.enemies.size();i++) {
			int[] enemyPos = Level.enemies.get(i).getPos();
			int ex1 = enemyPos[0];
			int ex2 = ex1 + 16;
			int ey1 = enemyPos[1];
			int ey2 = ey1 + 16;
			if((px1 <= ex2 && px2 >= ex1) && (py1 <= ey2 && py2 >= ey1) ) playerdead = true;
			for(int j=0;j<Bomb.explosions.size();++j) {
				int bx1 = Bomb.explosions.get(j).x;
				int bx2 = bx1 + 32;
				int by1 = Bomb.explosions.get(j).y;
				int by2 = by1 + 32;
//				System.out.println("H I");
				if((bx1 <= ex2 && bx2 >= ex1) && (by1 <= ey2 && by2 >= ey1)) {
					Level.score+=Level.timescore*100;
					Level.enemies.remove(i);
					break;
					
				}
				if((bx1 <= px2 && bx2 >= px1) && (by1 <= py2 && by2 >= py1)) {
                                        playerdead = true;
					break;
					
				}
			}
		}
                if(playerdead && lives > 0) {
                    player.setPos(100, 95);
                    playerdead = false;
                    lives--;
                }
		
	}
//
//private void doCollision(int i, int x, int y,Object o) {
//		if(i == 2) {
//			Level.enemies.remove(o);
//		}
//	}

//	private void doCollision(int i, int x, int y) {
//		if(i==2) 
//	}
	public static boolean play = false;
	
	public static void main(String[] args) {
		Game game = new Game();
		Menu menu = new Menu();
		while(true) {
		game.frame.setResizable(false);
		game.frame.setTitle(Game.TITLE);
		if(game.play == false) {
		game.frame.add(menu);
		} else {
			game.frame.remove(menu);
			game.frame.add(game);
		}
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		if(game.play == false) menu.run();
		else {
		game.start();
		break;
		}
		}
	}

	
}
