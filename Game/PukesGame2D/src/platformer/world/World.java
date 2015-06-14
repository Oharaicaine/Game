package platformer.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import platformer.blocks.Block;
import platformer.blocks.BlockBrick;
import platformer.blocks.BlockSpawn;
import platformer.blocks.BlockStone;
import platformer.display.DamageText;
import platformer.entity.Projectile;
import platformer.entity.entityliving.EntityLiving;
import platformer.entity.entityliving.enemy.Enemy;
import platformer.entity.entityliving.player.Player;
import platformer.entity.items.Item;
import platformer.states.GameState;
import platformer.states.GameStateManager;
import platformer.utils.Assets;

public class World {

	private GameState gameState;
	private static BufferedImage map;
	private static BufferedImage subMap;
	
	public static boolean moveMap = false;
	public static int spawnX = 100, spawnY = 100;
	public static int exitX, exitY;
	private static boolean spawnSet = false;
	
	private int mapX = 0;
	private int mapY = 0;
	
	private static CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();
	private static CopyOnWriteArrayList<Block> tiles = new CopyOnWriteArrayList<Block>();
	private static CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<Item>();
	private static CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<Projectile>();
	private static CopyOnWriteArrayList<DamageText> damageTexts = new CopyOnWriteArrayList<DamageText>();
	
	public World(GameState gameState) {
		this.gameState = gameState;
		map = Assets.getMap();
		loadWorld(0, 0);
	}
	
	public void tick(){
		subMapLoader();
		
		for(Block block : tiles) {
			block.tick();
		}
		if(!items.isEmpty()){
			for(Item item : items){
				item.tick();
			}
		}
		if(!enemies.isEmpty()){
			for(Enemy em : enemies){
				em.tick();
			}
		}
		if(!damageTexts.isEmpty()){
			for(DamageText text : damageTexts){
				text.tick();
			}
		}
		if(!projectiles.isEmpty()){
			for(Projectile proj : projectiles){
				proj.tick();
			}
		}
		
		
	}
	

	public void render(Graphics2D g){

		for(Block block : tiles) {
			block.render(g);
		}
		if(!items.isEmpty()){
			for(Item item : items){
				item.render(g);
			}
		}
		if(!enemies.isEmpty()){
			for(Enemy em : enemies){
				em.render(g);
			}
		}	
		
		// Moved to HUD
		//if(!damageTexts.isEmpty()){
		//	for(DamageText text : damageTexts){
		//		text.render(g);
		//	}
		//}
		if(!projectiles.isEmpty()){
			for(Projectile proj : projectiles){
				proj.render(g);
			}
		}
		
	}
	
	//Total maps 66
	private int worldWidth = 30;
	private int worldHeight = 17;
	//private final int HUDoffset = Main.getHeight()/8-15;
	
	public void loadWorld(int xMapOffset, int yMapOffset) {
		subMap = map.getSubimage(0 + xMapOffset, 0 + yMapOffset, worldWidth, worldHeight);
		clearArrays();
		for(int x = 0; x < worldWidth; x++){
			for(int y = 0; y < worldHeight; y++){
				int col = subMap.getRGB(x, y);
				switch(col & 0xFFFFFF){
					case 0x808080:
						tiles.add(new BlockStone(x * Block.TILEWIDTH, y * Block.TILEHEIGHT));
						break;
					case 0x404040:
						tiles.add(new BlockBrick(x * Block.TILEWIDTH, y * Block.TILEHEIGHT));
						break;
					case 0x202020:
						if(!spawnSet){
							tiles.add(new BlockSpawn(x * Block.TILEWIDTH, y * Block.TILEHEIGHT));
							spawnX = x * Block.TILEWIDTH;
							spawnY = y * Block.TILEHEIGHT;
							spawnSet = true;
						}else if(spawnSet){
							tiles.add(new BlockSpawn(x * Block.TILEWIDTH, y * Block.TILEHEIGHT));
							exitX = x * Block.TILEWIDTH;
							exitY = y * Block.TILEHEIGHT;
							spawnSet = false;
						}
						break;
				 }
			}
		}	
	}

	private void subMapLoader() {
		if(moveMap){
			Random random = new Random();
			loadWorld(random.nextInt(5)*worldWidth, random.nextInt(8)*worldHeight);
			gameState.getSpawner().setPopulate(true);
			moveMap = false;
		}
	}
	
	public void clearArrays(){
		tiles.clear();
		enemies.clear();
		items.clear();
		damageTexts.clear();
		projectiles.clear();
		spawnSet = false;
	}


	public static void removeEntityLiving(EntityLiving entityliving) {
		if(entityliving instanceof Enemy){
			enemies.remove(entityliving);
		}else{
			GameStateManager.setState(GameStateManager.DEATHSTATE);
		}
				
	}
	
	public void removeEntity(Item item){
		
	}

	public static CopyOnWriteArrayList<Block> getTiles() {
		return tiles;
	}
	
	public static CopyOnWriteArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public static CopyOnWriteArrayList<Item> getItems() {
		return items;
	}
	
	public static CopyOnWriteArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public static CopyOnWriteArrayList<DamageText> getDamagetexts() {
		return damageTexts;
	}

	public int getMapX() {
		return mapX;
	}

	public int getMapY() {
		return mapY;
	}
		
}
