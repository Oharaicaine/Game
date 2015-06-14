package platformer.blocks;

import platformer.utils.Assets;

public class BlockBrick extends Block{

	public BlockBrick(int x, int y) {
		super(Assets.getBrick(),x , y);
	}
	
	public boolean isSolid(){
		return true;
	}
	
	public boolean isSpawnBlock(){
		return false;
	}


}
