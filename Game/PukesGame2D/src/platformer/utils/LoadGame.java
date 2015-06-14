package platformer.utils;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import platformer.states.GameStateManager;

public class LoadGame {

	@SuppressWarnings("unchecked")
	public LoadGame() {
		HashMap<String, ArrayList<int[]>> map = null;//new HashMap<String, ArrayList<int[]>>();
		
		try {
			  FileInputStream fis = new FileInputStream("data.ser");
		         ObjectInputStream ois = new ObjectInputStream(fis);
		         map = (HashMap<String, ArrayList<int[]>>) ois.readObject();
		         System.out.println(map);
		         ois.close();
		         fis.close();
		} catch (Exception e) {
			
		}
		ArrayList<int[]> loadedItems = map.get("hello");

		int[] num = new int[loadedItems.get(1).length];
		
		num = loadedItems.get(1);

		System.out.println(num[1]);
		
		
		//System.out.println(map.get("hello"));
		//System.out.println(loadedItems);
		GameStateManager.setState(GameStateManager.GAMESTATE);
	}

}
