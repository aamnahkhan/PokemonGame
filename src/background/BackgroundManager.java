package background;
/*Aamnah & Emmelyn
 * Purpose: calling and drawing background image (fight)
 * June 12, 2023
*/
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class BackgroundManager {
	GamePanel gp;
	Background []fight;
	
	public BackgroundManager(GamePanel gp) {
		this.gp = gp;
		fight = new Background[10];
		getImage();
	}
	public void getImage() {
		try {
			fight[0] = new Background();
			fight[0].image =ImageIO.read(getClass().getResourceAsStream("/backgrounds/fight1.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void draw(Graphics2D g2) {
		g2.drawImage(fight[0].image,  0, 0, gp.screenWidth, gp.screenHeight, null);
	}
}
