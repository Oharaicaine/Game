package platformer.entity.entityliving.player;

import java.awt.Graphics2D;

import platformer.entity.entityliving.EntityLiving;
import platformer.skills.Skill;
import platformer.skills.SkillArcaneBall;
import platformer.skills.SkillManaShield;


public class PlayerSkills {

	//private Skill activeSkill;
	private Player player;
	 
	public PlayerSkills(EntityLiving player) {
		this.player = (Player)player;
		
	}
	public void tick(){
		//setting active skill
		if(player.getActiveSkill() == null && player.getInventory().getActiveSkillSlot().getItem() !=null){
			player.setActiveSkill(player.getInventory().getActiveSkillSlot().getItem().getSkill());
		}
		
		//Checking if Active skill has changed
		if(player.getInventory().getActiveSkillSlot().getItem() !=null){
			if(player.getInventory().getActiveSkillSlot().getItem().getSkill() != player.getActiveSkill()){
				player.setActiveSkill(player.getInventory().getActiveSkillSlot().getItem().getSkill());
			}
		}
		//if(activeSkill != null){
		//	activeSkill.tick();
		//}
		//setting activeskill to null if it's empty
		if(player.getInventory().getActiveSkillSlot().getItem() == null){
			player.setActiveSkill(null);
		}
	}
}
