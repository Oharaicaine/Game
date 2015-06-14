package platformer.main;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import platformer.display.WindowDisplay;
import platformer.states.GameStateManager;
import platformer.utils.Assets;

public class GameLoop implements Runnable{

	private boolean running = false;
	private Thread thread;
	private WindowDisplay window;
	
	private BufferStrategy bs;
	private Graphics2D g;
	
	private GameStateManager gsm;	
	private int width, height;
	
	public GameLoop(WindowDisplay window) {
		this.window = window;
	}
	
	private void init(){
		width = window.getWidth();
		height = window.getHeight();
		Assets.init();
		gsm = new GameStateManager();

		GameStateManager.setState(GameStateManager.MENUSTATE);
	}
	
	private void tick(){
		gsm.tick();
	}
	
	private void render(){
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null){
			window.getCanvas().createBufferStrategy(2);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//RENDER START!
		
		gsm.render(g);
		
		//RENDER STOP!
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		
		//GAME LOOP
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double deltaTime = 0;
		long now;
		long lastTime = System.nanoTime();		
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			deltaTime += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(deltaTime >= 1){
				tick();
				render();
				ticks++;
				deltaTime--;
			}	
			if(timer >= 1000000000){
				//System.out.println("ticks"+ ticks);
				timer = 0;
				ticks = 0;
			}
		}
		stop();
		
	}
	
	public synchronized void start(){
		if(running)return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
