package gui;

import java.awt.Color;
import java.awt.Graphics;

import configuration.GameConfiguration;
import engine.Camera;
import engine.Position;
import engine.building.City;
import engine.map.Map;
import engine.map.Tile;
import engine.unit.Unit;

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
						graphics.setColor(Color.BLACK);
						graphics.fillOval(x * tileSize - camera.getX(), y * tileSize - camera.getY(), tileSize, tileSize);
					}
				}
			}
		}

	}
}
