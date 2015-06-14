package platformer.blocks;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends Rectangle{
	
	private BufferedImage texture;
	
	private int x, y;
	
	public static final int TILEWIDTH = 64,//Main.getWidth()/30,
							TILEHEIGHT = 64;//Main.getHeight()/ 15;

	public Block(BufferedImage texture, int x, int y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		setBounds(x, y, TILEWIDTH, TILEHEIGHT);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		g.drawImage(texture, x, y,TILEWIDTH, TILEHEIGHT, null );
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public boolean isSpawnBlock(){
		return true;
	}

}
