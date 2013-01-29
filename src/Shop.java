import java.awt.*;

import javax.swing.ImageIcon;


public class Shop {
	
	public static int shopWidth = 5;
	public static int buttonSize = 72;
	public static int cellSpace = 8;
	public static int itemIn = 4;
	public static int heldId = -1;
	public static int[] buttonId = {0,1,2,3,10};
	public static int[] buttonPrice = {0,10,20,30,40,50,60,70,80,90,0};
	public static int[] buttonRange = {0,108,54,1,1,1,1,1,1,0};
	public static int[] buttonDamage = {0,1,1,0,0,0,0,0,0,0};
	public static int[] buttonFire = {0,2000,2000,0,0,0,0,0,0,0};
	
	public Rectangle[] button = new Rectangle[shopWidth];
	
	public boolean holdsItem = false;
	
	public Shop() {
		define();
	}
	
	public void click(int mousebutton) {
		if (mousebutton == 1) {
			for(int i = 0; i < button.length; i++) {
				if (button[i].contains(Screen.mse)) {
					if (i == 0) {
						buttonId[1] -= 1;
						buttonId[2] -= 1;
						buttonId[3] -= 1;
						if (buttonId[1] == 0) {
							buttonId[1] = 9;
						}
						if (buttonId[2] == 0) {
							buttonId[2] = 9;
						}
						if (buttonId[3] == 0) {
							buttonId[3] = 9;
						}
					} else if (i == 4) {
						buttonId[1] += 1;
						buttonId[2] += 1;
						buttonId[3] += 1;
						if (buttonId[1] == 10) {
							buttonId[1] = 1;
						}
						if (buttonId[2] == 10) {
							buttonId[2] = 1;
						}
						if (buttonId[3] == 10) {
							buttonId[3] = 1;
						}
					} else {
					heldId = buttonId[i];
					holdsItem=true;
					}
				}
				if (holdsItem==true) {
					if (Screen.myCoins >= buttonPrice[heldId]) {
						for (int y = 0;y<Screen.room.block.length;y++) {
							for (int x=0;x<Screen.room.block[0].length;x++) {
								if (Screen.room.block[y][x].contains(Screen.mse)) {
									if (Screen.room.block[y][x].groundID == Value.groundOpen && Screen.room.block[y][x].airID == Value.airOpen) {
										Screen.room.block[y][x].airID = heldId; 
										Screen.room.block[y][x].towerRange = new Rectangle((Screen.room.block[y][x].x-(buttonRange[heldId])), (Screen.room.block[y][x].y-(buttonRange[heldId])), (54+(buttonRange[heldId]*2)), (54+(buttonRange[heldId]*2))); 
										Screen.room.block[y][x].towerDamage = buttonDamage[heldId];
										Screen.room.block[y][x].fireRate = buttonFire[heldId];
										Screen.myCoins -= buttonPrice[heldId];
										heldId = -1;
										holdsItem=false;
									}
								}
							}
						}
					}
				}
			}
		} else if (mousebutton == 3) {
			heldId = -1;
			holdsItem=false;
		}
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
			
			g.drawImage(new ImageIcon("res/knights/tower" + buttonId[i] + ".png").getImage(), button[i].x + itemIn, button[i].y + itemIn, button[i].width - (itemIn*2), button[i].height - (itemIn*2), null);
			if (buttonPrice[buttonId[i]] > 0) {
				g.setColor(new Color(255,255,255));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				g.drawString(buttonPrice[buttonId[i]] + "", button[i].x + itemIn, button[i].y + itemIn + 10);
			}
			if(button[i].contains(Screen.mse)) {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
			
			if (holdsItem) {
				g.drawImage(new ImageIcon("res/knights/tower" + heldId + ".png").getImage(), Screen.mse.x - ((button[i].width + (itemIn*2))/2), Screen.mse.y - ((button[i].height + (itemIn*2))/2), button[i].width, button[i].height - 18, null);
			}
		}
	}
}