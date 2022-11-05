package main;

public class checkCollision {
	boolean collision;
	GamePanel gp;
	
	public checkCollision(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkCollision() {
		
		for(int i = 0; i < gp.enemies.size(); i++) {
			Enemy e = gp.enemies.get(i);
			if(gp.player.x < e.x + 50 && gp.player.x + 50 > e.x && gp.player.y < e.y + 50 && gp.player.y + 50 > e.y) {
				collision = true;
			}
			else {
				collision = false;
			}
		}
		
	}
}
