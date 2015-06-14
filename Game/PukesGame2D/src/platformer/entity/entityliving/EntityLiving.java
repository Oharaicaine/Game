package platformer.entity.entityliving;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import platformer.display.DamageText;
import platformer.entity.Entity;
import platformer.entity.entityliving.player.Player;
import platformer.main.Main;
import platformer.skills.Skill;
import platformer.states.GameStateManager;
import platformer.utils.Check;
import platformer.world.LootGenerator;
import platformer.world.World;


@SuppressWarnings("serial")
public class EntityLiving extends Entity{

	protected Point topLeft, topRight, bottomLeft, bottomRight, leftTop, leftBottom, rightTop, rightBottom;
	
	protected float maxHealth, health, maxMana, mana, maxActiveDuration, activeDuration;
	protected float speed, acceleration, minDamage, maxDamage, critChance, attackSpeed, castSpeed, attackRange, armourValue;
	protected boolean stunned = false;
	protected boolean canAttack = true, canAttack2 = true;
	protected float canAttackTimer = 0, canAttackTimer2 = 0;
	protected int level = 1;
	protected int xpValue = 10;
	
	protected int aniTime = 200;
	protected int index = 0;
	
	protected Skill activeSkill = null;
	protected CopyOnWriteArrayList<Buff> buff = new CopyOnWriteArrayList<Buff>();
	
	protected float xMove, yMove;
	protected boolean inputUp, inputDown, inputLeft, inputRight;
	protected float moveUp = 0, moveDown = 0, moveLeft = 0, moveRight = 0;
	protected float padding = 5;
	
	private Random random = new Random();
	
	protected ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public EntityLiving(float x, float y) {
		super(x, y);
		health = 10f;
		maxHealth = health;
		armourValue = 10;
		speed = 1.0f;
		acceleration = speed / 5;
		minDamage = 2.0f;
		maxDamage = 5.0f;
		critChance = 5.0f;
		attackSpeed = 1.0f;
		castSpeed = 1.0f;
		attackRange = 90;
		setBounds(	(int)(x + padding), (int)(y + padding),
					(int)((entitySize) - padding * 2),
					(int)((entitySize) - padding * 2));
	}
	
	public void init(){
		
		topLeft = new Point((int)(getX()), (int)(getY() - padding));
		topRight = new Point((int)(getX() + getWidth()), (int)(getY() - padding));
		
		bottomLeft = new Point((int)(getX()), (int)(getY() + getHeight() + padding));
		bottomRight = new Point((int)(getX() + getWidth()), (int)(getY() + getHeight() + padding));	
		
		leftTop = new Point((int)(getX() - padding), (int)(getY()));
		leftBottom = new Point((int)(getX() - padding), (int)(getY() + getHeight()));
		
		rightTop = new Point((int)(getX() + getWidth() + padding), (int)(getY()));
		rightBottom = new Point((int)(getX() + getWidth() + padding), (int)(getY() + getHeight()));
		super.init();
	}
	
	public void move(){
		x += xMove;
		y += yMove;
	}
	
	public void tick(){	
		setBounds(	(int)(x + padding), (int)(y + padding),(int)((entitySize) - padding * 2),(int)((entitySize) - padding * 2));
		movement();
		timers();
		move();
		if(!buff.isEmpty()){
			for(Buff buffs : buff){
				buffs.tick();
			}
		}
	}

	private void movement() {
		xMove = 0;
		yMove = 0;
		//Up
		topLeft = new Point((int)(getX()), (int)(getY() - padding));
		topRight = new Point((int)(getX() + getWidth()), (int)(getY() - padding));
		
		bottomLeft = new Point((int)(getX()), (int)(getY() + getHeight() + padding));
		bottomRight = new Point((int)(getX() + getWidth()), (int)(getY() + getHeight() + padding));	
		
		leftTop = new Point((int)(getX() - padding), (int)(getY()));
		leftBottom = new Point((int)(getX() - padding), (int)(getY() + getHeight()));
		
		rightTop = new Point((int)(getX() + getWidth() + padding), (int)(getY()));
		rightBottom = new Point((int)(getX() + getWidth() + padding), (int)(getY() + getHeight()));
		
		//UP
		if(!Check.CollisionBlock(topLeft, topRight) && !Check.CollisionEnemy(topLeft, topRight) && !(y < -5)){
			if(inputUp && moveDown == 0){
				if(moveUp > speed)moveUp = speed;
				if(moveUp < speed)moveUp += acceleration;
				yMove = -moveUp;
			}
		}else{moveUp = 0;}
		
		if(!inputUp && moveUp > 0){
			moveUp -= acceleration * 2;
			yMove = -moveUp;
			if(moveUp < 0)moveUp = 0;
		}
		
		//Down
		if(!Check.CollisionBlock(bottomLeft, bottomRight) && !Check.CollisionEnemy(bottomLeft, bottomRight) && !(y > Main.getHeight() - entitySize)){
			if(inputDown && moveUp == 0){
				if(moveDown > speed)moveDown = speed;
				if(moveDown < speed)moveDown += acceleration;
				yMove = +moveDown;
			}
		}else{moveDown = 0;}
		
		if(!inputDown && moveDown > 0){
			moveDown -= acceleration * 2;
			yMove = +moveDown;
			if(moveDown < 0)moveDown = 0;
		}
		
		//Left
		if(!Check.CollisionBlock(leftTop, leftBottom) && !Check.CollisionEnemy(leftTop, leftBottom) && !(x < -5 )){
			if(inputLeft && moveRight == 0){
				if(moveLeft > speed)moveLeft = speed;
				if(moveLeft < speed)moveLeft += acceleration;
				xMove = -moveLeft;
			}
		}else{moveLeft = 0;}
		
		if(!inputLeft && moveLeft > 0){
			moveLeft -= acceleration * 2;
			xMove = -moveLeft;
			if(moveLeft < 0)moveLeft = 0;
		}
		
		//Right
		if(!Check.CollisionBlock(rightTop, rightBottom)	&& !Check.CollisionEnemy(rightTop, rightBottom) && !(x > Main.getWidth() - entitySize)){
			if(inputRight && moveLeft == 0){
				if(moveRight > speed)moveRight = speed;
				if(moveRight < speed)moveRight += acceleration;
				xMove = +moveRight;
			}
		}else{moveRight = 0;}
		
		if(!inputRight && moveRight > 0){
			moveRight -= acceleration * 2;
			xMove = +moveRight;
			if(moveRight < 0)moveRight = 0;
		}
		
	}
	
	public void applyEffect(Skill skill) {
		buff.add(new Buff(skill, this));
	}
	
	@Override
	public void render(Graphics2D g){
		
		if(!buff.isEmpty()){
			for(Buff buffs : buff){
				buffs.render(g);
			}
		}
		
		//Health Bar rendering
		g.setColor(Color.GREEN);
		float dmgPer = health / maxHealth;
		if(dmgPer < 0.7 && dmgPer > 0.3)g.setColor(Color.ORANGE);
		if(dmgPer <=  0.3)g.setColor(Color.RED);
		g.fillRect((int)x-10, (int)y-22, (int)(84*dmgPer), 7);
		g.setColor(Color.BLACK);
		g.drawRect((int)x-10, (int)y-22, 84, 7);
		
		//ManaBar
		if(hasMana()){
			g.setColor(Color.BLUE);
			float manaPer = mana / maxMana;
			g.fillRect((int)x-10, (int)y-14, (int)(84*manaPer), 7);
			g.setColor(Color.BLACK);
			g.drawRect((int)x-10, (int)y-14, 84, 7);
		}
		
		//Entity Level
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 22));
		g.drawString(Integer.toString(getLevel()), (int)x + entitySize + 15, (int)y-20);
		g.setColor(Color.WHITE);
		super.render(g);
	}
	
	private void timers() {
		//Animation handler
		aniTime++;
		if(aniTime > (40 + random.nextInt(40))/speed){
			image = images.get(index);
			aniTime = 0;
			index++;
			if(index >= images.size()){
				index = 0;
			}
		}
		if(!canAttack){
			canAttackTimer += attackSpeed/10;
			if(canAttackTimer > 5 ){
				canAttack = true;
				canAttackTimer = 0;
			}
		}
		if(!canAttack2){
			canAttackTimer2 += castSpeed/10;
			if(canAttackTimer2 > 5 ){
				canAttack2 = true;
				canAttackTimer2 = 0;
			}
		}
	}
	public boolean useMana(float amount) {
		if(hasMana()){
			if(mana >= amount){
				mana -= amount;
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}	
	}

	public boolean hasManaShield(){
		return false;
	}
	
	//	Getters and Setters
	public float getMinDamage() {return minDamage;}
	public float getMaxDamage() {return maxDamage;}
	public boolean canBeKnockback(){return true;}
	public boolean hasMana(){return false;}
	public boolean hasActiveSkill(){return false;}
	public float getMana() {return mana;}
	public void setMana(float mana) {this.mana = mana;}
	public float getMaxMana() {return maxMana;}
	public void setMaxMana(float maxMana) {this.maxMana = maxMana;}
	public void addMana(float mana){this.mana += mana;}
	public void setActiveDuration(float activeDuration) {this.activeDuration = activeDuration;}
	public void setMaxActiveDuration(float maxActiveDuration) {this.maxActiveDuration = maxActiveDuration;}
	public float getPadding() {return padding;}
	public int getXpValue() {return xpValue;}
	public float getSpeed() {return speed;}
	public int getLevel() {return level;}
	public void setLevel(int level) {this.level = level;}
	public float getCritChance() {return critChance;}
	public void setCritChance(float critChance) {this.critChance = critChance;}
	public Skill getActiveSkill() {return activeSkill;}

	public CopyOnWriteArrayList<Buff> getBuffs() {
		return buff;
	}

	public float getArmourValue() {
		return armourValue;
	}

	
	public float getHealth() {
		return health;
	}

	public void setHealth(float amount) {
		this.health = amount;
	}
	public void adjustHealth(float amount) {
		this.health += amount;
	}

	public void setStunned(boolean stunned) {
		this.stunned = stunned;	
	}

	public float getMaxHealth() {
		return maxHealth;
	}
	
}
