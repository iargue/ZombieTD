import java.awt.*;

import javax.swing.ImageIcon;


public class Block extends Rectangle {
	public Rectangle towerRange;
	public int groundID;
	public int airID;
	public int target = -1;
	public int towerDamage;
	public int fireRate;
	public int coolDown = 0;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void physic() {
		target = -1;
		if (airID > Value.airCastle) {
			for(int i=Screen.enemy.length-1;i>-1;i--) {
				if (Screen.enemy[i].inGame && Screen.enemy[i].isDead == false) {
						if (towerRange.intersects(Screen.enemy[i])) {
							target = i;
						}
				}
			}
		}
		
		if (target>=0 && coolDown == 0) {
			System.out.println(coolDown);
			Screen.enemy[target].health -= 1;
			coolDown = fireRate;
			if (Screen.enemy[target].health <=0) {
				Screen.enemy[target].isDead = true;
				Screen.enemy[target].enemyWalk = 0;
			}
		} else {
			coolDown -= 1;
			if (coolDown < 0) {
				coolDown = 0;
			}
		}
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
	public void fight(Graphics g) {
		if (airID > Value.airCastle) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
			g.drawImage(new ImageIcon("res/knights/tower" + airID + ".png").getImage(),x, y, width, height, null);
			if (target>=0) {
				g.setColor(new Color(255,255,0));
				g.drawLine(x + (width/2), y + (height/2), Screen.enemy[target].x + (Screen.enemy[target].width/2), Screen.enemy[target].y + (Screen.enemy[target].height/2));
			}
		}
		
	}
	
}
