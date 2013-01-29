import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Screen extends JPanel implements Runnable {
	
	public Thread GameLoop = new Thread(this);
	
	public static int myWidth, myHeight;
	
	public static int myHealth = 100, myCoins = 250,  myWavesRemaining;
	
	public static boolean isFirst = true;
	
	
	public static Point mse = new Point(0, 0);
	
	public static Room room;	
	public static Save save;
	public static Shop shop;
	public static Menu menu;
	
	public static Enemy[] enemy = new Enemy[10];
	
	public Screen(Frame frame) {
		
		frame.addMouseListener(new KeyHandler());
		frame.addMouseMotionListener(new KeyHandler());
		
		GameLoop.start();
	}
	
	public void paintComponent(Graphics g) {
		
		if(isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			
			define();
			isFirst = false;
		}
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		room.draw(g);
		shop.draw(g);
		menu.draw(g);
		
		for(int i = 0; i < enemy.length; i++) {
			if(enemy[i].inGame) {
				enemy[i].draw(g);
			}
		}
	}
	
	public void define() {
		
		room = new Room();
		save = new Save();
		shop = new Shop();
		menu = new Menu();
		
		save.loadSave(new File("Save/Mission1.tdgame"));
		
		for(int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy();
		}
	}
	
	public int spawnTime = 1000, spawnFrame = 0;    //spawnFrame -> spawnTime. When spawnFrame == spawnTime, enemySpawner is called.
	public void enemySpawner() {
		if(spawnFrame >= spawnTime) {
			for(int i = 0; i < enemy.length; i++) {
				if(!enemy[i].inGame) {
					enemy[i].spawnEnemy(0);
					break;
				}
			}
			spawnFrame = 0;
		}
		else {
			spawnFrame += 1;
		}
	}
		
	public void run() {
		while(true) {
			if(!isFirst) {
				room.physic();
				enemySpawner();
				for(int i = 0; i < enemy.length; i++) {
					if(enemy[i].inGame && enemy[i].isDead == false) {
						enemy[i].physic();
					}
				}
			}
			repaint();
			try {
				Thread.sleep(5);
			} catch(Exception e) {				
			}
		}
	}
}
