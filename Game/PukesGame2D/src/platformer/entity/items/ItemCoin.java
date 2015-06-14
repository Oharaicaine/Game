package platformer.entity.items;

import platformer.entity.entityliving.EntityLiving;
import platformer.utils.Assets;

public class ItemCoin extends Item{

	public ItemCoin(EntityLiving dropFrom, int x, int y) {
		super(dropFrom, x, y);
		image = Assets.getItemCoin();
		itemId = 1; //1-9
	}
}
