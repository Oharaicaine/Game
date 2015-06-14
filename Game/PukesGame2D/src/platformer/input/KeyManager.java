package platformer.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import platformer.states.GameState;

public class KeyManager implements KeyListener{
	
	private boolean[] keys;
	public boolean up, down, left, right;
	public static boolean shift, space, esc;
	public boolean one, two, three, four;
	public boolean key_F1;
	public boolean key_Q;
	public static boolean key_E;
	private boolean cooldown = false;
	private int cooldownTimer = 0;
	
	public KeyManager() {
		keys = new boolean[265];	
	}
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		key_E = keys[KeyEvent.VK_E];
		shift = keys[KeyEvent.VK_SHIFT];
		
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		four = keys[KeyEvent.VK_4];
		
		if(!cooldown){
			cooldown = true;
			esc = keys[KeyEvent.VK_ESCAPE];
			key_Q = keys[KeyEvent.VK_Q];
			key_F1 = keys[KeyEvent.VK_F1];
		}else{
			esc = false;
			key_Q = false;
			key_F1 = false;
			cooldownTimer++;
			if(cooldownTimer > 6){
				cooldown = false;
				cooldownTimer = 0;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		keys[key.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent key) {
		keys[key.getKeyCode()] = false;

	}
	@Override
	public void keyTyped(KeyEvent key) {	
		if(key.getKeyChar() == 'i'){
			if(GameState.getPlayer().getInventory().showInventory()){
				GameState.getPlayer().getInventory().setShowInventory(false);
			}else{
				GameState.getPlayer().getInventory().setShowInventory(true);
			}
		}
	}




}
