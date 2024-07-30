package Entity;
/*Aamnah & Emmelyn
 * Purpose: fireattack is subclass of entity, it is the charizard attack 
 * June 12, 2023
*/
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.Scaling;

public class FireAttack extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	int setX = 0;
	
	public FireAttack (GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
					
		setDefaultValues();
		getAttack();
	}
	/* Pre: x and y int coordinates
	 * Purpose: set default values of fireattack at beginning of game
	 */
	public void setDefaultValues() {
		xattack = xwin; // x coor must be same as charizard
		yattack = ywin; // y coor must be same as charizard
		speedfire = 6; //speed of attack
		direction = "down"; 
		
	}
	/* Pre: none
	 * Purpose: call images from folders
	 */
	public void getAttack() {
		try {
			fire1 = ImageIO.read(getClass().getResourceAsStream("/attack/flame1.png"));
			fire2 = ImageIO.read(getClass().getResourceAsStream("/attack/flame2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	/* Pre: int x and y values of updated charizard
	 * Purpose: release fire after space bar are pressed
	 */
	public void update(int x , int y) {
		yattack = y;
		if (keyH.spacePressed == true) { //cant shoot another fire if previous one is still shooting
			if (setX == 0) {
				xattack = x;
				setX = 1;
			}
			
			direction = "attack";
		}
		if (direction.equals("attack")) { //direction is attack
			xattack +=speedfire;
			if (xattack>=1010) { //finish after leaving edge of battelfield
				yattack =y;
				xattack = x;
				direction = "down"; //default
				setX = 0;
			}

		}
		spriteCounter++;
		if (spriteCounter>12) { //sprite counter
			if(spriteNumber==1) {
				spriteNumber=2;
			}
			else if (spriteNumber ==2) {
				spriteNumber = 1;
			}
			spriteCounter=0;
		}
	}
	/* Pre: graphics
	 * Purpose: draw sprites of each charizard during the movement
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image2 = null;
		switch(direction) {
		case "attack":
			if(spriteNumber ==1) {
				image2 = fire1;
			}
			if(spriteNumber ==2) {
				image2 = fire2;

			}
			break;
		}
		g2.drawImage(image2,  xattack, yattack, gp.tileSize, gp.tileSize, null);
	}
}