package platformer.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Utils {

	public static BufferedImage LoadImage(String path){
		try {
			return ImageIO.read(Utils.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static InputStream loadSound(String path) {
		try {
			return ClassLoader.class.getResourceAsStream(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
