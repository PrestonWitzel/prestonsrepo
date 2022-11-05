package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	private GamePanel gp;
	private KeyHandler kh;
	int x;
	int y;
	int speed;
	int health;
	long shootingTimer = System.nanoTime();
	long collisionTimer = System.nanoTime();
	long shootingDelay = 200;
	long collisionDelay = 2000;
	Color color;
	
	checkCollision cc;
	
	public Player(GamePanel gp, KeyHandler kh) {
		this.gp = gp;
		this.kh = kh;
		setDefaultValues();
	}
	
	public void setDefaultValues( ) {
		x = 0;
		y = 0;
		speed = 3;
		health = 10;
		cc = new checkCollision(gp);
		color = Color.WHITE;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillOval(x, y, 50, 50);
	}
	
	public void update() {
		if(health > 0) {
			
			cc.checkCollision();

				
				
			if(kh.down == true && y != (500 - 50)) {
				y += speed;
			}
			if(kh.left == true && x != 0) {
				x -= speed;
			}
			if(kh.right == true && x != (500 - 50)) {
				x += speed;
			}
			if(kh.up == true && y != 0) {
				y -= speed;
			}
		
			if(kh.shooting) {
				long currentTime = System.nanoTime();
				long differenceTime = currentTime - shootingTimer;
				differenceTime /= 1000000;
				if(differenceTime >= shootingDelay) {
					GamePanel.bullets.add(new Bullet(gp));
					shootingTimer = System.nanoTime();
				}
			}
		
			if(cc.collision == true) {
				color = Color.red;
				long currentTime = System.nanoTime();
				long differenceTime = currentTime - collisionTimer;
				differenceTime /= 1000000;
				if(differenceTime >= collisionDelay) {
					health--;
					
					collisionTimer = System.nanoTime();
				}
			}
			
			if(!cc.collision) {
				color = Color.white;
			}
		}
		
		if(health <= 0) {
			color = Color.BLACK;
		}
		
	}
}
