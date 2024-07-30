package Entity;
/*Aamnah & Emmelyn
 * Purpose: charizard is subclass of entity 
 * June 12, 2023
*/
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.Scaling;
																					
public class Charizard extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Charizard (GamePanel gp, KeyHandler keyH) {
		super(gp); //caling constructors
		this.gp = gp;
		this.keyH = keyH;
						
		setDefaultValues();
		getPlayerImage();
	}
	/* Pre: x and y int coordinates
	 * Purpose: set default values of charizard at beginning of game
	 */
	public void setDefaultValues() { //default values of charizard
		xwin = 180; //x coordinate
		ywin = 100; //y coordinate
		 
		speedwin = 4; //speed of charizard
		direction = "down"; //default direction
		
		WmaxLife = 3; //maxlife		
	}
	/* Pre: none
	 * Purpose: call scaled image
	 * Post: return scaled image
	 */
	public void getPlayerImage() {
		CR1 = setup("CR1"); //sprite images
		CR2 = setup("CR2");
		CR3 = setup("CR3");
		CR4 = setup("CR4");
		CR5 = setup("CR5");
		CL1 = setup("CL1");
		CL2 = setup("CL2");
		CL3 = setup("CL3");
		CL4 = setup("CL4");
		CL5 = setup("CL5");

	}
	/* Pre: string image
	 * Purpose: scale the image beforehand
	 * Post: return scaled image
	 */
	public BufferedImage setup (String imageName) {
		Scaling scaling = new Scaling(); //pre scaling work to avoid lots of chunky code in get image method
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" +imageName+ ".png"));
			image = scaling.scaleImage(image, gp.tileSize*2, gp.tileSize*2);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	/* Pre: nothing
	 * Purpose: move charizard after arrow keys are pressed
	 */
	public void update() { //keyhandler method
		if(keyH.upPressed== true) { //moves up
			direction = "up";
			if(ywin!=100) { //bounds to keep charizard within the battlefield
				ywin -=speedwin;
			}			
			else {
				ywin = 100;
			}
		}
		if(keyH.downPressed== true) {
			direction = "down";
			if(ywin!=412) {
				ywin +=speedwin;
			}
			else {
				ywin=348;
			}
		}
		if(keyH.leftPressed == true) {
			direction = "left";
			if (xwin!=180) {
				xwin -=speedwin;
			}
			else {
				xwin =180;
			}
		}
		if(keyH.rightPressed == true) {
			direction = "right";
			if(xwin!=552) {
				xwin +=speedwin;
			}
			else {
				xwin= 500;
			}
		}
		if (gp.collidechar == true) {
			xwin = xwin-4;
			ywin = ywin -4;
		}
		if (gp.invincible==false) {
			Wlife--;
			gp.invincible = true;
			if (gp.invincible =true) {
				gp.invincibleCount++;
				if (gp.invincibleCount>60) {
					gp.invincible = false;
					gp.invincibleCount =0;
				}
			}
		}
		
		gp.collidechar = false;
		
		spriteCounter++; //count sprites each key movement to keep charizard animated
		if (spriteCounter>12) { //counting sprites
			if(spriteNumber==1) {
				spriteNumber=2;
			}
			else if (spriteNumber ==2) {
				spriteNumber = 1;
			}
			spriteCounter=0;
		}
	}
	/* Pre: nothing
	 * Purpose: return charizard x coordinates after moving
	 * Post: charizard x coordinates after moving
	 */
	public int returnX () {
		return this.xwin;
	}
	/* Pre: nothing
	 * Purpose: return charizard y coordinates after moving
	 * Post: charizard y coordinates after moving
	 */
	public int returnY () {
		return this.ywin;
	}
	/* Pre: graphics
	 * Purpose: draw sprites of each charizard during the movement
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image1 = null;
		
		switch(direction) {
		case "up":
			image1 = CR5; //show image number 5
			break;
			
		case "down":
			image1 = CR5;
			break;
			
		case "left":
			if(spriteNumber ==1) { //turn left then move animatedle by showing multiple images
				image1 = CL1;

			}
			if(spriteNumber ==2) { //if sprite increase by 1 show other sprite to keep animated
				image1 = CL2;

			}
			if(spriteNumber ==3) {
				image1 = CL3;

			}
			if(spriteNumber ==4) {
				image1 = CL4;

			}
			if(spriteNumber ==5) {
				image1 = CL5;

			}
			break;
		
		case "right":
			if(spriteNumber ==1) {
				image1 = CR1;

			}
			if(spriteNumber ==2) {
				image1 = CR2;

			}
			if(spriteNumber ==3) {
				image1 = CR3;

			}
			if(spriteNumber ==4) {
				image1 = CR4;

			}
			if(spriteNumber ==5) {
				image1 = CR5;

			}
			break;
		}
		g2.drawImage(image1,  xwin, ywin, null); //draw charizard
		
		
	}
}
