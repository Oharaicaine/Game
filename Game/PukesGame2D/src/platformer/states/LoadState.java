package platformer.states;

import java.awt.Graphics2D;
import java.io.Serializable;

import platformer.utils.LoadGame;

public class LoadState extends State implements Serializable{

	private static final long serialVersionUID = 1L;
	
	

	public LoadState(GameStateManager gsm) {
		
		
	}

	@Override
	public void init() {
		new LoadGame();

	}

	@Override
	public void tick() {


	}

	@Override
	public void render(Graphics2D g) {


	}

}
