package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import configuration.GameConfiguration;
import engine.Camera;
import engine.Mouse;
import engine.Position;
import engine.building.City;
import engine.map.Map;
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

				// System.out.println("Tile [" + x + "," + y + "]");

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
				if (MouseMotion.isDragged) {
					MouseMotion.isDragged = false;
					MouseMotion.init = false;
					if(manager.getSelectedUnit() != null && manager.getSelectedUnit().getPath() != null && !manager.getSelectedUnit().getPath().isEmpty()) {
						manager.getSelectedUnit().calculateSpeed(manager.getSelectedUnit().getPath().get(0));
					}
				}
			}
			
			/*
			 * else if (e.getButton() == 3) { int x = (e.getX() + camera.getX()) /
			 * GameConfiguration.TILE_SIZE; int y = (e.getY() + camera.getY()) /
			 * GameConfiguration.TILE_SIZE; System.out.println("Move Tile [" + x + "," + y +
			 * "] " + manager.getSelectedUnit()); if(manager.getSelectedUnit() != null) {
			 * manager.getSelectedUnit().calculateSpeed(new Position(x, y)); } }
			 */

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

		private Position removedPath;
		private Position addedPath;
		private static Boolean isDragged = false;
		private static Boolean init = false;

		@Override
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				isDragged = true;
				int x = (e.getX() + camera.getX()) / GameConfiguration.TILE_SIZE;
				int y = (e.getY() + camera.getY()) / GameConfiguration.TILE_SIZE;
				Position position = new Position(x, y);
				Unit unit = manager.getSelectedUnit();
				if (unit != null) {
					List<Position> path = unit.getPath();
					if (path.isEmpty()) {
						List<Position> possibleStartPositions = new ArrayList<>();
						for (int i = -1; i <= 1; i++) {
							for (int j = -1; j <= 1; j++) {
								if ((i != 0 && j != 0) || (i == 0 && j != 0) || (i != 0 && j == 0)) {
									 //System.out.println((unit.getPosition().getX() + i) + "," + (unit.getPosition().getY() + j));
									possibleStartPositions.add(new Position(unit.getPosition().getX() + i, unit.getPosition().getY() + j));
								}
							}
						}
						for (Position p : possibleStartPositions) {
							if (position.equals(p)) {
								//System.out.println("init path");
								init = true;
								unit.addPath(position);
							}
						}

					}
					if (path.size() > 2) {
						if (path.get(path.size() - 2).equals(position) && !addedPath.equals(position)) {
							//System.out.println("remove path");
							removedPath = path.get(path.size() - 1);
							unit.removeLastPath();
						}
					}
					if (path.size() == 2 && position.equals(path.get(0))) {
						System.out.println("remove path");
						removedPath = path.get(path.size() - 1);
						unit.removeLastPath();
					}
					if (removedPath != null && init) {
						if (!path.get(path.size() - 1).equals(position)) {
							//System.out.println("add path");
							unit.addPath(position);
							addedPath = position;
						}
					} else if (init && !path.get(path.size() - 1).equals(position)) {
						//System.out.println("add path");
						unit.addPath(position);
						addedPath = position;
					}
				}
			}

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
