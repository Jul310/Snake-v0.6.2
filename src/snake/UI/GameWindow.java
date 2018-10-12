package snake.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	private WelcomeScreen ws;
	private GamePanel snakePanel;
	public static boolean startGame = false;

	public GameWindow() {

		ws = new WelcomeScreen();
		// snakePanel = new GamePanel();

		registerWindowListener();
		createMenu();

		add(ws);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Snake v0.6 beta");
		setLocation(10, 10);
		setResizable(false);

		setVisible(true);

		enterGame();
	}
	
	
	
	private void enterGame() {
		// Wait until game should start
		while (!startGame) {
			try {
				Thread.sleep(3);
			} catch (Exception e) {}
		}
		
		getContentPane().removeAll();
		snakePanel = new GamePanel();
		add(snakePanel);
		snakePanel.requestFocus();
		pack();
	}

	private void createMenu() {

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		JMenu gameMenu = new JMenu("Game");
		JMenu prefMenu = new JMenu("Preferences");

		menuBar.add(fileMenu);
		menuBar.add(gameMenu);
		menuBar.add(prefMenu);

		this.addFileMenuItems(fileMenu);
		this.addGameMenuItems(gameMenu);
		this.addPrefMenuItems(prefMenu);
	}

	private void addFileMenuItems(JMenu fileMenu) {
		JMenuItem quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		quitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void addGameMenuItems(JMenu gameMenu) {

		JMenuItem pauseItem = new JMenuItem("Pause");
		gameMenu.add(pauseItem);
		pauseItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.pauseGame();
			}
		});

		JMenuItem continueItem = new JMenuItem("Continue");
		gameMenu.add(continueItem);
		continueItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.continueGame();
			}
		});

		gameMenu.addSeparator();
		JMenuItem restartItem = new JMenuItem("Restart");
		gameMenu.add(restartItem);
		restartItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.restartGame();
			}
		});
	}

	private void addPrefMenuItems(JMenu prefMenu) {

		JMenu submenu = new JMenu("Choose Background");
		prefMenu.add(submenu);

		JMenuItem menuItem = new JMenuItem("Snake Skin");
		submenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.setBackground(0);
				repaint();
			}
		});

		menuItem = new JMenuItem("No Idea");
		submenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.setBackground(1);
				repaint();
			}
		});

		menuItem = new JMenuItem("Reptile");
		submenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.setBackground(2);
				repaint();
				;
			}
		});

		prefMenu.addSeparator();

		JMenu speedMenu = new JMenu("Choose Speed");
		prefMenu.add(speedMenu);

		JMenuItem speed = new JMenuItem("Easy");
		speedMenu.add(speed);
		speed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.setDelay(150);
			}
		});

		speed = new JMenuItem("Normal");
		speedMenu.add(speed);
		speed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.setDelay(70);
			}
		});

		speed = new JMenuItem("Hard");
		speedMenu.add(speed);
		speed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snakePanel.setDelay(30);
			}
		});
	}

	private void registerWindowListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowDeactivated(WindowEvent e) {
				// snakePanel.pauseGame();
			}

			public void windowActivated(WindowEvent e) {
				// snakePanel.continueGame();
			}
		});
	}

	public void setStartGame(boolean b) {
		startGame = b;
	}

}
