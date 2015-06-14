package platformer.entity.entityliving;

import java.awt.Color;
import java.util.Random;

import platformer.display.DamageText;
import platformer.entity.entityliving.player.Player;
import platformer.skills.SkillManaShield;
import platformer.states.GameStateManager;
import platformer.world.LootGenerator;
import platformer.world.World;

public class EntityHurt {
	
	private static Random random = new Random();
	private static Color color = Color.RED;
	private static int textSize = 26;
	private static boolean hasManaShield = false;
	private static boolean wasStunned = false;

	public static void Hurt(EntityLiving attacker, EntityLiving victim){
		color = Color.RED;
		hasManaShield = false;
		wasStunned = false;
		float minDmg = attacker.getMinDamage();
		float maxDmg = attacker.getMaxDamage();
		float amount = (random.nextFloat() * (maxDmg - minDmg)) + minDmg;
		float damageReduction = victim.getArmourValue();
		float dmg = amount - (amount/damageReduction);
		
		//	Crit check
		if(random.nextInt(100) < attacker.getCritChance()){
			dmg *= 1.5;
			textSize = 36;
			color = Color.YELLOW;
		}else{
			textSize = 26;
		}
		
		//	ManaShield check
		for(Buff buffs : victim.getBuffs()){
			if(buffs.getSkill() instanceof SkillManaShield){
				hasManaShield = true;
			}
		}
		
		//	Check for Stun
		if(!hasManaShield){
			if(dmg > victim.getHealth()/10){
				victim.setStunned(true);
			}
		}
		// Check for manaUsage on shield
		if(hasManaShield && victim.useMana(dmg*2)){
			color = Color.BLUE;
		}else{
			//	Damaging Victim
			victim.adjustHealth(-dmg);
			
			//	Stun Check
			if(dmg > victim.getMaxHealth()/10){
				victim.setStunned(true);
			}else{
				victim.setStunned(false);
			}
			
		}
		
		World.getDamagetexts().add(new DamageText(dmg, (int) victim.getCenterX(), (int) victim.getCenterY(), color, textSize));
		//	Death!!
		if(victim.getHealth() <= 0){
			if(attacker instanceof Player){
				((Player) attacker).getPlayerStats().addToPlayerXP(victim.getXpValue());
				LootGenerator.MobDrop(victim);
			}
			World.removeEntityLiving(victim);
		}
	}
	public static void heal(EntityLiving entity, float amount){
		entity.adjustHealth(amount) ;
		if(entity.getHealth() > entity.getMaxHealth())entity.setHealth(entity.getMaxHealth());
		World.getDamagetexts().add(new DamageText(amount, (int)entity.getCenterX(), (int)entity.getCenterY(), Color.GREEN, 26));

	}
	

}
