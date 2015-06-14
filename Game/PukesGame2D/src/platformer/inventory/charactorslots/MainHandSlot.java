package platformer.inventory.charactorslots;

import java.util.ArrayList;
import java.util.Arrays;

import platformer.entity.entityliving.player.Player;
import platformer.entity.entityliving.player.PlayerStats;
import platformer.entity.items.Item.ItemTypes;
import platformer.entity.items.weapons.ItemWeapon;
import platformer.inventory.Slot;
import platformer.states.GameState;


public class MainHandSlot extends Slot{
	
	private ItemWeapon currentItem;
	private Player player;

	public MainHandSlot(int x, int y, int width, int height) {
		super(x, y, width, height, 10);
		slotItemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.MAINHAND));
		player = GameState.getPlayer();
	}
	
	public void tick(){
		/*
		if(GameState.getPlayer().getInventory().showInventory()){
			if(hasItem()){
				if(currentItem == null){
					currentItem = (ItemWeapon)getItem();
				}else if(getItem() != currentItem){
					GameState.getPlayer().getPlayerStats().updateMainHand(true);
					currentItem = (ItemWeapon)getItem();	
				}
			}
			
		}	
		*/
		super.tick();
	}

}
