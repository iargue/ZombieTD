import javax.swing.*;
import java.awt.*;

public class Room {			//All blocks on the ground.
	
	public int worldWidth = 6;
	public int worldHeight = 10;
	public int blockSize = 54;
	
	public Block[][] block;
	
	public Room() {
		define();
	}
	
	public void define() {
		block = new Block[worldHeight][worldWidth];
		
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x] = new Block((Screen.myWidth/2) - ((worldWidth*blockSize)/2) + (x * blockSize), y * blockSize, blockSize, blockSize, Value.groundOpen, Value.airOpen);
			}
		}
	}
	
	public void physic() {
		for(int y=0;y<block.length;y++) {
			for(int x=0;x<block[0].length;x++) {
				
				block[y][x].physic();
			}
		}
	}
	
	public void draw(Graphics g) {
		
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x].draw(g);
			}
		}
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x].fight(g);
			}
		}

	}
}
