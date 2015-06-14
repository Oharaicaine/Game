package platformer.entity.entityliving;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import platformer.skills.Skill;

public class Buff {
	
	private int maxDuration, currentDuration, durationTimer = 0;
	private Skill skill;
	private BufferedImage image;
	private EntityLiving entity;

	public Buff(Skill skill, EntityLiving entity) {
		this.skill = skill;
		this.entity = entity;
		this.maxDuration = skill.getDuration();
		this.image = skill.getImage();
	}
	
	public void tick() {
		durationTimer++;
		if(durationTimer >= maxDuration){
			entity.getBuffs().remove(this);
			durationTimer = 0;
		}
		skill.tick();
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int)entity.getX()-10, (int)entity.getY()-40, 16, 16, null);
		skill.render(g);
	
	}
	
	public Skill getSkill() {
		return skill;
	}

}
