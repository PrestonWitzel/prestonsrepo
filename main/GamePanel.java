package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	Thread gameThread;
	KeyHandler kh = new KeyHandler();
	Player player = new Player(this, kh);
	long currentTime;
	long waveTimer;
	long waveDelay = 5000;
	public static int screenWidth = 500;
	public static int screenHeight = 500;
	int wave = 1;
	boolean addEnemies;

	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setFocusable(true);
		this.addKeyListener(kh);
		startGame();
	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
		enemies.add(new Enemy(this));
		currentTime = System.nanoTime();
	}

	@Override
	public void run() {
		while (gameThread != null) {

			if (player.health <= 0) {
				System.out.println("Health: 0");
			}

			repaint();
			update();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, 500, 500);
		player.draw(g2);

		for (Bullet b : bullets) {
			b.draw(g2);
		}

		for (Enemy e : enemies) {
			e.draw(g2);
		}
		g2.dispose();
	}

	public void update() {

		waveTimer = System.nanoTime();
		long differenceTime = waveTimer - currentTime;
		differenceTime /= 1000000;
		if (differenceTime >= waveDelay && enemies.size() == 0) {
			wave++;
			addEnemies = true;
			waveTimer = System.nanoTime();
		}

		if (addEnemies) {
			if (wave == 1) {
				for (int i = 0; i < 3; i++) {
					enemies.add(new Enemy(this));
				}
			}

			if (wave == 2) {
				for (int i = 0; i < 5; i++) {
					enemies.add(new Enemy(this));
				}
			}

			addEnemies = false;
		}

		if (player.health > 0) {
			System.out.println("Health: " + player.health);
		}

		player.update();
		for (Bullet b : bullets) {
			b.update();
		}
		for (Enemy e : enemies) {
			e.update();
		}
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);

			if (e.getDeleted() == true) {
				enemies.remove(i);
				i--;
			}
		}
		bullets.removeIf(b -> b.getDeleted());
		System.out.println(enemies.size());
	}
}
