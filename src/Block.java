import java.awt.*;

import javax.swing.ImageIcon;


public class Block extends Rectangle {
	public int groundID;
	public int airID;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void draw(Graphics g) {
		
		if(groundID == Value.groundOpen) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		else if(groundID == Value.pathOpen) {
			g.setColor(Color.GRAY);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		if(airID == Value.airCastle) {
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		if (airID > Value.airCastle) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
			g.drawImage(new ImageIcon("res/knights/tower" + airID + ".png").getImage(),x, y, width, height, null);
		}
	}
}
