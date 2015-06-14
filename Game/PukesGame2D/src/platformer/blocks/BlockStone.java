package platformer.blocks;

import platformer.utils.Assets;


public class BlockStone extends Block{

	
	public BlockStone(int x,int y) {
		super(Assets.getStone(),x , y);

	}
	
	
	@Override
	public boolean isSpawnBlock() {
		
		return true;
	}

}
