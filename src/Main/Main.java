package Main;
/*Aamnah & Emmelyn
 * Purpose: Main panel that will call the game panel and play it fron here
 * June 12, 2023
*/
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Pokemon League Battle");
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setUpGame();
		gamePanel.startGameThread();
	}

}
