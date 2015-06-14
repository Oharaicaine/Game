package platformer.entity.entityliving.enemy;

import platformer.entity.Projectile;
import platformer.entity.entityliving.player.Player;
import platformer.skills.SkillArcaneBall;
import platformer.skills.SkillManaShield;
import platformer.utils.Assets;
import platformer.utils.Check;
import platformer.world.World;

@SuppressWarnings("serial")
public class EnemyShaman extends Enemy{

	public EnemyShaman(Player player, float x, float y, int monsterLevel) {
		super(player, x, y, monsterLevel);
		images = Assets.getImagesShamanLeft();
		healthMulti = 0.8f;
		speedMulti = 0.8f;
		damageMulti = 0.5f;
		attackSpeedMulti = 0.5f;
		activeSkill = new SkillArcaneBall(this);
		setStats();
	}
	
	@Override
	public void tick(){
		reset();
		
		if(Check.DistanceFromPlayer(player, this)< 500 && canAttack && !grace){
			activeSkill.useSkill();
			canAttack = false;
		}
		super.tick();
		
		if(inputLeft)images = Assets.getImagesShamanLeft();
		if(inputRight)images = Assets.getImagesShamanRight();
	}
	
	@Override
	public boolean isRanged(){
		return true;
	}
		
}
