package platformer.inventory;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import platformer.entity.items.Item;
import platformer.input.MouseManager;

public class SlotMouse extends Rectangle {

	private MouseManager mouse;
	private static Item mouseItem;
	private int size = 64;
	private static int mouseCurrentSlotSize = 0;
	private static int mouseMaxSlotSize = 1;
	/*
	* mouse, x, y, slotSize, slot_ID
	*/
	public SlotMouse(MouseManager mouse) {
		this.mouse = mouse;
		setBounds(200, 200, size, size);
	}
	
	public void tick(){
		setBounds((int)mouse.getMouse().getX(), (int)mouse.getMouse().getY(), size, size);
		if(mouseCurrentSlotSize <= 0)mouseItem = null;

	}
	public void render(Graphics2D g){
		//g.drawRect((int)getX(), (int)getY(), size, size);
		if(mouseItem != null){
			g.drawImage(mouseItem.getImage(), (int)getX(), (int)getY(), size, size, null);
		}
	}
	
	
	public static void setMouseItem(Item mouseItem) {
		SlotMouse.mouseItem = mouseItem;
		mouseCurrentSlotSize++;
	}
	
	public static Item getMouseItem() {	
		return mouseItem;
	}

	public static void removeMouseItem() {
		mouseCurrentSlotSize--;

	}
	
	public static boolean mouseHasItem(){
		if (mouseCurrentSlotSize > 0){
			return true;
		}else{
			return false;
		}
	}
	


}
