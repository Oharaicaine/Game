package platformer.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import platformer.input.MouseManager;
import platformer.utils.Assets;

public class ToolTip {
	
	private String name, text, text2;
	private Font font = new Font("Arial", Font.BOLD, 22);

	public ToolTip(String name){
		this.name = name;
	}
	public ToolTip(String name, String text, String text2){
		this.name = name;
		this.text = text;
		this.text2 = text2;
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		//g.drawImage(Assets.getInventorybg(), MouseManager.mouse.x, MouseManager.mouse.y-20 , 140, 80, null);
		g.fillRect(MouseManager.mouse.x, MouseManager.mouse.y-20 , 150, 100);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawRect(MouseManager.mouse.x, MouseManager.mouse.y-20, 150, 100);
		if(name != null)g.drawString(name, MouseManager.mouse.x+10, MouseManager.mouse.y);
		if(text != null)g.drawString(text, MouseManager.mouse.x+10, MouseManager.mouse.y+30);
		if(text2 != null)g.drawString(text2, MouseManager.mouse.x+10, MouseManager.mouse.y+60);
		
	}
}
