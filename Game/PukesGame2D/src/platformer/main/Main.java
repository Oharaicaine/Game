package platformer.main;

import platformer.display.WindowDisplay;

public class Main {
	
	//private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	private static WindowDisplay window;
	private static GameLoop game;
	private static int width, height;
	//private static int width = 1220, height = 840;

	public static void main(String[] args) {
		
		//new Sound("/test.mp3");
		//width = gd.getDisplayMode().getWidth();
		//height = gd.getDisplayMode().getHeight();
		width = 1918;
		height = 1058;
		window = new WindowDisplay("Demo", width, height);
		game = new GameLoop(window);
		game.start();	

	}
	
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	
	public static WindowDisplay getWindow() {
		return window;
	}

}
