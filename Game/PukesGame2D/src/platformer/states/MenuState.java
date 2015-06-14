package platformer.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import platformer.input.MouseManager;
import platformer.main.Main;
import platformer.utils.Assets;
import platformer.utils.ButtonHelper;

public class MenuState extends State{
	
	private BufferedImage background;
	private ButtonHelper startButton;
	private ButtonHelper quitButton;
	private ButtonHelper loadButton;
	private int screenWidth, screenHeight;
	private MouseManager menuMouseManager;
	private boolean grace;
	private int graceTimer = 0;

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		screenWidth = Main.getWidth();
		screenHeight = Main.getHeight();
	}

	@Override
	public void init() {
		grace = true;
		background = Assets.getBackGround();
		menuMouseManager = new MouseManager();
		Main.getWindow().getCanvas().addMouseListener(menuMouseManager);
		Main.getWindow().getCanvas().addMouseMotionListener(menuMouseManager);
		Main.getWindow().getCanvas().addMouseWheelListener(menuMouseManager);
		startButton = new ButtonHelper("Start", screenWidth / 2 - 100, screenHeight - screenHeight/8 * 3, 200, 100, menuMouseManager);
		loadButton = new ButtonHelper("Load", screenWidth / 2 - 100, screenHeight - screenHeight/8 * 2, 200, 100, menuMouseManager);
		quitButton = new ButtonHelper("Quit", screenWidth / 2 - 100, screenHeight - screenHeight/8 * 1, 200, 100, menuMouseManager);
		
	}

	@Override
	public void tick() {
		if(!grace){
		startButton.tick();
		loadButton.tick();
		quitButton.tick();
		menuMouseManager.tick();	
		}else if(grace){
			graceTimer++;
			if(graceTimer > 5){
				grace = false;
				graceTimer = 0;
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(background, 0, 0, Main.getWidth(), Main.getHeight()/2, null);
		g.drawImage(background, 0, Main.getHeight()/2, Main.getWidth(), Main.getHeight()/2, null);
		startButton.render(g);
		loadButton.render(g);
		quitButton.render(g);
		
	}

}
