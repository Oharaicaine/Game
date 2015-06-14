package platformer.entity.items.weapons;

import platformer.entity.entityliving.EntityLiving;

public class ItemShortSword extends ItemWeapon{

	public ItemShortSword(EntityLiving dropFrom, int x, int y) {
		super(dropFrom, x, y, WeaponTypes.SHORTSWORD);
		itemId = 101;
		itemName = "Shortsword";
		weaponRange = 200;
		weaponSpeed = 3;
		weaponCritChance = 6.0f;
		weaponGenerator();	
	}

}
