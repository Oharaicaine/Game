package platformer.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import platformer.world.World;

public class Entity extends Rectangle{

	protected float x, y;
	protected int entitySize = 64;
	protected float scale = 1.0f; 
	protected BufferedImage image;

	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
		init();
	}
	
	public void init(){
		setBounds((int)x, (int)y, (int)(entitySize * scale), (int)(entitySize * scale));
	}
	
	public void tick(){
		setBounds((int)x, (int)y, (int)(entitySize * scale), (int)(entitySize * scale));
	}
	
	public void render(Graphics2D g){
		g.drawImage(image,(int)x, (int)y, (int)(entitySize * scale), (int)(entitySize * scale), null);
	}
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	

}
