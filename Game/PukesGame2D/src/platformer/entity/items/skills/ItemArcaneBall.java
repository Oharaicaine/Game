package platformer.entity.items.skills;

import java.util.ArrayList;
import java.util.Arrays;

import platformer.entity.entityliving.EntityLiving;
import platformer.entity.items.Item.ItemTypes;
import platformer.skills.Skill;
import platformer.skills.SkillArcaneBall;
import platformer.states.GameState;
import platformer.utils.Assets;

@SuppressWarnings("serial")
public class ItemArcaneBall extends ItemSkill {

	public ItemArcaneBall(float x, float y) {
		super(x, y);
		itemId = 666;
		image = Assets.getItemMagicBall();
		skill = new SkillArcaneBall(player);
	}

}
