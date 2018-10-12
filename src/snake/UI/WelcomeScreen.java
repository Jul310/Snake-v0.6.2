package snake.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import snake.Logic.Stats;

@SuppressWarnings("serial")
public class WelcomeScreen extends JPanel implements Stats, ActionListener{
	
	public static final Dimension prefSize = new Dimension(B_WIDTH, B_HEIGHT);
	private ImageIcon background;
	public JButton startNormalButton; 
	
	public WelcomeScreen() {
		
		setPreferredSize(prefSize);
		setFocusable(true);
		
		background = new ImageIcon("src\\snake\\UI\\Images\\wallpaper.png");
		
		initGameControlButtons();
		
		add(startNormalButton);
	}
	
	
	private void initGameControlButtons() {
		
		startNormalButton = new JButton("Start Single Player Game");
		startNormalButton.addActionListener(this);
		startNormalButton.setActionCommand("StartSingle");
		startNormalButton.setBackground(new Color(100, 0, 0, 0));
		startNormalButton.setSize(100, 50);
		
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		background.paintIcon(null, g2d, -500, -100);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "StartSingle": 
			GameWindow.startGame = true;
			break;
		}
	}

}
