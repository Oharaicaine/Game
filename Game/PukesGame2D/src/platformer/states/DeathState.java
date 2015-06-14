package platformer.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import platformer.input.MouseManager;
import platformer.main.Main;
import platformer.utils.Assets;

public class DeathState extends State{
	
	private BufferedImage image;
	private MouseManager deathMouseManager;
	private boolean grace = true;
	private int graceTimer = 0;

	public DeathState(GameStateManager gsm) {
		
	}

	@Override
	public void init() {
		image = Assets.getItemGraveStone();
		deathMouseManager = new MouseManager();
		Main.getWindow().getCanvas().addMouseListener(deathMouseManager);
		
	}

	@Override
	public void tick() {
		if(!grace){
			if(deathMouseManager.getMouseOnePressed()){
				gsm.setState(gsm.MENUSTATE);
			}
		}else{
			graceTimer++;
			if(graceTimer > 100){
				grace = false;
				graceTimer = 0;
			}
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.getWidth(), Main.getHeight());
		g.drawImage(image, Main.getWidth()/2-200, Main.getHeight()/2-200, 400, 400, null);
		
	}

}
