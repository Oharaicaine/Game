package platformer.utils;

import java.awt.Point;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import platformer.blocks.Block;
import platformer.entity.entityliving.enemy.Enemy;
import platformer.entity.entityliving.player.Player;
import platformer.world.World;

public class Check {
	
	public static boolean CollisionBlock(Point p1, Point p2) {
		for(Block block : World.getTiles()) {
			if(block.isSolid()) {
				if(block.contains(p1) || block.contains(p2)) {
					return true;
				}
			}
		}
		
		return false;
	}
	

	public static boolean CollisionEnemy(Point p1, Point p2) {
		for(Enemy em : World.getEnemies()){
			if(em.contains(p1) || em.contains(p2)){
				return true;
			}
		}
		return false;
	}
	
	public static double DistanceFromPlayer(Player player, Enemy enemy){
		double disX = player.getCenterX() - enemy.getCenterX();
		double disY = player.getCenterY() - enemy.getCenterY();
		
		double distance = Math.sqrt((disX)*(disX) + (disY)*(disY));
		return distance;
	}
	
	public static ArrayList<Enemy> AttackEnemy(Arc2D.Double arc){
		ArrayList<Enemy> enemy = new ArrayList<Enemy>();
		for(Enemy em : World.getEnemies()){
			if(arc.intersects(em.getBounds())){
				enemy.add(em);
			}
		}
		return enemy;
	}
	
	

}
