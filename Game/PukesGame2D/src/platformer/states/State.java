package platformer.states;

import java.awt.Graphics2D;

import platformer.main.GameLoop;

public abstract class State {
	
	protected GameLoop game;
	protected GameStateManager gsm;
	
	public abstract void init();
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);



	
	
	

}
