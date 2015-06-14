package platformer.entity.entityliving.player;

import java.awt.Graphics2D;

import platformer.entity.entityliving.EntityHurt;
import platformer.entity.items.weapons.ItemWeapon;
import platformer.world.World;

public class PlayerStats {

	private Player player;	
	private float manaRegen;
	
	private static int coins = 0;
	
	private int playerXp = 0;
	private double xpRequired = 200;	
	
	//private static 
	
	
	public PlayerStats(Player player) {
		this.player = player;
		manaRegen = 0.1f;
		player.setArmourValue(10f);
		player.setCastSpeed(1f);
		player.setMoveSpeed(2.0f);
		player.setHealth(50);
		player.setSpeed(1.0f);
		player.setMaxHealth(player.getHealth());
		player.setMana(100);
		player.setMaxMana(player.getMana());
		player.setMinDamage(5f);
		player.setMaxDamage(10f);
		player.setCritChance(5.0f);
		player.setAttackRange(250f);
		player.setAttackSpeed(2f);
		player.setLevel(1);
	}
	
	public void tick(){
		
		if(player.getInventory().showInventory()){
			if(player.getInventory().getMainHandSlot().hasItem()){
				ItemWeapon weapon = (ItemWeapon) player.getInventory().getMainHandSlot().getItem();
				float[] mainHandWeaponStats = weapon.getWeaponStats();
				player.setMinDamage(mainHandWeaponStats[0]);
				player.setMaxDamage(mainHandWeaponStats[1]);
				player.setAttackSpeed(mainHandWeaponStats[2]);
				player.setAttackRange(mainHandWeaponStats[3]);
				player.setCritChance(mainHandWeaponStats[4]);
			}
		}
		
		
		if(player.getMana() < player.getMaxMana())player.addMana(manaRegen);
		if(playerXp > xpRequired){
			
			//Level alteration done here
			player.setMaxHealth(player.getMaxHealth()+10);
			EntityHurt.heal(player, player.getMaxHealth()-player.getHealth());
			
			player.setLevel(player.getLevel() + 1);
			xpRequired *= 2.3;
		}
		
	}
	
	public void render(Graphics2D g){

	}
	
	public void addToPlayerXP(int xp){
		this.playerXp += xp;
	}
	
	public int getCoins() {
		return coins;
	}
	public static void addCoins(int coin) {
		coins += coin;
	}
	public int getPlayerXp() {
		return playerXp;
	}
}
