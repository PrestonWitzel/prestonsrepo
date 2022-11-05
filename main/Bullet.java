package main;

import java.awt.Graphics2D;

public class Bullet {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int speed;
	private GamePanel gp;
	private boolean collision;
	private boolean deleted;

	public Bullet(GamePanel gp) {
		this.gp = gp;
		this.x = (gp.player.x + 50) - 25;
		this.y = gp.player.y;
		setDefaultValues();
	}

	public void setDefaultValues() {
		speed = 10;
		collision = false;
		deleted = false;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void update() {

		for (int i = 0; i < gp.enemies.size(); i++) {
			Enemy e = gp.enemies.get(i);
			if (collision == true && e.health > 0) {
				e.health--;
				deleted = true;
			}

			if (x < e.x + 50 && x + 50 > e.x && y < e.y + 50 && y + 50 > e.y) {
				if (e.health > 0) {
					collision = true;
				}

			} else {
				collision = false;
			}
		}

		y -= speed;

	}

	public void draw(Graphics2D g2) {
		g2.fillRect(x, y, 10, 10);
	}
}
