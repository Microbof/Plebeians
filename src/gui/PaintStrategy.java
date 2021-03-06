package gui;

import configuration.GameConfiguration;
import engine.Camera;
import engine.Player;
import engine.Position;
import engine.building.Building;
import engine.building.City;
import engine.map.Map;
import engine.map.Minimap;
import engine.map.Tile;
import engine.process.EntitiesManager;
import engine.unit.Unit;
import engine.unit.UnitBuilder;
import engine.unit.UnitFighter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public class PaintStrategy {

	public PaintStrategy() {

	}

	public void paint(Map map, Camera camera, Graphics graphics) {

		Tile[][] tiles = map.getTiles();
		int tileSize = GameConfiguration.TILE_SIZE;
		int width = GameConfiguration.WINDOW_WIDTH;
		int height = GameConfiguration.WINDOW_HEIGHT;

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Tile tile = tiles[lineIndex][columnIndex];
				switch (tile.getBiome().getId()) {
				case 0: // plain
					graphics.drawImage(new ImageIcon("src/graphic/plain.png").getImage(), tile.getColumn() * tileSize - camera.getX(),
							tile.getLine() * tileSize - camera.getY(), tileSize, tileSize, null);
					break;
				case 1: // hill
					graphics.drawImage(new ImageIcon("src/graphic/hill.png").getImage(), tile.getColumn() * tileSize - camera.getX(),
							tile.getLine() * tileSize - camera.getY(), tileSize, tileSize, null);
					break;
				case 2: // forest
					graphics.drawImage(new ImageIcon("src/graphic/forest.png").getImage(), tile.getColumn() * tileSize - camera.getX(),
							tile.getLine() * tileSize - camera.getY(), tileSize, tileSize, null);
					break;
				case 3: // mountain
					graphics.drawImage(new ImageIcon("src/graphic/mountain.png").getImage(), tile.getColumn() * tileSize - camera.getX(),
							tile.getLine() * tileSize - camera.getY(), tileSize, tileSize, null);
					break;
				case 4: // sea
					graphics.drawImage(new ImageIcon("src/graphic/water.png").getImage(), tile.getColumn() * tileSize - camera.getX(),
							tile.getLine() * tileSize - camera.getY(), tileSize, tileSize, null);
					break;
				}
				if (tile.getColumn() * tileSize - camera.getX() + tileSize >= 0) {
					if (tile.getColumn() * tileSize - camera.getX() <= width) {
						if (tile.getLine() * tileSize - camera.getY() <= height) {
							if (tile.getLine() * tileSize - camera.getY() + tileSize >= 0) {
								if (tile.getOwner() != null) {
									graphics.setColor(tile.getOwner().getColor());
									graphics.fillRect(tile.getColumn() * tileSize - camera.getX(),
											tile.getLine() * tileSize - camera.getY(), tileSize, tileSize);
								} else {
									graphics.setColor(Color.green);
									graphics.drawRect(tile.getColumn() * tileSize - camera.getX(),
											tile.getLine() * tileSize - camera.getY(), tileSize, tileSize);
								}
							}
						}
					}
				}
			}
		}

	}

	public void paint(City city, Camera camera, Graphics graphics) {
		Position position = city.getPosition();
		int tileSize = GameConfiguration.TILE_SIZE;
		int width = GameConfiguration.WINDOW_WIDTH;
		int height = GameConfiguration.WINDOW_HEIGHT;
		Color player1 = GameConfiguration.PLAYER1COLOR;
		Color player2 = GameConfiguration.PLAYER2COLOR;

		int y = position.getY();
		int x = position.getX();

		if (x * tileSize - camera.getX() + tileSize >= 0) {
			if (x * tileSize - camera.getX() <= width) {
				if (y * tileSize - camera.getY() <= height) {
					if (y * tileSize - camera.getY() + tileSize >= 0) {
						graphics.setColor(city.getPlayer().getColor().darker());
						graphics.fillOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize,
								tileSize);
						if (city.isSelected()) {
							graphics.setColor(Color.BLACK);
							graphics.setFont(graphics.getFont().deriveFont(18f));
							int stringwidth = graphics.getFontMetrics().stringWidth("Builder");
							graphics.drawString("<" + city.getName() + ">",
									x * tileSize - ((tileSize - stringwidth) / 2) - camera.getX(),
									y * tileSize + 80 - camera.getY());
							graphics.setColor(Color.RED);
							graphics.drawOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize,
									tileSize);
						}
					}
				}
			}
		}

	}

	public void paint(UnitBuilder unit, Camera camera, Graphics graphics) {
		Position position = unit.getPosition();
		int tileSize = GameConfiguration.TILE_SIZE;
		int width = GameConfiguration.WINDOW_WIDTH;
		int height = GameConfiguration.WINDOW_HEIGHT;

		int y = position.getY();
		int x = position.getX();

		// paint path
		if (!unit.getPath().isEmpty()) {
			List<Position> path = unit.getPath();
			graphics.setColor(Color.RED);
			graphics.drawLine(unit.getPosition().getX() * tileSize - camera.getX() + (tileSize / 2),
					unit.getPosition().getY() * tileSize - camera.getY() + (tileSize / 2),
					path.get(0).getX() * tileSize - camera.getX() + (tileSize / 2),
					path.get(0).getY() * tileSize - camera.getY() + (tileSize / 2));

			for (int i = 0; i < path.size() - 1; i++) {
				if (path.get(i).getX() * tileSize - camera.getX() + tileSize >= 0) {
					if (path.get(i).getX() * tileSize - camera.getX() <= width) {
						if (path.get(i).getY() * tileSize - camera.getY() <= height) {
							if (path.get(i).getY() * tileSize - camera.getY() + tileSize >= 0) {
								graphics.setColor(Color.RED);
								graphics.drawLine(path.get(i).getX() * tileSize - camera.getX() + (tileSize / 2),
										path.get(i).getY() * tileSize - camera.getY() + (tileSize / 2),
										path.get(i + 1).getX() * tileSize - camera.getX() + (tileSize / 2),
										path.get(i + 1).getY() * tileSize - camera.getY() + (tileSize / 2));
							}
						}
					}
				}
			}
		}

		if (x * tileSize - camera.getX() + tileSize >= 0) {
			if (x * tileSize - camera.getX() <= width) {
				if (y * tileSize - camera.getY() <= height) {
					if (y * tileSize - camera.getY() + tileSize >= 0) {
						if (unit.getPlayer().getColor() == GameConfiguration.PLAYER2COLOR) {
							graphics.setColor(new Color(255, 210, 210));
						} else if (unit.getPlayer().getColor() == GameConfiguration.PLAYER1COLOR) {
							graphics.setColor(new Color(210, 210, 255));
						}
						graphics.fillOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize,
								tileSize);
						if (unit.isSelected()) {
							paintHealthbar(unit, graphics, camera);
							graphics.setColor(Color.RED);
							graphics.drawOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize,
									tileSize);
							graphics.setColor(Color.BLACK);
							graphics.setFont(graphics.getFont().deriveFont(18f));
							int stringwidth = graphics.getFontMetrics().stringWidth("Builder");
							graphics.drawString("Builder",
									x * tileSize + ((tileSize - stringwidth) / 2) - camera.getX(),
									y * tileSize + 80 - camera.getY());
						}
					}
				}
			}
		}
	}

	public void paint(UnitFighter unit, Camera camera, Graphics graphics) {
		Position position = unit.getPosition();
		int tileSize = GameConfiguration.TILE_SIZE;
		int width = GameConfiguration.WINDOW_WIDTH;
		int height = GameConfiguration.WINDOW_HEIGHT;

		int y = position.getY();
		int x = position.getX();

		// paint path
		if (!unit.getPath().isEmpty()) {
			List<Position> path = unit.getPath();
			graphics.setColor(Color.RED);
			graphics.drawLine(unit.getPosition().getX() * tileSize - camera.getX() + (tileSize / 2),
					unit.getPosition().getY() * tileSize - camera.getY() + (tileSize / 2),
					path.get(0).getX() * tileSize - camera.getX() + (tileSize / 2),
					path.get(0).getY() * tileSize - camera.getY() + (tileSize / 2));

			for (int i = 0; i < path.size() - 1; i++) {
				if (path.get(i).getX() * tileSize - camera.getX() + tileSize >= 0) {
					if (path.get(i).getX() * tileSize - camera.getX() <= width) {
						if (path.get(i).getY() * tileSize - camera.getY() <= height) {
							if (path.get(i).getY() * tileSize - camera.getY() + tileSize >= 0) {
								graphics.setColor(Color.RED);
								graphics.drawLine(path.get(i).getX() * tileSize - camera.getX() + (tileSize / 2),
										path.get(i).getY() * tileSize - camera.getY() + (tileSize / 2),
										path.get(i + 1).getX() * tileSize - camera.getX() + (tileSize / 2),
										path.get(i + 1).getY() * tileSize - camera.getY() + (tileSize / 2));
							}
						}
					}
				}
			}
		}

		if (x * tileSize - camera.getX() + tileSize >= 0) {
			if (x * tileSize - camera.getX() <= width) {
				if (y * tileSize - camera.getY() <= height) {
					if (y * tileSize - camera.getY() + tileSize >= 0) {
						if (unit.getPlayer().getColor() == GameConfiguration.PLAYER2COLOR) {
							graphics.setColor(new Color(103, 50, 50));
						} else if (unit.getPlayer().getColor() == GameConfiguration.PLAYER1COLOR) {
							graphics.setColor(new Color(100, 100, 200));
						}
						graphics.fillOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize,
								tileSize);
						if (unit.isSelected()) {
							paintHealthbar(unit, graphics, camera);
							graphics.setColor(Color.RED);
							graphics.drawOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize,
									tileSize);
							graphics.setColor(Color.BLACK);
							graphics.setFont(graphics.getFont().deriveFont(18f));
							int stringwidth = graphics.getFontMetrics().stringWidth("Fighter");
							graphics.drawString("Fighter",
									x * tileSize + ((tileSize - stringwidth) / 2) - camera.getX(),
									y * tileSize + 80 - camera.getY());
						}
					}
				}
			}
		}
	}

	public void paintHealthbar(Unit unit, Graphics graphics, Camera camera) {
		int tileSize = GameConfiguration.TILE_SIZE;
		if (unit.isSelected()) {
			Position p = unit.getPosition();
			int widthLife = tileSize
					- (int) ((((float) unit.getHpMax() - (float) unit.getHp()) / (float) unit.getHpMax())
							* (float) tileSize);
			if (p.getY() > 10) {
				graphics.setColor(Color.red);
				graphics.fillRect(p.getX() * tileSize - camera.getX(), p.getY() * tileSize - 20 - camera.getY(),
						tileSize, 10);
				graphics.setColor(Color.green);
				graphics.fillRect(p.getX() * tileSize - camera.getX(), p.getY() * tileSize - 20 - camera.getY(),
						widthLife, 10);
			} else {
				graphics.setColor(Color.red);
				graphics.fillRect(p.getX() * tileSize - camera.getX(), p.getY() * tileSize - 20 - camera.getY(),
						tileSize, 10);
				graphics.setColor(Color.green);
				graphics.fillRect(p.getX() * tileSize - camera.getX(), p.getY() * tileSize - 20 - camera.getY(),
						widthLife, 10);
			}
		}
	}

	public void paint(Minimap minimap, Map map, EntitiesManager manager, Camera camera, Graphics graphics) {
		int width = minimap.getWidth();
		int height = minimap.getHeight();
		int columnCount = map.getColumnCount();
		int lineCount = map.getLineCount();
		float sizeX = (float) width / (float) columnCount;
		float sizeY = (float) height / (float) lineCount;
		Tile tiles[][] = map.getTiles();

		for (int lineIndex = 0 ; lineIndex < lineCount ; lineIndex++){
			for (int columnIndex = 0 ; columnIndex < columnCount ; columnIndex++){
				Tile tile = tiles[lineIndex][columnIndex];
				switch (tile.getBiome().getId()) {
					case 0: // plain
						graphics.drawImage(new ImageIcon("src/graphic/plain.png").getImage(), (int)(tile.getColumn() * sizeX),
								(int)(tile.getLine() * sizeY), (int)sizeX+1, (int)sizeY+1, null);
						break;
					case 1: // hill
						graphics.drawImage(new ImageIcon("src/graphic/hill.png").getImage(), (int)(tile.getColumn() * sizeX),
								(int)(tile.getLine() * sizeY), (int)sizeX+1, (int)sizeY+1, null);
						break;
					case 2: // forest
						graphics.drawImage(new ImageIcon("src/graphic/forest.png").getImage(), (int)(tile.getColumn() * sizeX),
								(int)(tile.getLine() * sizeY), (int)sizeX+1, (int)sizeY+1, null);
						break;
					case 3: // mountain
						graphics.drawImage(new ImageIcon("src/graphic/mountain.png").getImage(), (int)(tile.getColumn() * sizeX),
								(int)(tile.getLine() * sizeY), (int)sizeX+1, (int)sizeY+1, null);
						break;
					case 4: // sea
						graphics.drawImage(new ImageIcon("src/graphic/water.png").getImage(), (int)(tile.getColumn() * sizeX),
								(int)(tile.getLine() * sizeY), (int)sizeX+1, (int)sizeY+1, null);
						break;
				}
				if (tile.getOwner() != null) {
					graphics.setColor(tile.getOwner().getColor());
					graphics.fillRect((int)(tile.getColumn() * sizeX), (int)(tile.getLine() * sizeY), (int)sizeX+1, (int)sizeY+1);
				}
			}
		}

		List<Player> players = manager.getPlayers();

		List<Unit> units = manager.getUnits();
		for (Unit unit : units) {
			Position position = unit.getPosition();
			int x = position.getX();
			int y = position.getY();
			for (Player player : players) {
				if (unit.getPlayer().equals(player)) {
					if (unit.getPlayer().getColor() == GameConfiguration.PLAYER2COLOR) {
						graphics.setColor(new Color(255, 210, 210));
					} else if (unit.getPlayer().getColor() == GameConfiguration.PLAYER1COLOR) {
						graphics.setColor(new Color(210, 210, 255));
					}
					break;
				}
			}
			graphics.fillOval((int) (x * sizeX), (int) (y * sizeY), (int) sizeX, (int) sizeY);
		}
		List<City> cities = manager.getCities();
		for (City city : cities) {
			Position position = city.getPosition();
			int x = position.getX();
			int y = position.getY();
			for (Player player : players) {
				if (city.getPlayer().equals(player)) {
					graphics.setColor(player.getColor());
					break;
				}
			}
			graphics.fillOval((int) (x * sizeX), (int) (y * sizeY), (int) sizeX, (int) sizeY);
		}
		List<Building> buildings = manager.getBuildings();
		for (Building building : buildings) {
			Position position = building.getPosition();
			int x = position.getX();
			int y = position.getY();
			for (Player player : players) {
				if (building.getPlayer().equals(player)) {
					graphics.setColor(player.getColor());
					break;
				}
			}
			graphics.fillOval((int) (x * sizeX), (int) (y * sizeY), (int) sizeX, (int) sizeY);
		}

		if(manager.getSelectedUnit() != null){
			Position position = manager.getSelectedUnit().getPosition();
			graphics.setColor(Color.RED);
			graphics.drawOval((int)(position.getX() * sizeX), (int)(position.getY() * sizeY), (int)sizeX+1, (int)sizeY+1);
		}
		else if (manager.getSelectedCity() != null){
			Position position = manager.getSelectedCity().getPosition();
			graphics.setColor(Color.RED);
			graphics.drawOval((int)(position.getX() * sizeX), (int)(position.getY() * sizeY), (int)sizeX+1, (int)sizeY+1);
		}

		graphics.setColor(new Color(0, 0, 0, 100));
		int cameraX = (int)(camera.getX()/GameConfiguration.TILE_SIZE*sizeX);
		int cameraY = (int)(camera.getY()/GameConfiguration.TILE_SIZE*sizeY);
		int cameraW = (int)(GameConfiguration.WINDOW_WIDTH/GameConfiguration.TILE_SIZE*sizeX);
		int cameraH = (int)(GameConfiguration.WINDOW_HEIGHT/GameConfiguration.TILE_SIZE*sizeY);
		graphics.fillRect(0, 0, minimap.getWidth(), cameraY);
		graphics.fillRect(0, cameraY+cameraH, minimap.getWidth(), minimap.getHeight());
		graphics.fillRect(0, cameraY, cameraX, cameraH);
		graphics.fillRect(cameraX+cameraW, cameraY, minimap.getWidth()-(cameraX+cameraW), cameraH);


		int borderThickness = 3;
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, width, borderThickness-2);
		graphics.fillRect(width - borderThickness, 0, width, height);
		graphics.fillRect(0, 0, borderThickness, height);
		graphics.fillRect(0, height - borderThickness, width, height);
	}

}
