package platformer.display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import platformer.entity.entityliving.player.Player;
import platformer.entity.items.Item;
import platformer.entity.items.weapons.ItemWeapon;
import platformer.input.KeyManager;
import platformer.input.MouseManager;
import platformer.inventory.Slot;
import platformer.main.Main;
import platformer.world.World;

public class HUDManager {
	
	private Player player;
	private static ToolTip toolTip;
	
	public HUDManager(Player player) {
		this.player = player;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){

		if(!World.getDamagetexts().isEmpty()){
			for(DamageText text : World.getDamagetexts()){
				text.render(g);
			}
		}
		if(player.getInventory().showInventory()){
			for(Slot slots : player.getInventory().getSlots()){
				if (slots.getRenderToolTip()){
					slots.getItem().getItemToolTip().render(g);
				}
			}
			for(Slot slot : player.getInventory().getSkillSlots()){
				if(slot.getRenderToolTip()){
					slot.getItem().getItemToolTip().render(g);
				}
			}
			if(player.getInventory().getMainHandSlot().getRenderToolTip()){
				player.getInventory().getMainHandSlot().getItem().getItemToolTip().render(g);
			}
			if(player.getInventory().getPotionSlot().getRenderToolTip()){
				player.getInventory().getPotionSlot().getItem().getItemToolTip().render(g);
			}
		}
		
		for(Item items : World.getItems()){
			if(items.getBounds().contains(MouseManager.mouse)){
				items.getItemToolTip().render(g);
			}
		}
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial", Font.BOLD, 36));
		g.drawString("Coins: " + player.getPlayerStats().getCoins(), 70, 70);
		//g.drawString("Level: " + player.getPlayerStats().getPlayerLevel(), 300, 70);
		g.drawString("XP: " + player.getPlayerStats().getPlayerXp(), 500, 70);
		g.setFont(new Font("Arial", Font.PLAIN, 12));

		// Dimming the whole screen
		g.setColor(Color.BLACK);
		
		g.setComposite(AlphaComposite.SrcOver.derive(0.2f));
		g.fillRect(0, 0, Main.getWidth(), Main.getHeight());
		
		g.setComposite(AlphaComposite.SrcOver);
		
		g.setColor(Color.WHITE);
	
		/*
		Graphics2D g2 = (Graphics2D) lightMap.getGraphics();
		
		g2.setColor(new Color(0,0,0,255));
		g2.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
		g2.setComposite(AlphaComposite.DstOut);
		
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(200, 200, 200, 200);
		g2.dispose();
		*/
		
	}
	
	public static void setToolTip(ToolTip toolTip) {
		HUDManager.toolTip = toolTip;
	}

	public static ToolTip getToolTip() {
		return toolTip;	
	}


	
	

}
