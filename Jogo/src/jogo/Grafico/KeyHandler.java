package jogo.Grafico;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean up1Pressed, up2Pressed, down1Pressed, down2Pressed, left1Pressed, left2Pressed, right1Pressed, right2Pressed, space1Pressed, enter2Pressed;
	public String player;
	
	public KeyHandler(String player) {
		this.player = player;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//PARA O MENU
		
		
		//PARA O JOGO
		int code = e.getKeyCode();
		if(player == "Player 1") {
		if (code == KeyEvent.VK_W) {
			up1Pressed = true;
		}
		if (code == KeyEvent.VK_S) {
			down1Pressed = true;
		}
		if (code == KeyEvent.VK_A) {
			left1Pressed = true;
		}
		if (code == KeyEvent.VK_D) {
			right1Pressed = true;
		}
		if(code == KeyEvent.VK_SPACE) {
			space1Pressed = true;
		}} else {
		
		if(code == KeyEvent.VK_UP) {
			up1Pressed = true;
		}
		
		if (code == KeyEvent.VK_DOWN) {
			down1Pressed = true;
		}
		
		if (code == KeyEvent.VK_LEFT) {
			left1Pressed = true;
		}
		
		if (code == KeyEvent.VK_RIGHT) {
			right1Pressed = true;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			space1Pressed = true;
		}
		}
}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(player == "Player 1") {
		if (code == KeyEvent.VK_W) {
			up1Pressed = false;
		}
		if (code == KeyEvent.VK_S) {
			down1Pressed = false;
		}
		if (code == KeyEvent.VK_A) {
			left1Pressed = false;
		}
		if (code == KeyEvent.VK_D) {
			right1Pressed = false;
		}
		if(code == KeyEvent.VK_SPACE) {
			space1Pressed = true;
		}} else {
		if(code == KeyEvent.VK_UP) {
			up1Pressed = false;
		}
		
		if (code == KeyEvent.VK_DOWN) {
			down1Pressed = false;
		}
		
		if (code == KeyEvent.VK_LEFT) {
			left1Pressed = false;
		}
		
		if (code == KeyEvent.VK_RIGHT) {
			right1Pressed = false;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			space1Pressed = true;
		}
		}
	}
}