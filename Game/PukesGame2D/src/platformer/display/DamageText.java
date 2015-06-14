package platformer.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import platformer.world.World;

public class DamageText {
	
	private int amount;
	private int textLifeTimer = 0;
	private int textSize;
	private float x, y;
	private Color color = Color.RED;
	
	public DamageText(float amount, float x, float y) {
		this.amount = (int)amount;
		this.x = x;
		this.y = y;
		color = Color.RED;
		textSize = 26;
	}
	public DamageText(float amount, float x, float y, Color color, int textSize) {
		this.amount = (int)amount;
		this.x = x;
		this.y = y;
		this.color = color;
		this.textSize = textSize;
	}
	
	
	public void tick(){
		textLifeTimer++;
		y-=2;
		if(textLifeTimer > 30){
			World.getDamagetexts().remove(this);
		}
	}
	
	public void render(Graphics2D g){
		g.setFont(new Font("Arial", Font.BOLD, textSize));
		g.setColor(color);
		g.drawString(Integer.toString(amount), (int)x, (int)y - 64);
		g.setColor(Color.WHITE);
	}

}
