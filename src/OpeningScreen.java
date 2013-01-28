import java.awt.*;


public class OpeningScreen extends Rectangle {
	
	public int yRect = 30;
	public int xRect = 50;
	public int numOptions = 3;
	public int cellSpace = 10;
	
	public Rectangle[] option;
	
	public OpeningScreen() {
		define();
	}
	
	public void define() {
		option = new Rectangle[numOptions];
		
		for(int i = 0; i < numOptions; i++) {
			option[i] = new Rectangle((Screen.myWidth/2) - (xRect/2), (Screen.myHeight/2) - (((yRect + cellSpace)*numOptions)/2), xRect, yRect);
		}
	}
}
