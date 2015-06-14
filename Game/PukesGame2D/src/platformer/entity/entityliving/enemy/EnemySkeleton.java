package platformer.entity.entityliving.enemy;




import platformer.entity.entityliving.player.Player;
import platformer.utils.Assets;
import platformer.world.World;

public class EnemySkeleton extends Enemy{
	
	
	public EnemySkeleton(Player player, float x, float y, int monsterLevel) {
		super(player, x, y, monsterLevel);
		images = Assets.getImagesSkeletonLeft();
		healthMulti = 1;
		speedMulti = 1;
		damageMulti = 1;
		attackSpeedMulti = 1;
		setStats();
	}
	
	public void tick(){
		reset();	
		super.tick();
		
		if(inputLeft)images = Assets.getImagesSkeletonLeft();
		if(inputRight)images = Assets.getImagesSkeletonRight();
	}

}
