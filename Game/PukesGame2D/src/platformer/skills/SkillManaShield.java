package platformer.skills;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import platformer.entity.entityliving.EntityLiving;
import platformer.utils.Assets;

public class SkillManaShield extends Skill {

	private EntityLiving entity;
	
	public SkillManaShield(EntityLiving entity) {
		this.entity = entity;
		manaCost = 20;
		duration = 200;
		image = Assets.getItemManaShield();
	}
	
	@Override
	public void tick(){

	}
	
	@Override
	public void useSkill(){
		entity.applyEffect(this);
	}
	
	@Override
	public void render(Graphics2D g){

		g.setComposite(AlphaComposite.SrcOver.derive(0.4f));
		g.drawImage(Assets.getItemManaShield(), (int)entity.getX()-16, (int)entity.getY()-16, 86, 86, null);
		g.setComposite(AlphaComposite.SrcOver);

	}
	
	@Override
	public void CancelSkill() {
		/*
		duration = 200;
		durationTimer = 0;
		isActive = false;
		player.setActiveDuration(0);
		player.setMaxActiveDuration(0);
		*/
	}
}
