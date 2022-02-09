package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import configuration.GameConfiguration;
import engine.Camera;
import engine.Mouse;
import engine.Position;
import engine.building.City;
import engine.map.Map;
import engine.map.Tile;
import engine.process.GameBuilder;
import engine.unit.Unit;
import engine.process.EntitiesManager;

public class MainGui extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

	private GameDisplay gameDisplay;

	private Map map;

	private EntitiesManager manager;

	private Camera camera;

	private Mouse mouse;

	public MainGui() {
		super("Game");
		init();
	}

	public void init() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		mouse = new Mouse();
		camera = new Camera(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

		MouseControls mouseControls = new MouseControls();
		MouseMotion mouseMotion = new MouseMotion();

		map = GameBuilder.buildMap();

		manager = new EntitiesManager(map);

		GameBuilder.buildInitUnit(map, manager);

		gameDisplay = new GameDisplay(map, camera, mouse, manager);
		gameDisplay.addMouseListener(mouseControls);
		gameDisplay.addMouseMotionListener(mouseMotion);
		gameDisplay.setPreferredSize(preferredSize);
		contentPane.add(gameDisplay, BorderLayout.CENTER);

		pack();
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
			manager.update();
			camera.update();
			gameDisplay.repaint();
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

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// left click
			if (e.getButton() == 1) {
				int x = (e.getX() + camera.getX()) / GameConfiguration.TILE_SIZE;
				int y = (e.getY() + camera.getY()) / GameConfiguration.TILE_SIZE;

				manager.unselectCity();
				manager.unselectUnit();

				Boolean entitySelected = false;

				//System.out.println("Tile [" + x + "," + y + "]");

				for (Unit unit : manager.getUnits()) {
					Position pos = unit.getPosition();
					if (pos.getX() == x && pos.getY() == y) {
						manager.selectUnit(unit);
						entitySelected = true;
						break;
					}
				}
				if (entitySelected == false) {
					for (City city : manager.getCities()) {
						Position pos = city.getPosition();
						if (pos.getX() == x && pos.getY() == y) {
							manager.selectCity(city);
							entitySelected = true;
							break;
						}
					}
				}
			}
			else if (e.getButton() == 3) {
				int x = (e.getX() + camera.getX()) / GameConfiguration.TILE_SIZE;
				int y = (e.getY() + camera.getY()) / GameConfiguration.TILE_SIZE;
				System.out.println("Move Tile [" + x + "," + y + "] " + manager.getSelectedUnit());
				if(manager.getSelectedUnit() != null) {
					manager.getSelectedUnit().calculateSpeed(new Position(x, y));
				}
			}

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
			int x = e.getX();
			int y = e.getY();
			mouse.setX(x);
			mouse.setY(y);

			if (x < camera.getRectX() || x > camera.getRectX() + camera.getRectW() || y < camera.getRectY() || y > camera.getRectY() + camera.getRectH()) {
				double angle = Math.atan2(y - GameConfiguration.WINDOW_HEIGHT / 2, x - GameConfiguration.WINDOW_WIDTH / 2);
				camera.move((int) (20 * Math.cos(angle)), (int) (20 * Math.sin(angle)));
			} else {
				camera.move(0, 0);
			}

		}

	}

	public Map getMap() {
		return map;
	}

}
