package platformer.utils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platformer.entity.entityliving.player.Player;
import platformer.entity.items.Item;
import platformer.inventory.Slot;

public class SaveGame {

	private Map<String, ArrayList<int[]>> map = new HashMap<String, ArrayList<int[]>>();
	
	private ArrayList<int[]> saveItems = new ArrayList<int[]>();
	
	public SaveGame(Player player) {
		
		for(Slot slots : player.getInventory().getSlots()){
			if(slots.hasItem()){
				int slotid = slots.getSlotId();
				int itemid = slots.getItem().getItemId();
				saveItems.add(new int[]{slotid, itemid});
			}
		}
		map.put("hello", saveItems);
		System.out.println(map);
		System.out.println(map.keySet());
			try {
				FileOutputStream file = new FileOutputStream("data.ser");
				ObjectOutputStream oos = new ObjectOutputStream(file);
				oos.writeObject(map);
				oos.close();
				file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	

}
