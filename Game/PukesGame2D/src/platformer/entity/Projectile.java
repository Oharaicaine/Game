package platformer.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import platformer.blocks.Block;
import platformer.entity.entityliving.EntityHurt;
import platformer.entity.entityliving.EntityLiving;
import platformer.entity.entityliving.enemy.Enemy;
import platformer.entity.entityliving.player.Player;
import platformer.utils.Assets;
import platformer.world.World;

public class Projectile extends Rectangle{
	
	protected BufferedImage image;
	protected float speed, lifeTime;
	protected double x, y;
	protected double deltaX, deltaY;
	private double startX, startY, endX, endY;
	private EntityLiving caster;
	private double rotation;
	private int size;
	private int lifeTimeTimer = 0;
	private Player player;

	public Projectile(EntityLiving caster, Point end, int size, int speed) {
		this.size = size;
		this.speed = speed + caster.getSpeed();;
		this.x = caster.getX()+ size/2;
		this.y = caster.getY()+ size/2;
		this.caster = caster;
		this.startX = caster.getCenterX();
		this.startY = caster.getCenterY();
		this.endX = end.getX();
		this.endY = end.getY();
		setBounds((int)x, (int)y, size, size);
		init();
	}
	public Projectile(EntityLiving caster, Player player, int size, int speed) {
		this.size = size;
		this.speed = speed + caster.getSpeed();
		this.x = caster.getX()+ size/2;
		this.y = caster.getY()+ size/2;
		this.caster = caster;
		this.player = player;
		this.startX = caster.getCenterX();
		this.startY = caster.getCenterY();
		this.endX = player.getCenterX();
		this.endY = player.getCenterY();
		setBounds((int)x, (int)y, size, size);
		init();
	}
	
	private void init() {
		lifeTime = 500/speed;
		image = Assets.getItemMagicBall();
		
		deltaX = endX - startX;
		deltaY = endY - startY;
		rotation = Math.atan2(deltaX, deltaY);
		
	}

	public void tick(){
		
		x = x + (speed * Math.sin(rotation));
		y = y + (speed * Math.cos(rotation));
		setBounds((int)x, (int)y, size, size);

		if(caster instanceof Enemy){
			if(getBounds().intersects(player.getBounds())){
				EntityHurt.Hurt(caster, player);
				World.getProjectiles().remove(this);
			}
		}
		if(caster instanceof Player){
			for(Enemy em : World.getEnemies()){
				if(getBounds().intersects(em.getBounds())){
					EntityHurt.Hurt(caster, em);
					World.getProjectiles().remove(this);
				}
			}
		}
		for(Block blocks : World.getTiles()){
			if(blocks.isSolid()){
				if(getBounds().intersects(blocks.getBounds())){
					World.getProjectiles().remove(this);
				}
			}
		}
		
		
		lifeTimeTimer++;
		if(lifeTimeTimer > lifeTime){
			World.getProjectiles().remove(this);
		}
		
	}
	
	public void render(Graphics2D g){
		g.draw(getBounds());
		g.drawImage(image, (int)x-size/2, (int)y-size/2, size*2, size*2, null);
	}
}
