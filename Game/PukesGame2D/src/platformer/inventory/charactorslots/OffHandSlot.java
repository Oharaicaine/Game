package platformer.inventory.charactorslots;

import java.util.ArrayList;
import java.util.Arrays;

import platformer.entity.items.Item.ItemTypes;
import platformer.inventory.Slot;


public class OffHandSlot extends Slot{

	public OffHandSlot(int x, int y, int width, int height) {
		super(x, y, width, height, 11);
		slotItemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.OFFHAND));
	}

}
