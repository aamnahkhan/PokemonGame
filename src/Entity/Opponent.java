package Entity;
/*Aamnah & Emmelyn
 * Purpose: opponent is subclass of entity 
 * June 12, 2023
*/
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Opponent extends Entity {
	GamePanel gp;
	public Opponent (GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		setDefaultValues();
		getPlayerImage();
	}
	/* Pre: x and y int coordinates
	 * Purpose: set default values of opponent and attacks at beginning of game
	 */
	public void setDefaultValues() {
		xattack1 = 1010; //x and y coor of 3 attacks
		xattack2 = 1010;
		xattack3 = 1010;
		yattack1 = 100;
		yattack2 = 250;
		yattack3 = 400;
		speedattack = 8; //speed of attack
		xopp = 950; //x and y coor of opponent
		yopp = 100;
		speedopp = 4;
		OmaxLife = 3;
		
	}
	/* Pre: none
	 * Purpose: upload pics from downloads
	 */
	public void getPlayerImage() {
		try {
			//actual character
			M1 = ImageIO.read(getClass().getResourceAsStream("/player/M1.png"));
			M2 = ImageIO.read(getClass().getResourceAsStream("/player/M2.png"));
			
			//attacks
			fireballAttack1 = ImageIO.read(getClass().getResourceAsStream("/attack/fireball2.png"));
			fireballAttack2 = ImageIO.read(getClass().getResourceAsStream("/attack/fireball1.png"));
	

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Pre: nothing
	 * Purpose: move oppponent after arrow keys are pressed
	 */
	public void update() {
			yopp+=speedopp;
			xattack1-=speedattack; //move all the attacks
			xattack2 -= speedattack;
			xattack3 -= speedattack;
			if(yopp==412) { //opponent only moves up and down
				yopp=100;
			}
						
			while (xattack1==170 || xattack2==170 || xattack3==170) {
				xattack1 = 946; //attacks go out of battlefield renew them all
				yattack1= 100;
				xattack2 = 946;
				yattack2=250;
				xattack3 = 946;
				yattack3=400;
			}
		if (gp.collidemag == true) {
			yopp = yopp-16;
			Olife-=1;
		}
		gp.collidemag = false;

		spriteCounter++; //sprite counter
		if (OPPspriteCounter>12) {
			if(OPPspriteNumber==1) {
				OPPspriteNumber=2;
			}
			else if (OPPspriteNumber ==2) {
				OPPspriteNumber = 1;
			}
			OPPspriteCounter=0;
		}
	}
	/* Pre: graphics
	 * Purpose: draw sprites of each opponent during the movement
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image1 = null; //image of opponent
		BufferedImage image2 = null; // image of attack
		
		if(spriteNumber ==1) {
			image1 = M1;
			image2 = fireballAttack2;
			
		}
		else if(spriteNumber ==2) {
			image1 = M2;
			image2 = fireballAttack1;
		}
		g2.drawImage(image1,  xopp, yopp, gp.tileSize*2, gp.tileSize*2, null); //draw character
		g2.drawImage(image2,  xattack1, yattack1, gp.tileSize*2, gp.tileSize, null); //draw all 3 attacks
		g2.drawImage(image2,  xattack2, yattack2, gp.tileSize*2, gp.tileSize, null);
		g2.drawImage(image2,  xattack3, yattack3, gp.tileSize*2, gp.tileSize, null);
	}
}
