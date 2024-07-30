package Main;
/*Aamnah & Emmelyn
 * Purpose: different screen switching 
 * June 12, 2023
*/
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Screens {
	GamePanel gp;
	Graphics2D g2;
	BufferedImage introscr,instructscr,round1,charizardstats,arcaninestats, winscr, losescr, full, half, empty; //screen & button images
	BufferedImage enter, okay, play, player,opponent;
	public boolean gameEnd = false; //if game is done
	public int titleSubState = 0; // 0 is the intro, 1 is the instruction
	
	public Screens(GamePanel gp) {
		this.gp=gp;
		getImage();

	}
	/* Pre: nothing
	 * Purpose: call image and scale the image from folder
	 */
	public void getImage() {
		try {
			introscr = ImageIO.read(getClass().getResourceAsStream("/backgrounds/backgroundpokemon.png"));
			instructscr = ImageIO.read(getClass().getResourceAsStream("/backgrounds/instructscr.png"));
			round1 = ImageIO.read(getClass().getResourceAsStream("/backgrounds/round1.png"));
			charizardstats = ImageIO.read(getClass().getResourceAsStream("/backgrounds/charizard.png"));
			winscr = ImageIO.read(getClass().getResourceAsStream("/backgrounds/winscr.png"));
			losescr = ImageIO.read(getClass().getResourceAsStream("/backgrounds/losescr.png"));
			
			
			//hearts
			full = ImageIO.read(getClass().getResourceAsStream("/game_objects/full.png"));
			empty = ImageIO.read(getClass().getResourceAsStream("/game_objects/empty.png"));
			
			//buttons
			enter =ImageIO.read(getClass().getResourceAsStream("/buttons/enter.png"));
			okay = ImageIO.read(getClass().getResourceAsStream("/buttons/okaybutton.png"));
			play = ImageIO.read(getClass().getResourceAsStream("/buttons/playbutton.png"));
			player = ImageIO.read(getClass().getResourceAsStream("/buttons/player.png"));
			opponent = ImageIO.read(getClass().getResourceAsStream("/buttons/opponent.png"));

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	/* Pre: graphics g2
	 * Purpose: update the screens hsowing depending on the state of the game is in
	 */
	public void draw (Graphics2D g2) {
		this.g2 = g2;
		
		//TITLE SCREEN
		if (gp.gameState == gp.titleState) {
			drawScreen();
		}
		//PLAY SCREEN
		if (gp.gameState == gp.playState) {
			drawLifePlayer();
			drawLifeOpponent();
		}
		if (gp.gameState == gp.loseState || gp.gameState == gp.winState) {
			endScreen();
		}
	}
	/* Pre:nothing
	 * Purpose: draw screens depending on what title substate and main state the game is in
	 */
	public void drawScreen() {
		if (titleSubState == 0) { 
			//TITLE
			g2.drawImage(introscr,  0, 0, gp.screenWidth, gp.screenHeight, null);
			g2.drawImage(enter,  570, 450, gp.tileSize*3, gp.tileSize+17, null);
		}
		else if (titleSubState ==1) {
			//INSTRUCTION
			g2.drawImage(instructscr,  0, 0, gp.screenWidth, gp.screenHeight, null);
			g2.drawImage(okay,  800, 570, gp.tileSize*2, gp.tileSize+12, null);
		}
		else if (titleSubState ==2) {
			//ROUND
			g2.drawImage(round1,  0, 0, gp.screenWidth, gp.screenHeight, null);
			g2.drawImage(player,  300, 570, gp.tileSize*2, gp.tileSize+12, null);
			g2.drawImage(opponent,  850, 570, gp.tileSize*2, gp.tileSize+12, null);		}
		else if (titleSubState ==3) {
			//STATS WIN
			g2.drawImage(charizardstats,  0, 0, gp.screenWidth, gp.screenHeight, null);
			g2.drawImage(play,  900, 570, gp.tileSize*2, gp.tileSize+12, null);
		}
	}
	public void endScreen() {
		if (gp.gameState == gp.loseState) {
			g2.drawImage(losescr,  0, 0, gp.screenWidth, gp.screenHeight, null);

		}
		else if (gp.gameState == gp.winState) {
			g2.drawImage(winscr,  0, 0, gp.screenWidth, gp.screenHeight, null);

		}
	}
	/* Pre: nothing
	 * Purpose: draw the player hearts and 3 hearts 
	 */
	public void drawLifePlayer() {

		if (gp.charizard.WmaxLife == 0) {
			g2.drawImage(empty,50, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			g2.drawImage(empty,100, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			g2.drawImage(empty,150, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			gp.Pdead = true;
			if (gp.Pdead == true) {
				gp.gameState = gp.loseState;
			}
		}
		else if (gp.charizard.WmaxLife == 1) {
			g2.drawImage(full,50, 25, gp.tileSize/2+4, gp.tileSize/2+4, null ); //draw full
			g2.drawImage(empty,100, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			g2.drawImage(empty,150, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
		}
		else if (gp.charizard.WmaxLife == 2) {
			g2.drawImage(full,50, 25, gp.tileSize/2+4, gp.tileSize/2+4, null ); //draw full
			g2.drawImage(full,100, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw full hearts
			g2.drawImage(empty,150, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
		}
		else if (gp.charizard.WmaxLife == 3) {
			g2.drawImage(full,50, 25, gp.tileSize/2+4, gp.tileSize/2+4, null ); //draw full
			g2.drawImage(full,100, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw full hearts
			g2.drawImage(full,150, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw full hearts
		}
		
	}
	/* Pre: none
	 * Purpose: draw opponent hearts
	 */
	public void drawLifeOpponent() {

		if (gp.opponent.OmaxLife == 0) {
			g2.drawImage(empty,850, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			g2.drawImage(empty,900, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			g2.drawImage(empty,950, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			gp.Odead = true;
			if (gp.Odead == true) {
				gp.gameState = gp.winState;
			}
		}
		else if (gp.opponent.OmaxLife == 1) {
			g2.drawImage(full,850, 25, gp.tileSize/2+4, gp.tileSize/2+4, null ); //draw full
			g2.drawImage(empty, 900, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
			g2.drawImage(empty,950, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
		}
		else if (gp.opponent.OmaxLife == 2) {
			g2.drawImage(full,850, 25, gp.tileSize/2+4, gp.tileSize/2+4, null ); //draw full
			g2.drawImage(full,900, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw full hearts
			g2.drawImage(empty,950, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw empty hearts
		}
		else if (gp.opponent.OmaxLife == 3) {
			g2.drawImage(full,850, 25, gp.tileSize/2+4, gp.tileSize/2+4, null ); //draw full
			g2.drawImage(full,900, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw full hearts
			g2.drawImage(full,950, 25, gp.tileSize/2+4, gp.tileSize/2+4, null );	//draw full hearts
		}
	}
}
