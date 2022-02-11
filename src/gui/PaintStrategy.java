package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import configuration.GameConfiguration;
import engine.Camera;
import engine.Entity;
import engine.Position;
import engine.building.City;
import engine.map.Map;
import engine.map.Tile;
import engine.unit.Unit;
import engine.unit.UnitBuilder;
import engine.unit.UnitFighter;

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

		int y = position.getY();
		int x = position.getX();

		if (x * tileSize - camera.getX() + tileSize >= 0) {
			if (x * tileSize - camera.getX() <= width) {
				if (y * tileSize - camera.getY() <= height) {
					if (y * tileSize - camera.getY() + tileSize >= 0) {
						graphics.setColor(city.getPlayer().getColor());
						graphics.fillOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize, tileSize);
						if(city.isSelected()) {
							graphics.setColor(Color.RED);
							graphics.drawOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize, tileSize);
						}
					}
				}
			}
		}

	}
	
	public void paint(Unit unit, Camera camera, Graphics graphics) {
		Position position = unit.getPosition();
		int tileSize = GameConfiguration.TILE_SIZE;
		int width = GameConfiguration.WINDOW_WIDTH;
		int height = GameConfiguration.WINDOW_HEIGHT;

		int y = position.getY();
		int x = position.getX();

		if (x * tileSize - camera.getX() + tileSize >= 0) {
			if (x * tileSize - camera.getX() <= width) {
				if (y * tileSize - camera.getY() <= height) {
					if (y * tileSize - camera.getY() + tileSize >= 0) {
						if(unit instanceof UnitBuilder) {
							graphics.setColor(Color.PINK);
						} else if (unit instanceof UnitFighter) {
							graphics.setColor(Color.GREEN);
						} else {
							graphics.setColor(Color.BLACK);
						}
						graphics.fillOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize, tileSize);
						if(unit.isSelected()) {
							paintHealthbar(unit, graphics, camera);
							graphics.setColor(Color.RED);
							graphics.drawOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize, tileSize);
							graphics.setColor(Color.BLACK);
							graphics.setFont(graphics.getFont().deriveFont(18f));
							if(unit instanceof UnitBuilder) {
								int stringwidth = graphics.getFontMetrics().stringWidth("Builder");
								graphics.drawString("Builder", x * tileSize + ((tileSize-stringwidth)/2)  - camera.getX(), y * tileSize + 80 - camera.getY());
							} else if (unit instanceof UnitFighter) {
								int stringwidth = graphics.getFontMetrics().stringWidth("Fighter");
								graphics.drawString("Fighter", x * tileSize + ((tileSize-stringwidth)/2)  - camera.getX(), y * tileSize + 80 - camera.getY());
							}
						}
					}
				}
			}
		}
	}
	
	public void paint(List<Position> path, Camera camera, Graphics graphics) {
		int tileSize = GameConfiguration.TILE_SIZE;
		int width = GameConfiguration.WINDOW_WIDTH;
		int height = GameConfiguration.WINDOW_HEIGHT;
		for (int i = 0; i<path.size()-1; i++) {
			if (path.get(i).getX() * tileSize - camera.getX() + tileSize >= 0) {
				if (path.get(i).getX() * tileSize - camera.getX() <= width) {
					if (path.get(i).getY() * tileSize - camera.getY() <= height) {
						if (path.get(i).getY() * tileSize - camera.getY() + tileSize >= 0) {
							graphics.setColor(Color.RED);
							graphics.drawLine(path.get(i).getX() * tileSize - camera.getX() + (tileSize / 2), path.get(i).getY() * tileSize - camera.getY() + (tileSize / 2), path.get(i+1).getX() * tileSize - camera.getX() + (tileSize / 2), path.get(i+1).getY() * tileSize - camera.getY() + (tileSize / 2));
						}
					}
				}
			}
		}
	}

	public void paintHealthbar(Unit unit, Graphics graphics, Camera camera) {
		int tileSize = GameConfiguration.TILE_SIZE;
		if(unit.isSelected()) {
			Position p = unit.getPosition();
			int widthLife = tileSize - (int)((((float)unit.getHpMax() - (float)unit.getHp()) / (float)unit.getHpMax()) * (float)tileSize);
			if(p.getY() > 10) {
				graphics.setColor(Color.red);
				graphics.fillRect(p.getX()*tileSize - camera.getX(), p.getY()*tileSize - 20 - camera.getY(), tileSize, 10);
				graphics.setColor(Color.green);
				graphics.fillRect(p.getX()*tileSize - camera.getX(), p.getY()*tileSize - 20 - camera.getY(), widthLife, 10);
			}
			else {
				graphics.setColor(Color.red);
				graphics.fillRect(p.getX()*tileSize - camera.getX(), p.getY()*tileSize - 20 - camera.getY(), tileSize, 10);
				graphics.setColor(Color.green);
				graphics.fillRect(p.getX()*tileSize - camera.getX(), p.getY()*tileSize - 20 - camera.getY(), widthLife, 10);
			}
		}
	}

}
