package snake.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import snake.Logic.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Stats {

	public static final Dimension prefSize = new Dimension(B_WIDTH, B_HEIGHT);
	public Snake snake;
	private Apple apple;

	private String status;
	private int delay;
	
	Image backgroundImage;
	private final String path = "src\\snake\\UI\\Images\\";
	private String[] imageNum = new String[] {"bg_1.png", "bg_2.png", "bg_3.png"};

	Direction dir;

	private Timer t;

	// boolean variables for game Control
	public boolean gameOver;
	private boolean paused;

	public GamePanel() {

		setBackground(1);
		
		setPreferredSize(prefSize);
		setFocusable(true);

		delay = 70;
		status = "";

		initKeyListener();
		initGame();
		startGame();
	}

	public void setBackground(int number) {
		
		String directory = path + imageNum[number];
		
		ImageIcon bgIcon = new ImageIcon(directory);
		backgroundImage = bgIcon.getImage();
	}
	
	private void initGame() {

		snake = new Snake();
		apple = Apple.spawnApple();

		t = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doGame();
			}
		});
	}

	private void initKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					if (!gameOver && snake.getDirection() != Direction.UP)
						snake.setDirection(Direction.DOWN);
					break;
				case KeyEvent.VK_UP:
					if (!gameOver && snake.getDirection() != Direction.DOWN)
						snake.setDirection(Direction.UP);
					break;
				case KeyEvent.VK_RIGHT:
					if (!gameOver && snake.getDirection() != Direction.LEFT)
						snake.setDirection(Direction.RIGHT);
					break;
				case KeyEvent.VK_LEFT:
					if (!gameOver && snake.getDirection() != Direction.RIGHT)
						snake.setDirection(Direction.LEFT);
					break;
				case KeyEvent.VK_SPACE:
					if (!gameOver && !paused)
						pauseGame();
					else if (!gameOver && paused)
						continueGame();
					repaint();
					break;

				case KeyEvent.VK_R:
					if (gameOver) {
						restartGame();
					}
					break;
				case KeyEvent.VK_E:
					if (gameOver) {
						endGame();
					}
					
					// Q for quit only for debugging issues
				case KeyEvent.VK_Q:
						endGame();

				}
			}
		});
	}

	private void doGame() {

			snake.moveSnake();
			checkCollision();
			checkApple();

			repaint();
	}

	public void startGame() {
		status = "Running...";
		t.start();
	}

	public void pauseGame() {
		if (!gameOver && !paused) {
			paused = true;
			status = "Game is paused!";
			repaint();
			t.stop();
		}
	}

	public void continueGame() {
		if (!isGameOver()) {
			paused = false;
			status = "Running...";
			t.start();
		}
	}

	public void restartGame() {
		setGameOver(false);
		status = "Running...";
		snake.initSnake();
		apple = Apple.spawnApple();
		startGame();
	}

	private void endGame() {
		System.exit(0);
	}

	private void checkApple() {

		Point p = snake.get(0);

		if (p.getX() == apple.getApple_x() && p.getY() == apple.getApple_y()) {
			snake.add(new Point(snake.get(0)));
			apple = Apple.spawnApple();

		}

	}

	private void checkCollision() {

		Point head = snake.get(0);

		if (head.getX() < 0 || head.getX() >= (B_WIDTH) || head.getY() < 0 || head.getY() >= (B_H_USEABLE )) {
			setGameOver(true);
			status = "Collision with the wall!";
			repaint();
			t.stop();

		} else {

			for (Point p : snake) {

				if (head.getX() == p.getX() && head.getY() == p.getY() && p != head) {
					setGameOver(true);
					status = "Collision with the Snake's body!";
					repaint();
					t.stop();
				}
			}
		}

	}

	/*
	 * Painting the games components
	 */

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		setSize(B_WIDTH, B_HEIGHT);
		
		setBackground(Color.BLACK);
		g.drawImage(backgroundImage, 0, 0, this);
		
		
		// Part of drawing the message are in a different color
		// Need to rework as the size of the lower box is hardcoded
		// Need to implement a way of changing the overall board size together with this grey box 
		g.setColor(new Color(34,36,40));
		g.fillRect(0, 480, 600, 120);

		drawStatus(g);

		for (Point p : snake) {
			if (p.equals(snake.get(0))) {
				g.drawImage(snake.getHeadIcon(), (int)p.getX(), (int) p.getY(), this);
			}
			else
				g.drawImage(snake.getBodyIcon(), (int)p.getX(), (int) p.getY(), this);
		}

		g.drawImage(apple.getAppleImg(), apple.getApple_x(), apple.getApple_y(), this);

		// Draw the line for the Message area
		g.setColor(Color.RED);
		g.drawLine(0, B_HEIGHT - MESSAGE_AREA_SIZE, B_WIDTH, B_HEIGHT - MESSAGE_AREA_SIZE);

	}

	private void drawStatus(Graphics g) {

		// Draw status bar
		g.setColor(Color.WHITE);
		g.drawString("Status: ", 10, MESSAGE_AREA + 2 * BLOCK_SIZE);

		g.setColor(Color.RED);
		g.drawString(status, 55, MESSAGE_AREA + 20);

		// Draw Snake Lenght
		g.setColor(Color.WHITE);
		g.drawString("Current Lenght: " + snake.getLength(), 10, MESSAGE_AREA + 4 * BLOCK_SIZE);

		if (gameOver) {
			g.setColor(Color.ORANGE);
			g.drawString("Game Over!", B_W_CENTER - g.getFontMetrics().stringWidth("Game Over!") / 2,
					MESSAGE_AREA + 6 * BLOCK_SIZE);

			g.drawString("Press 'R' to Restart Game  |  Press 'E' to Exit",
					B_W_CENTER - g.getFontMetrics().stringWidth("Press 'R' to Restart Game  |  Press 'E' to Exit") / 2,
					MESSAGE_AREA + 8 * BLOCK_SIZE);
		}

	}

	/*
	 * Getter & Setter
	 */

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public String getStatus() {
		return status;
	}

	public int getScore() {
		return snake.getLength();
	}
	
	public void setDelay(int delay) {
		if(delay > 0)
			this.delay = delay;
			t.setDelay(this.delay);
	}

}
