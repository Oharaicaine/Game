package platformer.skills;

import java.awt.Graphics2D;
import java.awt.Point;

import platformer.entity.Projectile;
import platformer.entity.entityliving.EntityLiving;
import platformer.entity.entityliving.player.Player;
import platformer.input.MouseManager;
import platformer.states.GameState;
import platformer.utils.Assets;
import platformer.world.World;


public class SkillArcaneBall extends Skill{
	
	private EntityLiving entity;
	
	public SkillArcaneBall(EntityLiving entity) {
		this.entity = entity;
		image = Assets.getItemMagicBall();
		manaCost = 10;	
	}
	
	public void useSkill(){
		if(entity instanceof Player){
			World.getProjectiles().add(new Projectile(entity, new Point(MouseManager.mouse) ,32, 5));
		}else{
			World.getProjectiles().add(new Projectile(entity, GameState.getPlayer() ,32, 5));
		}
		
	}
	
	public void render(Graphics2D g){
		
	}
	

}
