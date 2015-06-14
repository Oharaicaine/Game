package platformer.entity.items.skills;


import platformer.entity.entityliving.EntityLiving;
import platformer.skills.Skill;
import platformer.skills.SkillManaShield;
import platformer.utils.Assets;

public class ItemManaShield extends ItemSkill {
	
	public ItemManaShield(float x, float y) {
		super(x, y);
		itemId = 667;
		image = Assets.getItemManaShield();
		skill = new SkillManaShield(player);
	}
}
