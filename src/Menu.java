import java.awt.*;

public class Menu {
	public static int menuLength = 3;
	public static int buttonSize = 45;
	public static int cellSpace = 8;
	
	public static int statusLength = 3;
	public static int statusSpace = 100;
	public static int centerString = 8;
	
	public Rectangle[] button = new Rectangle[menuLength];
	public Rectangle[] statusViewer = new Rectangle[statusLength];
	
	public Menu() {
		define();
	}
	
	public void define() {
		for(int i = 0; i < button.length; i++) {
			button[i] = new Rectangle((Screen.room.block[0][Screen.room.worldWidth-1].x) + Screen.room.blockSize + cellSpace, cellSpace + (i*(cellSpace + buttonSize)) , buttonSize, buttonSize);
		}
		for(int j = 0; j < statusViewer.length; j++) {
			statusViewer[j] = new Rectangle((Screen.room.block[0][0].x) - Screen.room.blockSize, statusSpace + (j*(statusSpace + buttonSize)) - centerString, buttonSize, buttonSize);
		}
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < button.length; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			g.setColor(Color.BLACK);
			g.drawRect(button[i].x, button[i].y, button[i].width, button[i].height);
			
			if(button[i].contains(Screen.mse)) {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
		}
		
		for(int j = 0; j < statusViewer.length; j++) {
			
			g.setFont(new Font("Arial", Font.BOLD, 16));
			
			if(j == 0) {
				g.setColor(Color.RED);
				g.fillRect(statusViewer[j].x, statusViewer[j].y, statusViewer[j].width, statusViewer[j].height);
				g.setColor(Color.BLACK);
				g.drawRect(statusViewer[j].x, statusViewer[j].y, statusViewer[j].width, statusViewer[j].height);
				g.drawString("" + Screen.myHealth, (Screen.room.block[0][0].x) - Screen.room.blockSize + centerString, statusSpace + (j*(statusSpace + buttonSize)) + (buttonSize + cellSpace));
			}
			else if (j == 1) {
				g.setColor(Color.YELLOW);
				g.fillRect(statusViewer[j].x, statusViewer[j].y, statusViewer[j].width, statusViewer[j].height);
				g.setColor(Color.BLACK);
				g.drawRect(statusViewer[j].x, statusViewer[j].y, statusViewer[j].width, statusViewer[j].height);
				g.drawString("" + Screen.myCoins, (Screen.room.block[0][0].x) - Screen.room.blockSize + centerString, statusSpace + (j*(statusSpace + buttonSize)) + (buttonSize + cellSpace));
			}
			else {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(statusViewer[j].x, statusViewer[j].y, statusViewer[j].width, statusViewer[j].height);
				g.setColor(Color.BLACK);
				g.drawRect(statusViewer[j].x, statusViewer[j].y, statusViewer[j].width, statusViewer[j].height);
				g.drawString("" + Screen.myWavesRemaining, (Screen.room.block[0][0].x) - Screen.room.blockSize + centerString, statusSpace + (j*(statusSpace + buttonSize)) + (buttonSize + cellSpace));
			}
		}
	}
}
