package platformer.inventory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import platformer.display.ToolTip;
import platformer.entity.items.Item;
import platformer.entity.items.Item.ItemTypes;
import platformer.input.MouseManager;
import platformer.states.GameState;
import platformer.utils.Assets;

public class Slot extends Rectangle{
	
	protected BufferedImage image;
	protected Item item;	
	protected int currentSlotStackSize = 0;
	protected int maxSlotStacksize = 2;
	protected int x, y;
	protected int width, height;
	protected ArrayList<ItemTypes> slotItemTypes;
	private boolean canSwap = true;
	private int canSwapTimer = 0;
	private boolean canUse = true;
	private int canUseTimer = 0;
	private boolean renderToolTip = false;
	private ToolTip tooltip;
	private int SlotId;
	
	public Slot(int x, int y, int width, int height, int SLOTID) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.SlotId = SLOTID;
		setBounds(x, y, width, height);
		slotItemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.ITEM, ItemTypes.SKILL, ItemTypes.MAINHAND, ItemTypes.OFFHAND, ItemTypes.POTION));
	}

	public void tick(){
		timers();	
		if(currentSlotStackSize == 0)item = null;
		
		//picking up and putting down items
		if(GameState.getPlayer().getInventory().showInventory()){
			if(getBounds().contains(MouseManager.mouse) && MouseManager.mouseOnePressed && canSwap){
				canSwap = false;
				if(hasItem() && !SlotMouse.mouseHasItem()){
					SlotMouse.setMouseItem(item);
					removeItemFromSlot();
					
				}else if(!hasItem() && SlotMouse.mouseHasItem()){
					
					if(getSlotTypes().containsAll(SlotMouse.getMouseItem().getItemTypes())){
						addItem(SlotMouse.getMouseItem());
						SlotMouse.removeMouseItem();
					}
				}
			}
			//Using an Item
			if(getBounds().contains(MouseManager.mouse) && MouseManager.mouseTwoPressed && canUse){
				if(hasItem()){
					if(getItem().hasUse()){
						getItem().useItem();
						currentSlotStackSize--;
					}
				}
			}	
			//ToolTip rendering
			if(getBounds().contains(MouseManager.mouse) && hasItem()){
				tooltip = getItem().getItemToolTip();
				renderToolTip = true;	
			}else{
				renderToolTip = false;
			}
		}
	}

	public ArrayList<ItemTypes> getSlotTypes() {	
		return slotItemTypes;
	}

	public void render(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fill(getBounds());
		g.drawImage(Assets.getSlotImage(), (int)getX(), (int)getY(), width, height, null);
		
		if(item !=null){
			g.drawImage(item.getImage(),(int)getX(), (int)getY(), width, height, null);
			g.drawString(""+currentSlotStackSize, (int)getX() + width, (int)getY()-5);
		}
		//g.drawString(""+slotId, (int)getX(), (int)getY());
		
	}
	
	private void timers() {
		if(!canUse){
			canUseTimer ++;
			if(canUseTimer > 10){
				canUse = true;
				canUseTimer = 0;
			}
		}
				
		if(!canSwap){
			canSwapTimer ++;
			if(canSwapTimer > 10){
				canSwap = true;
				canSwapTimer = 0;
			}
		}
		
	}
	
	public Item getItem() {
		return item;
	}
	public void addItem(Item item) {
		this.item = item;
		currentSlotStackSize++;
	}
	
	public int getCurrentSlotStackSize() {
		return currentSlotStackSize;
	}
	
	public void setCurrentSlotStackSize(int currentSlotStackSize) {
		this.currentSlotStackSize = currentSlotStackSize;
	}
	
	public void setMaxSlotStacksize(int maxSlotStacksize) {
		this.maxSlotStacksize = maxSlotStacksize;
	}

	public int getMaxSlotStacksize() {
		return maxSlotStacksize;
	}

	public boolean hasItem() {	
		if (currentSlotStackSize > 0){
			return true;
		}else{
			return false;
		}
	}

	public void addToCurrentSlot() {
		this.currentSlotStackSize ++;
		
	}
	public void addItemToSlot(Item item){
		this.item = item;
		currentSlotStackSize++;
	}

	public void removeItemFromSlot() {
		currentSlotStackSize--;
		if(currentSlotStackSize <= 0){
			item = null;
		}
	}	

	public boolean getRenderToolTip() {
		return renderToolTip;
	}
	public boolean isSkillSlot() {
		if(this instanceof SlotSkill){
			return true;
		}else{return false;}
	}

	public ToolTip getToolTip() {
		return tooltip;	
	}
	public int getSlotId() {
		return SlotId;
	}

}
