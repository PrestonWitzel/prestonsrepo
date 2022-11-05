package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy {
	private GamePanel gp;
	int x;
	int y;
	int speed;
	int xVelocity;
	int yVelocity;
	int health;
	boolean deleted;
	Color color;
	
	public Enemy(GamePanel gp) {
		this.gp = gp;
		setDefaultValues();
	}
	
	public void setDefaultValues( ) {
		x = 100;
		y = 0;
		speed = 4;
		xVelocity = 2;
		yVelocity = 2;
		health = 10;
		color = Color.RED;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillOval(x, y, 50, 50);
	}
	
	public void update() {
		if(health > 0) {
			if(x < 0 || (x+50) == 500) {
			xVelocity = xVelocity*-1;
			}
		
			x+= xVelocity;
		
			if(y < 0 || (y+50) == 500) {
				yVelocity = yVelocity*-1;
			}
		
			y+= yVelocity;
		}
		else {
			deleted = true;
		}
		
	}
	
	public boolean getDeleted() {
		return deleted;
	}
}
