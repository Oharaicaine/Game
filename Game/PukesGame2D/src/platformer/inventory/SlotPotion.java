package platformer.inventory;

import java.util.ArrayList;
import java.util.Arrays;

import platformer.entity.items.Item.ItemTypes;

public class SlotPotion extends Slot{

	public SlotPotion(int x, int y, int width, int height) {
		super(x, y, width, height, 5);
		slotItemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.POTION));
	}

}
