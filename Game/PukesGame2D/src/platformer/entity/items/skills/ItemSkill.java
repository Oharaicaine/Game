package platformer.entity.items.skills;

import java.util.ArrayList;
import java.util.Arrays;

import platformer.entity.entityliving.EntityLiving;
import platformer.entity.entityliving.player.Player;
import platformer.entity.items.Item;
import platformer.skills.Skill;
import platformer.states.GameState;

public class ItemSkill extends Item{
	
	protected Skill skill;
	protected Player player;

	public ItemSkill(float x, float y) {
		super(x, y);
		player = GameState.getPlayer();
		itemTypes = new ArrayList<ItemTypes>(Arrays.asList(ItemTypes.SKILL));
	}
	
	@Override
	public Skill getSkill(){
		return skill;
	}

}
