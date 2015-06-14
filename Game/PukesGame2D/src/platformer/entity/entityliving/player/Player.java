package platformer.entity.entityliving.player;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Random;

import platformer.entity.entityliving.EntityHurt;
import platformer.entity.entityliving.EntityLiving;
import platformer.entity.entityliving.enemy.Enemy;
import platformer.entity.items.Item;
import platformer.entity.items.skills.ItemArcaneBall;
import platformer.entity.items.skills.ItemManaShield;
import platformer.input.KeyManager;
import platformer.input.MouseManager;
import platformer.inventory.Inventory;
import platformer.skills.Skill;
import platformer.states.GameState;
import platformer.states.GameStateManager;
import platformer.utils.Assets;
import platformer.utils.Check;
import platformer.world.World;


public class Player extends EntityLiving{
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private PlayerStats playerStats;
	private PlayerSkills playerSkills;
	private Inventory inventory;
	private Arc2D.Double arc;
	private Arc2D.Double arcAttack;
	private Random random = new Random();
	
	public Player(GameState gameState, float x, float y){
		super(x, y);
		this.keyManager = gameState.getKeyManager();
		this.mouseManager = gameState.getMouseManager();
		init();
	}
	
	public void init(){
		images = Assets.getImagesLeft();
		playerStats = new PlayerStats(this);
		playerSkills = new PlayerSkills(this);
		inventory = new Inventory(this, mouseManager, keyManager);
		
		inventory.getSkillSlots().get(0).addItem(new ItemArcaneBall((float)inventory.getSkillSlots().get(1).getX(), (float)inventory.getSkillSlots().get(1).getY()));
		inventory.getSkillSlots().get(1).addItem(new ItemManaShield((float)inventory.getSkillSlots().get(1).getX(), (float)inventory.getSkillSlots().get(1).getY()));
		//arc = new Arc2D.Double(getCenterX(), getCenterY(), attackRange, attackRange, 90, 180, Arc2D.CHORD);
		
		//arcAttack = new Arc2D.Double(getCenterX(), getCenterY(), attackRange, attackRange, 90, 180, Arc2D.CHORD);
		
		super.init();
	}
	
	public void tick() {
		input();	
		attack();
		move();	
		playerStats.tick();
		playerSkills.tick();
		inventory.tick();
		//timers();
		super.tick();
	}
	
	private int attackRenderTimer = 0;
	private Color color;

	public void render(Graphics2D g) {
		super.render(g);
		playerStats.render(g);
		inventory.render(g);
		//g.drawRect((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
		//g.draw(arc);
		
		//Attack rendering
		if(!canAttack){
			if(attackRenderTimer < 5){
				g.setComposite(AlphaComposite.SrcOver.derive(0.4f));
				if(inventory.getMainHandSlot().getItem() !=null){
					color = inventory.getMainHandSlot().getItem().getColor();
				}else{
					color = Color.BLACK;
				}
				g.setColor(color);
				g.fill(arcAttack);
				attackRenderTimer++;
				attackOffset-=5;
			}
		}else{
			attackRenderTimer=0;
			attackOffset=20;
		}
		g.setComposite(AlphaComposite.SrcOver);
		g.setColor(Color.WHITE);
		
		
		/*
		 //Rotating images
		double X = mouseManager.getMouse().getX() - getCenterX();
		double Y = mouseManager.getMouse().getY() - getCenterY();
		
		double rotation = Math.atan2(X, Y);
		rotation = Math.toDegrees(rotation);
		//System.out.println(rotation);
		AffineTransform at = new AffineTransform();
		at.translate(x+32, y+32);
		at.rotate(rotation*Math.PI/90);
		at.scale(6.0, 6.0);
		at.translate(0, 0);
		*/

	}
	
	private void input(){
		inputUp = keyManager.up;
		inputDown = keyManager.down;
		inputLeft = keyManager.left;
		inputRight = keyManager.right;
		
		if(inputLeft)images = Assets.getImagesLeft();

		if(inputRight)images = Assets.getImagesRight();
		
		if(KeyManager.shift)speed = 3.0f; //Sprinting
		if(!KeyManager.shift)speed = 2f;
		
		if(keyManager.key_Q){
			if(inventory.getPotionSlot().getItem() != null){
				inventory.getPotionSlot().getItem().useItem();
			}
		}
		//if(keyManager.toggle(KeyEvent.VK_I, true)){
			//new SaveGame(this);
		//}
		
		if(KeyManager.esc){
			if(inventory.showInventory()){
				inventory.setShowInventory(false);
			}else{GameStateManager.setState(GameStateManager.MENUSTATE);}
		}
		
		//Moving the map
		if(getBounds().intersects(World.exitX + 16, World.exitY +16, 32, 32)){
			World.moveMap = true;
		}
		
	}

	private int attackOffset = 20;
	
	public void attack(){

		double attackR = attackRange;
		double X = mouseManager.getMouse().getX() - getCenterX();
		double Y = mouseManager.getMouse().getY() - getCenterY();
		
		double rotation = Math.atan2(X, Y);
		rotation = Math.toDegrees(rotation) + 180;
		
		arcAttack = new Arc2D.Double(getCenterX()-((attackR-attackOffset)/2), getCenterY()-((attackR-attackOffset)/2), attackR-attackOffset, attackR-attackOffset, rotation+30, 120, Arc2D.CHORD);
		arc = new Arc2D.Double(getCenterX()-(attackR/2), (getCenterY())-(attackR/2), attackR, attackR, rotation+10, 160, Arc2D.CHORD);
		
		//	Main Attack
		if(mouseManager.getMouseOnePressed() && canAttack && !inventory.showInventory()){
			canAttack = false;
			ArrayList<Enemy> enemys = Check.AttackEnemy(arc);
			if(!enemys.isEmpty()){
				for(Enemy em : enemys)
				EntityHurt.Hurt(this, em);
			}
		}
		//	Secondary Attack
		if(mouseManager.getMouseTwoPressed() && canAttack2 && !inventory.showInventory()){
			if(getActiveSkill() !=null){
				useMana(getActiveSkill().getManaCost());
				canAttack2 = false;
				getActiveSkill().useSkill();
			}
		}
	}	
	
	@Override
	public boolean hasMana(){
		return true;
	}
	
	@Override
	public boolean hasActiveSkill() {	
		return true;
	}
	
	public PlayerStats getPlayerStats() {
		return playerStats;
	}
	public PlayerSkills getPlayerSkills() {
		return playerSkills;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public float getHealth() {	
		return health;
	}
	public void setHealth(float health) {
		this.health = health;	
	}
	public float getMaxHealth(){
		return maxHealth;
	}
	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;		
	}

	public float getMoveSpeed() {		
		return speed;
	}
	public void setMoveSpeed(float speed) {
		this.speed = speed;	
	}
	public void setAttackRange(float range) {
		this.attackRange = range;
		
	}
	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;	
	}

	public void setMinDamage(float minDamage){
		this.minDamage = minDamage;
	}

	public void setMaxDamage(float maxDamage){
		this.maxDamage = maxDamage;
	}
	
	public void setActiveSkill(Skill skill){
		this.activeSkill = skill;
	}

	public void setArmourValue(float armour) {
		this.armourValue = armour;	
	}
	public void setCastSpeed(float castSpeed) {
		this.castSpeed = castSpeed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
		
	}

	
}
