import java.awt.*;


public class Shop {
	
	public static int shopWidth = 5;
	public static int buttonSize = 72;
	public static int cellSpace = 8;
	
	public Rectangle[] button = new Rectangle[shopWidth];
	
	public Shop() {
		define();
	}
	
	public void define() {
		for(int i = 0; i < button.length; i++) {
			button[i] = new Rectangle((Screen.myWidth)/2 - ((shopWidth*(buttonSize+cellSpace))/2) + ((buttonSize+cellSpace)*i) + cellSpace/2, (Screen.room.block[Screen.room.worldHeight-1][0].y) + Screen.room.blockSize + cellSpace, buttonSize, buttonSize);
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
	}
}