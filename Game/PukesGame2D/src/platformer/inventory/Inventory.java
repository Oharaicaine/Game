package platformer.inventory;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import platformer.entity.entityliving.player.Player;
import platformer.entity.items.Item;
import platformer.entity.items.skills.ItemSkill;
import platformer.input.KeyManager;
import platformer.input.MouseManager;
import platformer.inventory.charactorslots.MainHandSlot;
import platformer.inventory.charactorslots.OffHandSlot;
import platformer.main.Main;
import platformer.utils.Assets;

public class Inventory {
	
	private Player player;
	private MouseManager mouse;
	private KeyManager key;
	private int width, height;
	private int rows, columns;
	private ArrayList<Slot> slots = new ArrayList<Slot>();
	private ArrayList<SlotSkill> skillSlots = new ArrayList<SlotSkill>();
	private MainHandSlot mainHandSlot;
	private OffHandSlot offHandSlot;
	//private Slot armourChestSlot;
	private SlotMouse mouseSlot;
	private SlotSkill activeSkillSlot;
	private SlotPotion potionSlot;
	private SlotBin slotbin;
	private boolean showInventory = false;
	private Rectangle inventoryScreen;
	
	private ArrayList<Item> saveItems = new ArrayList<Item>();
	
	public Inventory(Player player,  MouseManager mouse, KeyManager key) {
		this.rows = 7;
		this.columns = 4;
		this.player = player;
		this.mouse = mouse;
		this.key = key;
		init();
	}
	
	private int idCounter = 0;
	
	private void init() {
		width = Main.getWidth();
		height = Main.getHeight();
		inventoryScreen = new Rectangle(Main.getWidth()-Main.getWidth()/3, 0, Main.getWidth()/3, Main.getHeight());
		//Inventory slots
		for(int y = 0; y < columns; y++){
			for(int x = 0; x < rows; x++){
				slots.add(new Slot(((x * 80)+ 55)+ width-(width/3), ((y * 80)) + height/2 + 100, 64, 64, 20 + idCounter));
				idCounter++;
			}
		}
		//skills
		for(int x = 0; x < 4; x++){
			skillSlots.add(new SlotSkill((x * 96)+ 100, height-(height/8), 64, 64, 1 + x));
		}
		//charactor slots
		potionSlot = new SlotPotion(500, height-(height/8), 64, 64);
		mainHandSlot = new MainHandSlot(width - 560, (height/2)-100, 100, 100);
		offHandSlot = new OffHandSlot(width - 200, (height/2)-100, 100, 100);
		slotbin = new SlotBin(width - 105, height - 100, 64, 64, 30);
		mouseSlot = new SlotMouse(mouse);
		activeSkillSlot = skillSlots.get(0);
	}

	public void tick(){
		
		if(showInventory){
			for(Slot slot : slots){
				slot.tick();
			}
			mouseSlot.tick();
			mainHandSlot.tick();
			offHandSlot.tick();
			slotbin.tick();
		}
		
		for(SlotSkill skillSlots : skillSlots){
			skillSlots.tick();
		}
		potionSlot.tick();
				
		
		if(key.one && !(skillSlots.get(0) == activeSkillSlot)){
			if(activeSkillSlot.hasItem())activeSkillSlot.getItem().getSkill().CancelSkill();
			activeSkillSlot = skillSlots.get(0);
		}
		if(key.two && !(skillSlots.get(1) == activeSkillSlot)){
			if(activeSkillSlot.hasItem())activeSkillSlot.getItem().getSkill().CancelSkill();
			activeSkillSlot = skillSlots.get(1);
		}
		if(key.three && !(skillSlots.get(2) == activeSkillSlot)){
			if(activeSkillSlot.hasItem())activeSkillSlot.getItem().getSkill().CancelSkill();
			activeSkillSlot = skillSlots.get(2);
		}
		if(key.four && !(skillSlots.get(3) == activeSkillSlot)){
			if(activeSkillSlot.hasItem())activeSkillSlot.getItem().getSkill().CancelSkill();
			activeSkillSlot = skillSlots.get(3);
		}
		
		
	}
	

	public void render(Graphics2D g){

		if(showInventory){
			
			g.drawImage(Assets.getInventorybg(),Main.getWidth()-Main.getWidth()/3, 0, Main.getWidth()/3, Main.getHeight(), null);
			g.drawImage(Assets.getPlayer(), (Main.getWidth()-Main.getWidth()/4)+20, Main.getHeight()/3, 230, 230, null);
			g.drawRect((Main.getWidth()-Main.getWidth()/3)+20, 100, 500, 200);
			for(Slot slot : slots){
				slot.render(g);
			}
			mainHandSlot.render(g);
			offHandSlot.render(g);
			mouseSlot.render(g);
			slotbin.render(g);
		}
		potionSlot.render(g);
		
		for(SlotSkill skillSlots : skillSlots){
			skillSlots.render(g);
		}
		
		 //highlighting the active skillslot
		if(activeSkillSlot != null)
		g.drawRect((int)activeSkillSlot.getX()-10, (int)activeSkillSlot.getY()-10, 64+20, 64+20);

	}
	//TODO fix Item stacking
	public void addItemToNextEmptySlot(Item item){
		/*
		 * Used for picking Items up
		 * Checks for same item stack before making a new stack
		 */
		for(Slot slot : slots){
			if(slot.hasItem()){
				if(slot.getItem().equals(item)){
					if(slot.getCurrentSlotStackSize() < slot.getMaxSlotStacksize()){
						slot.addItem(item);
						return;
					}
				}
			}
		}
		
		for(Slot slot2 : slots){
			if(slot2.getItem() == null){
				slot2.addItem(item);
				slot2.setMaxSlotStacksize(item.getMaxStackSize());
				return;
			}
		}
	}
	
	public boolean showInventory(){
		return showInventory;
	}
	public boolean isFull(){
		for(Slot slot : slots){
			if(!slot.hasItem()){
				return false;
			}
		}
		return true;
	}
	public void setShowInventory(boolean showInventory) {
		this.showInventory = showInventory;
	}
	public ArrayList<Slot> getSlots() {
		return slots;
	}
	public SlotSkill getActiveSkillSlot() {
		return activeSkillSlot;
	}
	public ArrayList<SlotSkill> getSkillSlots() {
		return skillSlots;
	}
	
	public void addItemSkill(ItemSkill skill, int slot){
		skillSlots.get(slot).addItemToSlot(skill);
	}
	public Rectangle getInventoryScreen() {
		return inventoryScreen;
	}

	public MainHandSlot getMainHandSlot() {
		return mainHandSlot;	
	}
	public OffHandSlot getWeaponOffSlot() {
		return offHandSlot;
	}

	public SlotPotion getPotionSlot() {
		return potionSlot;	
	}
	
	public void addToSaveItems(Item saveItems) {
		this.saveItems.add(saveItems);
	}
	
}
