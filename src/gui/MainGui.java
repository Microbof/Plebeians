package gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import configuration.GameConfiguration;

public class MainGui extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

	
	/**
	 * Constructor used to set all gui elements and to create the window
	 */
	public MainGui() {
		super("Game");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		MouseControls mouseControls = new MouseControls();
		MouseMotion mouseMotion = new MouseMotion();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setPreferredSize(preferredSize);

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		MainGui n = new MainGui();
		Thread gameThread = new Thread(n);
		gameThread.start();
	}

	private class MouseControls implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	private class MouseMotion implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
