package Entity;
/*Aamnah & Emmelyn
 * Purpose: superclass for all characters in the game
 * June 12, 2023
*/
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.Scaling;
public class Entity {
	GamePanel gp;
	//attacking
	boolean attacking = false;
	//collision detection
	
	//winner
	public int  xwin,ywin, xattack, yattack; //attack coordinates and charizard coordinates
	public int speedwin; //speed of charizard
	public int WmaxLife; //max life of charizard
	public int Wlife; // life of charizard
	
	//both players
	public int spriteCounter = 0; //sprite counter
	public int spriteNumber = 1;  //sprite number
	public BufferedImage CR1,CR2,CR3,CR4,CR5,CL1,CL2,CL3,CL4,CL5; //charizard pictures
	public String direction; //direction of movement
	public BufferedImage fire1, fire2; //attack sprites
	public int speedfire, attack; //speed of attack

	//magmar opponent
	public BufferedImage M1,M2; //opponent sprites
	public BufferedImage fireballAttack1; //opponent attack sprites
	public BufferedImage fireballAttack2;
	public int xopp, yopp, yattack1, xattack1,yattack2, xattack2,yattack3, xattack3; //3 attack coordinates
	public int speedopp, speedattack; //speed of opponent and speed of attack
	public int OPPspriteCounter = 0; //sprite counter
	public int OPPspriteNumber = 1; // sprite number
	public int Olife, OmaxLife ; //opponent life and max life
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}

}
