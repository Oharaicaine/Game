package platformer.entity.entityliving.enemy;

import platformer.entity.entityliving.player.Player;
import platformer.utils.Assets;
import platformer.utils.Check;
import platformer.world.World;

public class EnemyRhino extends Enemy{
	

	public EnemyRhino(Player player, float x, float y, int monsterLevel) {
		super(player, x, y, monsterLevel);
		images = Assets.getImagesRhinoLeft();
		healthMulti = 1.5f;
		speedMulti = 1.0f;
		damageMulti = 0.8f;
		attackSpeedMulti = 1.2f;
		critMulti = 2f;
		setStats();
	}
	
	public void tick(){
		reset();
		
		if(Check.DistanceFromPlayer(player, this) < 350 ){
			speed = 5f;
		}

		super.tick();

		if(inputLeft)images = Assets.getImagesRhinoLeft();
		if(inputRight)images = Assets.getImagesRhinoRight();
	}
	
	@Override
	public boolean canBeKnockback() {
		return true;
	}


}
