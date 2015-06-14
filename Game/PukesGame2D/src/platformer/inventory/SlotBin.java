package platformer.inventory;

import platformer.entity.entityliving.player.PlayerStats;

public class SlotBin extends Slot{

	public SlotBin(int x, int y, int width, int height, int slotID) {
		super(x, y, width, height, slotID);
	}
	
	@Override
	public void tick(){
		if(hasItem()){
			removeItemFromSlot();
			PlayerStats.addCoins(1);
		}
		super.tick();
	}

}
