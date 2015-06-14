package platformer.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound implements Runnable{
	
	private String file;
	
	public Sound(String file) {
		this.file = file;
		Thread thread = new Thread(this);
		thread.start();	
	}

	@Override
	public void run() {
	
		try {
			Player play = new Player(ClassLoader.class.getResourceAsStream(file));
			play.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

}
