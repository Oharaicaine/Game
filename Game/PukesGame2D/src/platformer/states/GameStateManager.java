package platformer.states;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

	private static ArrayList<State> gameStates;
	private static int currentState;
	//private static State currentState = null;
	
	public static final int MENUSTATE = 0;
	public static final int GAMESTATE = 1;
	public static final int DEATHSTATE = 2;
	public static final int LOADSTATE = 3;
	
	public static boolean gamestateLoaded = false;
	
	public GameStateManager(){
		gameStates = new ArrayList<State>();
		
		
		gameStates.add(new MenuState(this));
		gameStates.add(new GameState(this));
		gameStates.add(new DeathState(this));
		gameStates.add(new LoadState(this));
		currentState = MENUSTATE;
		
	}
	
	public static void setState(int state){
		currentState = state;
		if(!gamestateLoaded)gameStates.get(currentState).init();
		
		if(gameStates.get(currentState) == gameStates.get(LOADSTATE)){
			gameStates.get(GAMESTATE).init();
			gamestateLoaded = true;
		}
	}
	
	public State getState(){
		return gameStates.get(currentState);
	}
	
	 public void tick() {
		 gameStates.get(currentState).tick();
	 }
	 
	 public void render(Graphics2D g){
		 gameStates.get(currentState).render(g);
	 }
	 
	 
	 
	 
	 
}
