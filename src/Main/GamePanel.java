package Main;
/*Aamnah & Emmelyn
 * Purpose: main game class where all the gaming will occur 
 * June 12, 2023
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import Entity.Opponent;
import Entity.Charizard;
import Entity.Entity;
import Entity.FireAttack;
import background.BackgroundManager;
import Main.KeyHandler;


public class GamePanel extends JPanel implements Runnable{
	
	//SET SCREEN SETTINGS
	final int originalTileSize = 20; //20*20 tile
	final int scale = 3; //3 times 20*20 (3*20 = 60)
	
	public final int tileSize = originalTileSize * scale; //60x60 tile
	final int maxScreenCol = 22;
	final int maxScreenRow = 11;
	public final int screenWidth = tileSize * maxScreenCol; //1320
	public final int screenHeight = tileSize * maxScreenRow; //660
	
	//GAME STATES
	public int gameState; //game state of the game 
	public final int titleState = 0;	//title and title substate stuff in screens
	public final int playState = 1; //play state to begin the fight
	public final int loseState = 2;
	public final int winState = 3;
	public int g=0; //x coor of updated charizard that is returned
	public int t=0;  //y coor of updated charizard that is returned
	public boolean collidechar = false;
	public boolean collidemag = false;
	public boolean invincible = false;
	public int invincibleCount;
	public boolean Pdead = false;
	public boolean Odead = false;


	Graphics2D g2;
	BufferedImage introscr,instructscr,round1,charizardstats,arcaninestats, winscr, losescr, full, half, empty;
	
	//FPS
	int FPS = 60;
	BackgroundManager fightBackground = new BackgroundManager(this); //call background fight
	KeyHandler keyH = new KeyHandler(this); //key handler
	//public CollisionCheck collided = new CollisionCheck (this);
	public Screens screen = new Screens(this); //call screens
	Thread gameThread; //starting the game thread
	
	//players and opponents
	Charizard charizard = new Charizard(this,keyH); //call all characters and attacks
	FireAttack fire = new FireAttack(this,keyH);
	Opponent opponent = new Opponent(this);
	
	/* Pre: N/A
	 * Purpose: set game panel 
	 * Post: N/A
	 */
	public GamePanel() {
		this.setPreferredSize (new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	/* Pre: N/A
	 * Purpose: setting up the deafult values of game
	 * Post: N/A
	 */
	public void setUpGame() {
		gameState = titleState;
	}
	
	/* Pre: N/A
	 * Purpose: start the game thread (code used from youtube video)
	 * Post: N/A
	 */
	public void startGameThread() {
		gameThread = new Thread (this);
		gameThread.start();
	}
	@Override
	/* Pre: N/A
	 * Purpose: start the game frames to move quickly and update smoothly (code from youtube)
	 * Post: N/A
	 */
	public void run() {
		while(gameThread!=null) {
			double drawInterval = 1000000000/FPS;
			double nextDrawTime = System.nanoTime()+drawInterval;
			while (gameThread!= null) {
				update();
				repaint();
				
				try {
					double remainingTime = nextDrawTime-System.nanoTime();
					remainingTime = remainingTime/1000000;
					
					if (remainingTime<0) {
						remainingTime = 0;
					}
					
					Thread.sleep((long)remainingTime);
					nextDrawTime += drawInterval;
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	/* Pre: N/A
	 * Purpose: update the player movements after each key  
	 * Post: N/A
	 */
	int charizardHearts = 3;
	int magmarHearts = 3;
	
	public void update() {
		if (gameState == playState) {
			intersects();
			charizard.update();
			opponent.update();

			g=charizard.returnX(); //return the x coordinate of charizard
			t=charizard.returnY(); //return the y coordinate of charizard
			fire.update(g,t); //update fire attack with new coordinates

		}
	}
	/* Pre: graphics g2
	 * Purpose: draw the game depending on what state the game is in 
	 * Post: N/A
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		//TITLE
		screen.draw(g2); //still in title
		if (gameState == playState) { //= to 1 and starting playstate
			//MAIN FIGHT
			fightBackground.draw(g2);
			screen.draw(g2);
			charizard.draw(g2);
			opponent.draw(g2);
			fire.draw(g2);
		}
		g2.dispose(); //dispose frames 	
	}

	public void intersects () {
		
		//charizard hitting fireballs
		Rectangle charizardHit = new Rectangle (charizard.xwin,charizard.ywin, tileSize+4,tileSize+4);
		Rectangle firehitChar1 = new Rectangle (opponent.xattack1, opponent.yattack1, tileSize,tileSize);
		Rectangle firehitChar2 = new Rectangle (opponent.xattack2, opponent.yattack2, tileSize,tileSize);
		Rectangle firehitChar3 = new Rectangle (opponent.xattack3, opponent.yattack3, tileSize,tileSize);
		
		//blast burns hitting opponent
		Rectangle blasthitOpp = new Rectangle (fire.xattack, fire.yattack, tileSize,tileSize);
		Rectangle OppHit = new Rectangle (opponent.xopp, opponent.yopp, tileSize*2,tileSize-2);
			
		// Check if the hitbox of Charizard intersects with any of the fireball hitboxes
		if (charizardHit.intersects(firehitChar1) || charizardHit.intersects(firehitChar2) || charizardHit.intersects(firehitChar3)) {

		    // Check which fireball hitbox Charizard intersects with and moves the respective fireball off-screen
		    if (charizardHit.intersects(firehitChar1)) {
		        opponent.xattack1 = 3000;
		    }
		    
		    else if (charizardHit.intersects(firehitChar2)) {
		        opponent.xattack2 = 3000;
		    }
		    
		    else {
		        opponent.xattack3 = 3000;
		    }
		    
		    collidechar = true; // Set the collidechar flag to indicate Charizard has collided with a fireball
		    charizardHearts--; // Decrease the number of hearts for Charizard
		    hearts(charizardHearts,charizard); // Update the hearts
		}
	
		else if (OppHit.intersects(blasthitOpp)) {
			fire.xattack = 2000;
			
			collidemag = true;	
		    magmarHearts--; // Decrease the number of hearts for Charizard
		    hearts(magmarHearts,opponent); // Update the hearts
		}
		
	}

	public void hearts(int hearts, Entity entity) {
		if (entity == charizard) {
			charizard.WmaxLife = hearts;
		}
		else {
			opponent.OmaxLife = hearts;			
		}
		
	}
}