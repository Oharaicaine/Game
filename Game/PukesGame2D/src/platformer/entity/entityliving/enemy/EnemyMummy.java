package platformer.entity.entityliving.enemy;



import platformer.entity.entityliving.player.Player;
import platformer.utils.Assets;
import platformer.world.World;

public class EnemyMummy extends Enemy{
	
	
	public EnemyMummy(Player player, float x, float y, int monsterLevel) {
		super(player, x, y, monsterLevel);
		images = Assets.getImagesMummyLeft();
		healthMulti = 1.5f;
		speedMulti = 0.5f;
		damageMulti = 1f;
		attackSpeedMulti = 1f;
		setStats();
	}
	
	public void tick(){
		reset();
		super.tick();

		if(inputLeft)images = Assets.getImagesMummyLeft();
		if(inputRight)images = Assets.getImagesMummyRight();
	}
	
	@Override
	public boolean canBeKnockback() {
		return false;
	}

}
