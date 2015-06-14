package platformer.entity.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import platformer.display.ToolTip;
import platformer.entity.entityliving.EntityLiving;
import platformer.entity.entityliving.player.Player;
import platformer.entity.entityliving.player.PlayerStats;
import platformer.entity.items.skills.ItemSkill;
import platformer.entity.items.weapons.ItemWeapon;
import platformer.input.KeyManager;
import platformer.input.MouseManager;
import platformer.inventory.Slot;
import platformer.skills.Skill;
import platformer.states.GameState;
import platformer.world.World;

public class Item extends Rectangle{

	protected BufferedImage image;
	
	protected float x, y;
	protected int itemSize = 48;
	protected int maxStackSize = 1;
	protected Skill skill;
	protected ToolTip itemToolTip;
	protected ArrayList<ItemTypes> itemTypes;
	protected int itemId = 0;
	
	public Item(EntityLiving dropFrom, float x, float y) {
		this.x = x;
		this.y = y;
		setBounds((int)x, (int)y, itemSize, itemSize);
		itemToolTip = new ToolTip("name");
		itemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.ITEM));
	}
	
	public Item(float x, float y) {
		this.x = x;
		this.y = y;
		setBounds((int)x, (int)y, itemSize, itemSize);
		itemToolTip = new ToolTip("name");
	}
	
	public void tick(){
		setBounds((int)x, (int)y, itemSize, itemSize);
		if(GameState.getPlayer().intersects(getBounds())){
			if(this.getItemId() == 1){ // Itemcoins
				PlayerStats.addCoins(1);
				World.getItems().remove(this);
			}else if(this.getItemId() == 10){ // Item health potion
				if(GameState.getPlayer().getInventory().getPotionSlot().getCurrentSlotStackSize() < 8){
					GameState.getPlayer().getInventory().getPotionSlot().addItemToSlot(this);
					World.getItems().remove(this);
				}
			}
		}
		if(getBounds().contains(MouseManager.mouse) && KeyManager.key_E){
			if(!GameState.getPlayer().getInventory().isFull()){
				GameState.getPlayer().getInventory().addItemToNextEmptySlot(this);
				
				World.getItems().remove(this);
			}
		}
	}
	public void render(Graphics2D g){
		g.drawImage(image, (int)x, (int)y, itemSize, itemSize, null);
	}
	
	public Slot getSlot(Item item){
		for(Slot slot : GameState.getPlayer().getInventory().getSlots()){
			if(slot.hasItem()){
				if(slot.getItem() == item)return slot;
			}
		}
		
		if(GameState.getPlayer().getInventory().getPotionSlot().getItem() == item){
			return GameState.getPlayer().getInventory().getPotionSlot();
		}
		
		return null;
	}
	
	public enum ItemTypes{
		ITEM, SKILL, MAINHAND, OFFHAND, USEABLE, POTION;
	}
	
	
	public void useItem(){
		
	}
	public int getItemId() {
		return itemId;
	}	
	public static Item addItem(EntityLiving dropFrom, float x, float y){
		return new Item(dropFrom, x, y);
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public int getMaxStackSize() {
		return maxStackSize;
	}

	public Skill getSkill() {
		return skill;
	}
	
	public boolean hasUse(){
		return false;
	}

	public ToolTip getItemToolTip() {
		return itemToolTip;
	}

	public ArrayList<ItemTypes> getItemTypes() {		
		return itemTypes;
	}

	public Color getColor() {
		return null;
	}
}
