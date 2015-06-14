package platformer.entity.items;

import java.util.ArrayList;
import java.util.Arrays;

import platformer.display.ToolTip;
import platformer.entity.entityliving.EntityHurt;
import platformer.entity.entityliving.player.Player;
import platformer.entity.items.Item.ItemTypes;
import platformer.states.GameState;
import platformer.utils.Assets;


@SuppressWarnings("serial")
public class ItemHealthPotion extends Item{

	public ItemHealthPotion(float x, float y) {
		super(x, y);
		itemId = 10;
		image = Assets.getItemHealthPotion();
		itemToolTip = new ToolTip("heals");
		itemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.POTION));
	}
	
	@Override
	public void useItem() {
		Player player = GameState.getPlayer();
		EntityHurt.heal(player, (float)(player.getMaxHealth()*0.3));
		getSlot(this).removeItemFromSlot();
	}
	
	@Override
	public boolean hasUse() {	
		return true;
	}

}
