package platformer.entity.items.weapons;

import platformer.entity.entityliving.EntityLiving;
import platformer.entity.items.Item;

public class ItemLongSword extends ItemWeapon {

	public ItemLongSword(EntityLiving dropFrom, int x, int y) {
		super(dropFrom, x, y, WeaponTypes.LONGSWORD);
		itemId = 102;
		itemName = "Longsword";
		weaponRange = 250;
		weaponSpeed = 2;
		weaponCritChance = 5.0f;
		weaponGenerator();
	}

}
