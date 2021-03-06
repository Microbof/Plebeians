package gui;

import configuration.GameConfiguration;
import engine.Camera;
import engine.Mouse;
import engine.Player;
import engine.Position;
import engine.building.City;
import engine.map.Map;
import engine.map.Tile;
import engine.process.EntitiesManager;
import engine.process.GameBuilder;
import engine.unit.Unit;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainGui extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH,
			GameConfiguration.WINDOW_HEIGHT);

	private GameDisplay gameDisplay;

	private Map map;

	private EntitiesManager manager;

	private Camera camera;

	private Mouse mouse;

	public static Clip music;

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

		KeyControls keyboardListener = new KeyControls();
		JTextField textField = new JTextField();
		textField.addKeyListener(keyboardListener);
		this.getContentPane().add(textField);

		map = GameBuilder.buildMap();

		manager = new EntitiesManager(map);

		GameBuilder.buildInitUnit(map, manager);
		manager.gameCreated = true;
		System.out.println("game created");

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
			if(manager.blueWin) {
				System.out.println("blue win 2");
				gameDisplay.setVisible(false);
				createWinPannel(manager.getPlayers().get(0));
			}
			if(manager.redWin) {
				System.out.println("red win 2");
				gameDisplay.setVisible(false);
				createWinPannel(manager.getPlayers().get(1));
			}
		}
	}
	
	public void createWinPannel(Player winner) {
		JPanel panel = new JPanel(new GridLayout(2,1));
		JLabel gameTitle = new JLabel("<html><h1>" + winner.getName() + " a gagn? !</h1></html>");
		gameTitle.setAlignmentX(SwingConstants.CENTER);
		gameTitle.setAlignmentY(SwingConstants.CENTER);
		gameTitle.setHorizontalAlignment(JLabel.CENTER);
		gameTitle.setVerticalAlignment(JLabel.CENTER);
		JButton quitButton = new JButton(new ExitGameButton("Quitter le jeu"));
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setMargin(new Insets(10, 20, 10, 20));
		panel.add(gameTitle);
		panel.add(quitButton);
		getContentPane().add(panel);
		panel.setVisible(true);
	}
	
	private class ExitGameButton extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public ExitGameButton(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		MainGui n = new MainGui();
		Thread gameThread = new Thread(n);
		gameThread.start();
		try {
			music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(new File("./res/titlescreen.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		music.start();
		music.loop(50);
	}

	private class KeyControls implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {
			char keyChar = event.getKeyChar();
			switch (keyChar) {
			case 'q':
				camera.move(-15, camera.getSpeed().getVy());
				break;

			case 'd':
				camera.move(15, camera.getSpeed().getVy());
				break;

			case 'z':
				camera.move(camera.getSpeed().getVx(), -15);
				break;

			case 's':
				camera.move(camera.getSpeed().getVx(), 15);
				break;
			default:
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent event) {
			char keyChar = event.getKeyChar();
			switch (keyChar) {
			case 'q':
				camera.move(0, camera.getSpeed().getVy());
				break;

			case 'd':
				camera.move(0, camera.getSpeed().getVy());
				break;

			case 'z':
				camera.move(camera.getSpeed().getVx(), 0);
				break;

			case 's':
				camera.move(camera.getSpeed().getVx(), 0);
				break;

			default:
				break;
			}
		}
	}

	private class MouseControls implements MouseListener {

		public boolean isNextTo(Position position, Position position2) {
			boolean isNextToUnit = false;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if ((i != 0 && j != 0) || (i == 0 && j != 0) || (i != 0 && j == 0)) {
						if (position2.equals(new Position(position.getX() + i, position.getY() + j))) {
							return true;
						}
					}
				}
			}
			return isNextToUnit;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = (e.getX() + camera.getX()) / GameConfiguration.TILE_SIZE;
			int y = (e.getY() + camera.getY()) / GameConfiguration.TILE_SIZE;
			if (e.getButton() == 3) {
				Unit selectedUnit = manager.getSelectedUnit();
				Unit enemy = null;
				City enemyCity = null;
				Position pos = new Position(x, y);
				if (selectedUnit != null) {
					List<Unit> units = manager.getUnits();
					for (Unit unit : units) {
						if (unit.getPosition().equals(pos) && unit.getPlayer() != manager.getCurrentPlayer()
								&& selectedUnit.getAp() > 0) {
							enemy = unit;
						}
					}
					if (enemy != null && isNextTo(selectedUnit.getPosition(), enemy.getPosition())) {
						selectedUnit.attack(enemy);
					}
					if(enemy == null) {
						List<City> cities = manager.getCities();
						for (City city : cities) {
							if(city.getPosition().equals(pos) && city.getPlayer() != manager.getCurrentPlayer() && selectedUnit.getAp() > 0) {
								enemyCity = city;
							}
						}
						if (enemyCity != null && isNextTo(selectedUnit.getPosition(), enemyCity.getPosition())) {
							selectedUnit.attack(enemyCity);
						}
					}
				}
			}
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

				Tile tile = map.getTile(x, y);
				System.out.println("tile : " + tile.getLine() + "," + tile.getColumn());
				if (tile.getUnit() != null) {
					System.out.println("unit : " + tile.getUnit().getDescription());
				}
				Boolean entitySelected = false;

				// System.out.println("Tile [" + x + "," + y + "]");

				for (Unit unit : manager.getUnits()) {
					Position pos = unit.getPosition();
					if (pos.getX() == x && pos.getY() == y) {
						manager.selectUnit(unit);
						entitySelected = true;
						gameDisplay.setDescriptionLabel("<html>" + manager.getSelectedUnit().getPlayer().getName()
								+ " | AP : " + manager.getSelectedUnit().getAp() + "/"
								+ manager.getSelectedUnit().getMaxAp() + " | HP : " + manager.getSelectedUnit().getHp()
								+ "/" + manager.getSelectedUnit().getHpMax() + " | Attaque : "
								+ manager.getSelectedUnit().getAttack() + " | D??fense : "
								+ manager.getSelectedUnit().getDefense() + "<br />"
								+ manager.getSelectedUnit().getDescription() + "</html>");
						if (manager.getSelectedUnit().getDescription() == "Unit?? combattante") {
							Clip clip;
							try {
								clip = AudioSystem.getClip();
								clip.open(AudioSystem.getAudioInputStream(new File("./res/fx/select_fighter.wav")));
								clip.start();
							} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (manager.getSelectedUnit().getDescription() == "Ouvriers") {
							try {
								Clip clip = AudioSystem.getClip();
								clip.open(AudioSystem.getAudioInputStream(new File("./res/fx/select_builder.wav")));
								clip.start();
							} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (manager.getSelectedCity().getName() == "le Bled Bleu" || manager.getSelectedCity().getName() == "le Bled Rouge") {
							try {
								Clip clip = AudioSystem.getClip();
								clip.open(AudioSystem.getAudioInputStream(new File("./res/fx/select_city.wav")));
								clip.start();
							} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						break;
					}
				}

				if (entitySelected == false) {
					for (City city : manager.getCities()) {
						Position pos = city.getPosition();
						if (pos.getX() == x && pos.getY() == y) {
							if (manager.getCurrentPlayer() == city.getPlayer() && city.getConstructWait() == 0) {
								city.setConstructWait(3);
							}
							manager.selectCity(city);
							entitySelected = true;
							gameDisplay.setDescriptionLabel(manager.getSelectedCity().getName() + " | "
									+ manager.getSelectedCity().getDescription() + " | HP : "
									+ manager.getSelectedCity().getHealth());
							break;
						}
					}
				}

			} else if (e.getButton() == 3) {
				if (MouseMotion.isDragged) {
					MouseMotion.isDragged = false;
					MouseMotion.init = false;
					if (manager.getSelectedUnit() != null && manager.getSelectedUnit().getPath() != null
							&& !manager.getSelectedUnit().getPath().isEmpty()) {
						// manager.getSelectedUnit().calculateSpeed(manager.getSelectedUnit().getPath().get(0));
						Unit selectedUnit = manager.getSelectedUnit();
						Tile tile = map.getTile(selectedUnit.getPosition().getX(), selectedUnit.getPosition().getY());
						tile.setUnit(null);
						tile = map.getTile(selectedUnit.getPath().get(selectedUnit.getPath().size() - 1).getX(),
								selectedUnit.getPath().get(selectedUnit.getPath().size() - 1).getY());
						tile.setUnit(selectedUnit);
						manager.getSelectedUnit().setPendingAction(true);
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

		public List<Position> getPossiblePosition(Position position) {
			List<Position> possibleStartPositions = new ArrayList<>();
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if ((i != 0 && j != 0) || (i == 0 && j != 0) || (i != 0 && j == 0)) {
						// System.out.println((unit.getPosition().getX() + i) + "," +
						// (unit.getPosition().getY() + j));
						possibleStartPositions.add(new Position(position.getX() + i, position.getY() + j));
					}
				}
			}
			return possibleStartPositions;
		}

		public boolean isNextTo(Position position, Position position2) {
			boolean isNextToUnit = false;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if ((i != 0 && j != 0) || (i == 0 && j != 0) || (i != 0 && j == 0)) {
						if (position2.equals(new Position(position.getX() + i, position.getY() + j))) {
							return true;
						}
					}
				}
			}
			return isNextToUnit;
		}

		public void moveUnit(int x, int y, EntitiesManager manager) {
			Position position = new Position(x, y);
			Unit unit = manager.getSelectedUnit();
			boolean biomeIsAccessible;
			biomeIsAccessible = map.getTile(y, x).getBiome().isAccessible();
			if (unit != null && unit.getPlayer().equals(manager.getCurrentPlayer()) && biomeIsAccessible) {
				List<Position> path = unit.getPath();
				if (path.isEmpty() && unit.getAp() > 0) {
					List<Position> possibleStartPositions = getPossiblePosition(unit.getPosition());
					for (Position p : possibleStartPositions) {
						if (position.equals(p) && !isEntityOnTile(position)) {
							// System.out.println("init path");
							init = true;
							unit.addPath(position);
						}
					}
				}
				if (path.size() == 1 && position.equals(unit.getPosition())) {
					path.clear();
					init = false;
				}
				if (path.size() > 2) {
					if (path.get(path.size() - 2).equals(position) && !addedPath.equals(position)) {
						// System.out.println("remove path");
						removedPath = path.get(path.size() - 1);
						unit.removeLastPath();
					}
				}
				if (path.size() == 2 && position.equals(path.get(0))) {
					// System.out.println("remove path");
					removedPath = path.get(path.size() - 1);
					unit.removeLastPath();
				}
				if (removedPath != null && init && path.size() < unit.getAp() && !isEntityOnTile(position)) {
					if (!path.get(path.size() - 1).equals(position) && isNextTo(position, path.get(path.size()-1))) {
						// System.out.println("add path");
						unit.addPath(position);
						addedPath = position;
					}
				} else if (init && !path.get(path.size() - 1).equals(position) && path.size() < unit.getAp() && !isEntityOnTile(position) && isNextTo(position, path.get(path.size()-1))) {
					// System.out.println("add path");
					unit.addPath(position);
					addedPath = position;
				}
			}
		}
		
		public boolean isEntityOnTile(Position pos) {
			List<Unit> units = manager.getUnits();
			for (Unit unit : units) {
				if (unit.getPosition().equals(pos)) {
					return true;
				}
			}
			for(City city : manager.getCities()) {
				if(city.getPosition().equals(pos)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				isDragged = true;
				int x = (e.getX() + camera.getX()) / GameConfiguration.TILE_SIZE;
				int y = (e.getY() + camera.getY()) / GameConfiguration.TILE_SIZE;
				if (x >= 0 && y >= 0 && x <= GameConfiguration.COLUMN_COUNT && y <= GameConfiguration.LINE_COUNT) {
					moveUnit(x, y, manager);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			mouse.setX(x);
			mouse.setY(y);

			if (x < camera.getRectX() || x > camera.getRectX() + camera.getRectW() || y < camera.getRectY()
					|| y > camera.getRectY() + camera.getRectH()) {
				double angle = Math.atan2(y - GameConfiguration.WINDOW_HEIGHT / 2,
						x - GameConfiguration.WINDOW_WIDTH / 2);
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
