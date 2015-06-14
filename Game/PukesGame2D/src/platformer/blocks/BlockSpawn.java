package platformer.blocks;


import platformer.utils.Assets;

public class BlockSpawn extends Block {

	public BlockSpawn(int x, int y) {
		super(Assets.getItemCoin(), x, y);
		
	}
	
	@Override
	public boolean isSpawnBlock(){
		return false;
	}

}
