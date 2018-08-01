package com.sust.bomberman.gui.state;

import java.util.ArrayList;
import com.sust.bomberman.gui.menu.*;

import com.sust.bomberman.Game;

public class GameStateManager{
	public ArrayList<GameState> gameStates;
	public static int currentState;
	public static final int MENUSTATE=0;
	public static final int LEVEL1STATE=2;
	public static final int HELPSTATE=1;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState=MENUSTATE;
		MenuState m = new MenuState(this);
		gameStates.add(m);
		HelpState h=new HelpState(this);
		gameStates.add(h);
		LevelState l=new LevelState(this);
		gameStates.add(l);
	}
	
	public void setState(int state) {
		currentState=state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
}
