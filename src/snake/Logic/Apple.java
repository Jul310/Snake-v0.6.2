package snake.Logic;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;


public class Apple implements Stats{
	
	private final Random r; 
	private int apple_x;
	private int apple_y; 
	private ImageIcon appleImg;
	private Image apple;
	
	public Apple() {
		
		r = new Random(); 
		apple_x = r.nextInt((B_WIDTH/BLOCK_SIZE)-1)*BLOCK_SIZE  ;
		apple_y = r.nextInt((B_H_USEABLE/BLOCK_SIZE)-1)*BLOCK_SIZE ;
		appleImg = new ImageIcon("src\\snake\\UI\\Images\\apple.png");
		apple = appleImg.getImage();
	}

	public int getApple_x() {
		return apple_x;
	}

	public void setApple_x(int apple_x) {
		this.apple_x = apple_x;
	}

	public int getApple_y() {
		return apple_y;
	}

	public void setApple_y(int apple_y) {
		this.apple_y = apple_y;
	}
	
	public Image getAppleImg() {
		return this.apple;
	}

	
	public static Apple spawnApple() {
		return new Apple();
	}
	

}
