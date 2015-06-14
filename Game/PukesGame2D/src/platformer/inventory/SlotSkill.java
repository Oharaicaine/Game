package platformer.inventory;

import java.util.ArrayList;
import java.util.Arrays;

import platformer.entity.items.Item.ItemTypes;

public class SlotSkill extends Slot{

	public SlotSkill(int x, int y, int size, int Id, int SLOTID) {
		super(x, y, size, Id, SLOTID);
		slotItemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.SKILL));
	}

}
