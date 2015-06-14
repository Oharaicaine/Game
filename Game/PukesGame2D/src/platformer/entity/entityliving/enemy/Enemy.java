package platformer.entity.entityliving.enemy;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import platformer.entity.entityliving.EntityHurt;
import platformer.entity.entityliving.EntityLiving;
import platformer.entity.entityliving.player.Player;
import platformer.main.Main;
import platformer.utils.Check;
import platformer.world.World;

public class Enemy extends EntityLiving{
	
	protected float agroRange = 800;
	protected Player player;
	protected boolean grace = true;
	
	protected Ellipse2D.Double hitR; 
	
	protected float healthMulti = 1;
	protected float speedMulti = 1;
	protected float damageMulti = 1;
	protected float critMulti = 1;
	protected float attackSpeedMulti = 1;
	
	

	public Enemy(Player player, float x, float y, int level) {
		super(x, y);
		this.player = player;
		this.level = level;
		this.xpValue = level*10;
		hitR = new Ellipse2D.Double(x, y, attackRange, attackRange);
	}
	
	protected void setStats() {
		health = (float) (15 + (level*1.5)) * healthMulti;
		maxHealth = health;
		speed = (float) (1.5 + (level/10)) * speedMulti;
		maxDamage = (float) (6.0 + (level*1.5)) * damageMulti;
		minDamage = (float) (1.5 + (level*1.5)) * damageMulti;
		critChance = (float) 5.0 * critMulti;
		attackSpeed = (float) 1.0 * attackSpeedMulti;
		attackRange = 100 + level ;
		
	}
	
	protected void reset(){
		inputUp = false;
		inputDown = false;
		inputLeft = false;
		inputRight = false;
	}

	public void tick() {
		timers();
		if(!grace){ // delay when spawned before they can move and attack //TODO connect to player first movement
		AiMove();
		AiAttack();
		}
		super.tick();
	}
	
	public void render(Graphics2D g){
		g.draw(hitR);
		super.render(g);
	}
	
	private void AiMove() {
		if(Check.DistanceFromPlayer(player, this) < agroRange){
			if(!isRanged()){
				if(Check.DistanceFromPlayer(player, this) > 64 + speed){
					if(player.getY() < y - speed){
						inputUp = true;	
					}
					if(player.getY() > y + speed){
						inputDown = true;
					}
					if(player.getX() < x - speed){
						inputLeft = true;
					}
					if(player.getX() > x + speed){
						inputRight = true;
					}	
				}
			}
			if(isRanged()){
				if(Check.DistanceFromPlayer(player, this) > 400){
					if(player.getY() < y - speed){
						inputUp = true;	
					}
					if(player.getY() > y + speed){
						inputDown = true;
					}
					if(player.getX() < x - speed){
						inputLeft = true;
					}
					if(player.getX() > x + speed){
						inputRight = true;
					}	
				}
				else if(Check.DistanceFromPlayer(player, this) < 300){
					if(player.getY() < y - (speed / 2) && y < Main.getHeight() - entitySize){
						inputDown = true;	
					}
					if(player.getY() > y + (speed / 2) && y > 0){
						inputUp = true;
					}
					if(player.getX() < x - (speed / 2) && x > Main.getWidth() - entitySize){
						inputRight = true;
					}
					if(player.getX() > x + (speed / 2) && x > 0){
						inputLeft = true;
					}	
					
				}
			}
		}
	}
	
	private void AiAttack(){
		hitR = new Ellipse2D.Double(getCenterX()-(attackRange/2), getCenterY()-(attackRange/2), attackRange, attackRange);
		
		if(hitR.intersects(player.getBounds2D()) && canAttack){
			EntityHurt.Hurt(this, player);
			canAttack = false;
		}	
		
		if(isRanged()){
		}
	}
	private int graceTimer = 0;
	
	private void timers(){
		if(grace){
			graceTimer++;
			if(graceTimer > 100){
				grace = false;
				graceTimer = 0;
			}
		}
		
	}
	
	public boolean isRanged(){
		return false;
	}
	
	public boolean getGrace() {
		return grace;
	}

	
	
	

}
