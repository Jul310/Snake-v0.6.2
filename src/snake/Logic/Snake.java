package snake.Logic;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Snake extends ArrayList<Point> implements Stats {

	// Enum controlling the head of the snake to move in the right direction
	Direction dir;
	
	private int initialLenght;
	private Image headIcon;
	private Image bodyIcon;

	public Snake() {

		initialLenght = 5;
		loadImage();
		initSnake();
	}

	public void initSnake() {
		
		dir = Direction.RIGHT;
		
		clear();
		int xStart = B_WIDTH / 2;
		int yStart = B_HEIGHT / 2;

		// Paint the Start situation of the Snake
		for (int i = 0; i < initialLenght; i++) {
			add(new Point(xStart, yStart));
			xStart -= BLOCK_SIZE;
		}
	}
	
	private void loadImage() {
		ImageIcon headI = new ImageIcon("src\\snake\\UI\\Images\\head.png");
		ImageIcon bodyI = new ImageIcon("src\\snake\\UI\\Images\\dot.png");
		
		headIcon = headI.getImage();
		bodyIcon = bodyI.getImage();
	}


	public void moveSnake() {

		switch (dir) {
		case UP:
			moveSnakeUP();
			break;
		case DOWN:
			moveSnakeDOWN();
			break;
		case LEFT:
			moveSnakeLEFT();
			break;
		case RIGHT:
			moveSnakeRIGHT();
			break;
		default:
			break;
		}
	}

	private void moveSnakeUP() {
		for (int i = size() - 1; i >= 0; i--) {
			if (i != 0)
				get(i).setLocation(get(i - 1));
			else
				get(i).translate(0, -BLOCK_SIZE);
		}
	}

	private void moveSnakeDOWN() {
		for (int i = size() - 1; i >= 0; i--) {
			if (i != 0)
				get(i).setLocation(get(i - 1));
			else
				get(i).translate(0, BLOCK_SIZE);
		}
	}

	private void moveSnakeRIGHT() {
		for (int i = size() - 1; i >= 0; i--) {
			if (i != 0)
				get(i).setLocation(get(i - 1));
			else
				get(i).translate(BLOCK_SIZE, 0);
		}
	}

	private void moveSnakeLEFT() {
		for (int i = size() - 1; i >= 0; i--) {
			if (i != 0)
				get(i).setLocation(get(i - 1));
			else
				get(i).translate(-BLOCK_SIZE, 0);
		}
	}
	
	public Direction getDirection() {
		return dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

	public int getLength() {
		return this.size();
	}

	public Image getHeadIcon() {
		return headIcon;
	}
	
	public Image getBodyIcon() {
		return bodyIcon;
	}
}
