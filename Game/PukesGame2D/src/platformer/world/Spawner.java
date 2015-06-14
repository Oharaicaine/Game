package platformer.world;

import java.util.Random;

import platformer.blocks.Block;
import platformer.entity.entityliving.enemy.Enemy;
import platformer.entity.entityliving.enemy.EnemyMummy;
import platformer.entity.entityliving.enemy.EnemyRhino;
import platformer.entity.entityliving.enemy.EnemyShaman;
import platformer.entity.entityliving.enemy.EnemySkeleton;
import platformer.entity.entityliving.player.Player;
import platformer.utils.Check;

public class Spawner {
	
	private Player player;
	private World world;
	private boolean populate;
	private int zoneLevel = 1;
	private int entityLivingLevel;
	private int levelChance = 0;
	private int spawnAmount = 5;
	private Random ran = new Random();

	public Spawner(World world, Player player) {
		this.world = world;
		this.player = player;
		populate = true;
		
	}
	public void tick() {
		if(populate){
			player.setX(World.spawnX);
			player.setY(World.spawnY);
			if(ran.nextInt(5)==0)levelChance++;
			
			entityLivingLevel = zoneLevel + levelChance;
			spawnAmount = zoneLevel;
			for(Block blocks : World.getTiles()){
				if(blocks.isSpawnBlock()){
					
					if(ran.nextInt(100-zoneLevel) == 0 )World.getEnemies().add(new EnemyMummy(player, (int)blocks.getBounds().x, (int)blocks.getBounds().y, entityLivingLevel));
					if(ran.nextInt(100-zoneLevel) == 0 )World.getEnemies().add(new EnemyRhino(player, (int)blocks.getBounds().x, (int)blocks.getBounds().y, entityLivingLevel));
					if(ran.nextInt(100-zoneLevel) == 0 )World.getEnemies().add(new EnemySkeleton(player, (int)blocks.getBounds().x, (int)blocks.getBounds().y,entityLivingLevel));
					if(ran.nextInt(200-zoneLevel) == 0 )World.getEnemies().add(new EnemyShaman(player, (int)blocks.getBounds().x, (int)blocks.getBounds().y,entityLivingLevel));
				}
			}
	
			for(Enemy em : World.getEnemies()){
				if(Check.DistanceFromPlayer(player, em) < 300){
					world.removeEntityLiving(em);
				}else{
					for(Block block : World.getTiles()){
						if(block.isSolid()){
							if(em.getBounds().intersects(block)){
								world.removeEntityLiving(em);
							}
						}	
					}
				}
			}	

			if(World.getEnemies().size() > (spawnAmount+5)){
				int num = World.getEnemies().size() - (spawnAmount+5);
				for(int i = 0; i < num; i ++){
					World.getEnemies().remove(ran.nextInt(spawnAmount+4));
				}
			}
			populate = false;
		}	
	}
	
	public int getZoneLevel() {
		return zoneLevel;
	}
	
	public void setPopulate(boolean populate) {
		this.populate = populate;
	}
	
	public void setZoneLevel(int zoneLevel) {
		this.zoneLevel = zoneLevel;
	}
	
	
	
}
