package platformer.entity.items.weapons;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import platformer.display.ToolTip;
import platformer.entity.entityliving.EntityLiving;
import platformer.entity.items.Item;
import platformer.utils.Assets;

public class ItemWeapon extends Item{

	protected String itemName;
	
	protected float weaponMinDamage;
	protected float weaponMaxDamage;
	protected float weaponCritChance;
	protected float weaponRange;
	protected float weaponSpeed;
	protected Color color;
	
	protected float dmgMin;
	protected float dmgMax;
	
	protected int itemLevel;
	private static Random random = new Random();

	protected float[] weaponStats = new float[5];
	
	
	public ItemWeapon(EntityLiving dropFrom , int x, int y, WeaponTypes weaponType) {
		super(dropFrom, x, y);
		itemId = 100; //100-199
		itemLevel = dropFrom.getLevel();
		image = Assets.getItemLongsword();
		itemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.MAINHAND));
	}
	protected void weaponGenerator() {
		weaponMinDamage = (1+itemLevel)+(random.nextFloat()*itemLevel);
		weaponMaxDamage = (5+itemLevel)+(random.nextFloat()*itemLevel);
		color = Color.RED;
		itemToolTip = new ToolTip(itemName, "dmg= "+Math.round(weaponMinDamage)+"-"+Math.round(weaponMaxDamage),"spd= "+Math.round(weaponSpeed));
		setStats();
	}
	private void setStats() {
		weaponStats[0] = weaponMinDamage;
		weaponStats[1] = weaponMaxDamage;
		weaponStats[2] = weaponSpeed;
		weaponStats[3] = weaponRange;
		weaponStats[4] = weaponCritChance;
	}
	
	public float[] getWeaponStats() {
		return weaponStats;
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	public enum WeaponTypes {
		SHORTSWORD,
		LONGSWORD,
	}

}
