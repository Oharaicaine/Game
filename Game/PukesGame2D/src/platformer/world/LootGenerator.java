package platformer.world;

import java.util.Random;

import platformer.entity.entityliving.EntityLiving;
import platformer.entity.items.ItemCoin;
import platformer.entity.items.ItemHealthPotion;
import platformer.entity.items.skills.ItemManaShield;
import platformer.entity.items.weapons.ItemLongSword;
import platformer.entity.items.weapons.ItemShortSword;
import platformer.entity.items.weapons.ItemWeapon;
import platformer.states.GameState;

public class LootGenerator {
	
	private static Random random = new Random();

	public static void MobDrop(EntityLiving victim) {
		int num = random.nextInt(4); // 0-3
		switch(num){
		case 0: World.getItems().add(new ItemCoin(victim, (int)victim.getX(), (int)victim.getY()));
			break;
		case 1: World.getItems().add(new ItemManaShield((int)victim.getX(), (int)victim.getY()));
			break;
		case 2: World.getItems().add(new ItemHealthPotion((int)victim.getX(), (int)victim.getY()));
			break;
		case 3: World.getItems().add(new ItemShortSword(victim, (int)victim.getX(), (int)victim.getY()));
		}
	}
}
