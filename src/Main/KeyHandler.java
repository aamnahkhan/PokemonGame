package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*Aamnah & Emmelyn
 * Purpose: key handler to update after keys are pressed
 * June 12, 2023
*/
public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed; //all possible pressing options
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//TITLE
		if (gp.gameState==gp.titleState) {
			if(gp.screen.titleSubState == 0) {//show intro
				if (code == KeyEvent.VK_ENTER) {
					gp.screen.titleSubState = 1; //move onto instruct
				}
				
			}
			//instruct
			else if(gp.screen.titleSubState == 1) {//show instruct
				if (code == KeyEvent.VK_ENTER) {
					gp.screen.titleSubState = 2; //move onto round1
				}
				
			}
			//round1
			else if (gp.screen.titleSubState == 2) {
				if (code == KeyEvent.VK_ENTER) {
					gp.screen.titleSubState = 3;//chari stats
				}
			}
			//charizard stats
			else if (gp.screen.titleSubState == 3) {
				if (code == KeyEvent.VK_ENTER) {
					gp.gameState = gp.playState;
				}
			}
		} 
		else if (gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_UP) { //move up
				upPressed = true;
			}
			if (code == KeyEvent.VK_DOWN) { //move down
				downPressed = true;

			}
			if (code == KeyEvent.VK_LEFT) { //move left
				leftPressed = true;

			}
			if (code == KeyEvent.VK_RIGHT) { //move right
				rightPressed = true;

			}	
			if (code == KeyEvent.VK_SPACE) { //attack
				spacePressed = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = false;

		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = false;

		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = false;

		}
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
	}

}
