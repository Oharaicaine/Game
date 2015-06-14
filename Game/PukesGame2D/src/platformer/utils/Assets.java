package platformer.utils;


import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

public class Assets {

	private static BufferedImage playerSheet;
	private static BufferedImage tileSheet;
	private static BufferedImage enemySheet;
	private static BufferedImage spriteSheet;
	
	private static BufferedImage inventorybg; 
	
	private static ArrayList<BufferedImage> imagesPlayerLeft = new ArrayList<BufferedImage>();
	private static ArrayList<BufferedImage> imagesPlayerRight = new ArrayList<BufferedImage>();
	
	private static ArrayList<BufferedImage> imagesSkeletonLeft = new ArrayList<BufferedImage>();
	private static ArrayList<BufferedImage> imagesSkeletonRight = new ArrayList<BufferedImage>();
	
	private static ArrayList<BufferedImage> imagesMummyLeft = new ArrayList<BufferedImage>();
	private static ArrayList<BufferedImage> imagesMummyRight = new ArrayList<BufferedImage>();
	
	private static ArrayList<BufferedImage> imagesShamanLeft = new ArrayList<BufferedImage>();
	private static ArrayList<BufferedImage> imagesShamanRight = new ArrayList<BufferedImage>();
	
	private static ArrayList<BufferedImage> imagesRhinoLeft = new ArrayList<BufferedImage>();
	private static ArrayList<BufferedImage> imagesRhinoRight = new ArrayList<BufferedImage>();
	
	private static BufferedImage map;
	
	private static BufferedImage player;
	
	private static BufferedImage attack;
	
	private static BufferedImage skeleton;
	private static BufferedImage mummy;
	private static BufferedImage shaman;
	private static BufferedImage rhino;
	
	private static BufferedImage stone;
	private static BufferedImage brick;
	
	private static BufferedImage itemCoin;
	private static BufferedImage itemHealthPotion;
	private static BufferedImage itemMagicBall;
	private static BufferedImage itemManaShield;
	private static BufferedImage itemGraveStone;
	
	private static BufferedImage itemLongsword;
	
	private static BufferedImage backGround;
	private static BufferedImage menuButton;
	private static BufferedImage slotImage;
	
	
	public static void init(){
		
		map = Utils.LoadImage("/map.png");
		tileSheet = Utils.LoadImage("/worldsheet.png");
		playerSheet = Utils.LoadImage("/playersheet.png");
		enemySheet = Utils.LoadImage("/enemysheet.png");
		spriteSheet = Utils.LoadImage("/spritesheet.png");
		
		backGround = Utils.LoadImage("/backGround.png");
		menuButton = Utils.LoadImage("/menuButton.png");
		
		inventorybg = Utils.LoadImage("/inventorybg.png");
		
		//Item sprites
		itemCoin = spriteSheet.getSubimage(0, 0, 24, 24);
		itemHealthPotion = spriteSheet.getSubimage(0, 24*2, 24, 24);
		itemMagicBall = spriteSheet.getSubimage(24, 0, 24, 24);
		itemGraveStone = spriteSheet.getSubimage(0, 24, 24, 24);
		slotImage = spriteSheet.getSubimage(24, 24, 24, 24);
		itemManaShield = spriteSheet.getSubimage(24*2, 0, 24, 24);
		
		itemLongsword = spriteSheet.getSubimage(0, 24*3, 24, 24);
		
		//player sprite
		player = playerSheet.getSubimage(0, 0, 24, 24);
		
		attack = playerSheet.getSubimage(0, 24*7, 24, 24);
		
		imagesPlayerLeft.add(playerSheet.getSubimage(0, 0, 24, 24));
		imagesPlayerLeft.add(playerSheet.getSubimage(24, 0, 24, 24));
		
		imagesPlayerRight.add(playerSheet.getSubimage(0, 24*2, 24, 24));
		imagesPlayerRight.add(playerSheet.getSubimage(24, 24*2, 24, 24));
		//enemy sprite
		
		skeleton = enemySheet.getSubimage(0, 0, 24, 24);
		imagesSkeletonLeft.add(enemySheet.getSubimage(0, 0, 24, 24));
		imagesSkeletonLeft.add(enemySheet.getSubimage(24, 0, 24, 24));
		
		imagesSkeletonRight.add(enemySheet.getSubimage(24*2, 0, 24, 24));
		imagesSkeletonRight.add(enemySheet.getSubimage(24*2, 0, 24, 24));
		
		mummy = enemySheet.getSubimage(0, 24, 24, 24);
		imagesMummyLeft.add(enemySheet.getSubimage(0, 24, 24, 24));
		imagesMummyLeft.add(enemySheet.getSubimage(24, 24, 24, 24));
		
		imagesMummyRight.add(enemySheet.getSubimage(24*2, 24, 24, 24));
		imagesMummyRight.add(enemySheet.getSubimage(24*3, 24, 24, 24));
		
		shaman = enemySheet.getSubimage(0, 24*2, 24, 24);
		imagesShamanLeft.add(enemySheet.getSubimage(0, 24*2, 24, 24));
		imagesShamanLeft.add(enemySheet.getSubimage(24, 24*2, 24, 24));
		
		imagesShamanRight.add(enemySheet.getSubimage(24*2, 24*2, 24, 24));
		imagesShamanRight.add(enemySheet.getSubimage(24*3, 24*2, 24, 24));
		
		rhino = enemySheet.getSubimage(0, 24*2, 24, 24);
		imagesRhinoLeft.add(enemySheet.getSubimage(0, 24*3, 24, 24));
		imagesRhinoLeft.add(enemySheet.getSubimage(24, 24*3, 24, 24));
		
		imagesRhinoRight.add(enemySheet.getSubimage(24*2, 24*3, 24, 24));
		imagesRhinoRight.add(enemySheet.getSubimage(24*3, 24*3, 24, 24));
		//world sprite
		stone = tileSheet.getSubimage(24, 0, 24, 24);
		brick = tileSheet.getSubimage(0, 0, 24, 24);
	
	}
	
	public static BufferedImage getMap() {return map;}
	
	public static BufferedImage getInventorybg(){return inventorybg;}
	public static BufferedImage getSlotImage(){return slotImage;}
	
	public static BufferedImage getStone() {return stone;}
	public static BufferedImage getBrick() {return brick;}
	
	public static BufferedImage getPlayer() {return player;}
	public static BufferedImage getSkeleton() {return skeleton;}
	public static BufferedImage getMummy() {return mummy;}
	public static BufferedImage getShaman() {return shaman;}
	public static BufferedImage getRhino() {return rhino;}
	
	public static ArrayList<BufferedImage> getImagesLeft() {return imagesPlayerLeft;}
	public static ArrayList<BufferedImage> getImagesRight() {return imagesPlayerRight;}
	public static ArrayList<BufferedImage> getImagesSkeletonLeft() {return imagesSkeletonLeft;}
	public static ArrayList<BufferedImage> getImagesSkeletonRight() {return imagesSkeletonRight;}
	public static ArrayList<BufferedImage> getImagesMummyLeft() {return imagesMummyLeft;}
	public static ArrayList<BufferedImage> getImagesMummyRight() {return imagesMummyRight;}
	public static ArrayList<BufferedImage> getImagesShamanLeft() {return imagesShamanLeft;}
	public static ArrayList<BufferedImage> getImagesShamanRight() {	return imagesShamanRight;}
	public static ArrayList<BufferedImage> getImagesRhinoLeft() {return imagesRhinoLeft;}
	public static ArrayList<BufferedImage> getImagesRhinoRight() {return imagesRhinoRight;}
	
	public static BufferedImage getItemCoin() {return itemCoin;}
	public static BufferedImage getItemHealthPotion() {return itemHealthPotion;}
	
	public static BufferedImage getItemMagicBall() {return itemMagicBall;}
	public static BufferedImage getItemManaShield() {return itemManaShield;}
	public static BufferedImage getItemGraveStone() {return itemGraveStone;}
	
	public static BufferedImage getItemLongsword() {return itemLongsword;}
	
	public static BufferedImage getAttack() {return attack;}
	public static BufferedImage getBackGround() {return backGround;}
	public static BufferedImage getMenuButton() {return menuButton;}
	
}
