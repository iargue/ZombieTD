import java.awt.*;

public class Enemy extends Rectangle {
	
	public int xC, yC;
	public int walkFrame = 0, walkSpeed = 10;
	public int health;
	
	
	public int enemySize = 54;
	public int enemyID;
	public int enemyWalk = 0;
	
	public int down = 0, left = 1, right = 2, up = 3;
	public int direction = down;
	
	public boolean inGame = false;
	public boolean isDead = false;
	public boolean wasDown = false, wasUp = false, wasRight = false, wasLeft = false;

	
	public void spawnEnemy(int enemyId) {
		for(int x = 0; x < Screen.room.block[0].length; x++) {
			if(Screen.room.block[0][x].groundID == Value.pathOpen) {
				setBounds(Screen.room.block[0][x].x, Screen.room.block[0][x].y, enemySize, enemySize);
				xC = x;
				yC = y;
			}
		}
		this.enemyID = enemyId;
		walkSpeed = 10;
	    walkFrame = 0;
	    health = 1;
		inGame = true;
	}
	
	public void deleteEnemy() {
		inGame = true;
		isDead = true;
	}
	
	public void looseHealth() {
		Screen.myHealth -= 1;
	}
	
	
	
	public void physic() {
		
		if(walkFrame >= walkSpeed) {
			if(direction == down) {
				y += 1;
			}
			else if(direction == left) {
				x -= 1;
			}
			else if(direction == right) {
				x += 1;
			}
			else if(direction  == up) {
				y -= 1;
			}
			
			enemyWalk += 1;
			
			if(enemyWalk == Screen.room.blockSize) {
				if(direction == down) {
					yC += 1;
					wasDown = true;
				}
				else if(direction == left) {
					xC -= 1;
					wasLeft = true;
				}
				else if(direction == right) {
					xC += 1;
					wasRight = true;
				}
				else if(direction  == up) {
					yC -= 1;
					wasUp = true;
				}
				
				if(!wasUp) {
					try {
						if(Screen.room.block[yC+1][xC].groundID == Value.pathOpen) {
							direction = down;
						}
					} catch (Exception e) {
						
					}
				}
				if(!wasRight) {
					try {
						if(Screen.room.block[yC][xC-1].groundID == Value.pathOpen) {
							direction = left;
						}
					} catch (Exception e) {
						
					}
				}
				if(!wasLeft) {
					try {
						if(Screen.room.block[yC][xC+1].groundID == Value.pathOpen) {
							direction = right;
						}	
					} catch (Exception e) {
						
					}
				}	
				if(!wasDown) {
					try {
						if(Screen.room.block[yC-1][xC].groundID == Value.pathOpen) {
							direction = up;
						}
					} catch (Exception e) {
						
					} 
				}
				
				if(Screen.room.block[yC][xC].airID == Value.airCastle) {
					deleteEnemy();
					looseHealth();
				}
				
				wasUp = false;
				wasDown = false;
				wasRight = false;
				wasLeft = false;
				
				enemyWalk = 0;
			}
			
			walkFrame = 0;
			
		}
		else {
			walkFrame += 1;
		}
	}
	
	public void draw(Graphics g) {
		if(inGame && isDead == false) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, enemySize, enemySize);
		}
	}
}
