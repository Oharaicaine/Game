package platformer.states;

import java.awt.Graphics2D;

import platformer.display.HUDManager;
import platformer.entity.entityliving.player.Player;
import platformer.input.KeyManager;
import platformer.input.MouseManager;
import platformer.main.Main;
import platformer.world.Spawner;
import platformer.world.World;


public class GameState extends State{

	private static Player player;
	private static World world;
	private HUDManager hud;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private GameStateManager gsm;
	private Spawner spawn;
	private boolean hasBeenInit = false;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		
	}	
	
	@Override
	public void init() {
		if(!hasBeenInit){
			hasBeenInit = true;
			keyManager = new KeyManager();
			mouseManager = new MouseManager();
			Main.getWindow().getCanvas().addMouseListener(mouseManager);
			Main.getWindow().getFrame().addKeyListener(keyManager);
			world = new World(this);
			player = new Player(this, 200, 200);
			spawn = new Spawner(world, player);
			hud = new HUDManager(player);
		}
	}
	
	@Override
	public void tick() {
		keyManager.tick();
		mouseManager.tick();
		world.tick();
		player.tick();
		spawn.tick();
		hud.tick();
	}

	@Override
	public void render(Graphics2D g) {
		world.render(g);
		player.render(g);
		hud.render(g);
		
	}
	
	public HUDManager getHud() {
		return hud;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameStateManager getGsm() {
		return gsm;
	}
	
	public Spawner getSpawner() {
		return spawn;
	}
	
	public static Player getPlayer(){
		return player;
	}

	public static World getWorld() {
		
		return world;
	}

	public static void setPlayer(Player player) {
		GameState.player = player;
	}

}
