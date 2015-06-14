package platformer.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener{

	public static boolean mouseOnePressed = false;
	public static boolean mouseTwoPressed = false;
	public static int mouseMoveX, mouseMoveY;
	public static Point mouse;
	
	public void tick(){
		mouse = new Point(mouseMoveX, mouseMoveY);
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			mouseOnePressed = true;
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			mouseTwoPressed = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			mouseOnePressed = false;
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			mouseTwoPressed = false;
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoveX = e.getX();
		mouseMoveY = e.getY();
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		mouseMoveX = e.getX();
		mouseMoveY = e.getY();
		
	}
	
	public Point getMouse() {
		return mouse;
	}
	
	public boolean getMouseOnePressed() {
		return mouseOnePressed;
	}
	
	public boolean getMouseTwoPressed() {
		
		return mouseTwoPressed;
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
	}



	

}
