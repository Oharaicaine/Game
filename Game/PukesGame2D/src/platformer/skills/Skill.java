package platformer.skills;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import platformer.entity.items.Item;

public class Skill {
	
	protected BufferedImage image;
	protected float manaCost;
	protected int duration;
	protected boolean isActive = false;
	
	public Skill() {
		
	}

	public void tick() {
			
	}
	
	public void render(Graphics2D g){
	}

	public void useSkill() {	
	}

	public float getManaCost() {
		return manaCost;
	}
	
	public boolean isActive(){
		return isActive;
	}

	public void CancelSkill() {
		return;	
	}

	public int getDuration() {	
		return duration;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	

}
