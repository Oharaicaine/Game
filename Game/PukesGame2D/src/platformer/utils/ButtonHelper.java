package platformer.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import platformer.input.MouseManager;
import platformer.states.GameStateManager;

public class ButtonHelper extends Rectangle{

	private String text;
	private int x, y, width, height;
	private MouseManager mouseManager;
	private Font font;
	
	public ButtonHelper(String text, int x, int y, int width, int height, MouseManager mouseManager) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.mouseManager = mouseManager;
		setBounds(x, y, width, height);
		font = new Font("Arial", Font.ROMAN_BASELINE, 36);
		
	}
	
	public void tick(){
		setBounds(x, y, width, height);
		if(mouseManager.getMouse() != null){
			if(getText().equals("Start")){
				if(getBounds().contains(mouseManager.getMouse()) && mouseManager.getMouseOnePressed()){
					GameStateManager.setState(GameStateManager.GAMESTATE);
				}
			}
			if(getText().equals("Quit")){
				if(getBounds().contains(mouseManager.getMouse()) && mouseManager.getMouseOnePressed()){
					System.exit(0);
				}
			}
			if(getText().equals("Load")){
				if(getBounds().contains(mouseManager.getMouse()) && mouseManager.getMouseOnePressed()){
					GameStateManager.setState(GameStateManager.LOADSTATE);
				}
			}
		}	
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawImage(Assets.getMenuButton(), x, y, width, height, null);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(text, x + width/2 - font.getSize(), (y + height / 2)+5);
	}
	
	public String getText() {
		return text;
	}

}
