package engine.map;

import javax.swing.JPanel;

import configuration.GameConfiguration;
import engine.process.EntitiesManager;

public class Minimap {

	int firstGridXOfMap;
	private int firstGridYOfMap;
	private int gridMapWidth;
	private int gridMapHeight;
	private final int MINIMAP_OFFSET = 10;

	private Tile[][] tiles;

	private int lineCount;

	private int columnCount;

	public Minimap(JPanel minimapPanel) {

		int panelWidth = minimapPanel.getWidth();
		int panelHeight = minimapPanel.getHeight();
		int panelX = minimapPanel.getX() + panelWidth / 2;
		int panelY = minimapPanel.getY();

		firstGridXOfMap = panelX + MINIMAP_OFFSET;
		firstGridYOfMap = panelY + MINIMAP_OFFSET;
		gridMapWidth = (panelWidth / 2) / GameConfiguration.COLUMN_COUNT;
		gridMapHeight = panelHeight / GameConfiguration.LINE_COUNT;

	}

	public int getFirstGridXOfMap() {
		return firstGridXOfMap;
	}

	public int getFirstGridYOfMap() {
		return firstGridYOfMap;
	}

	public int getGridMapWidth() {
		return gridMapWidth;
	}

	public int getGridMapHeight() {
		return gridMapHeight;
	}
	public Tile[][] getTiles(){
		return tiles;
	}
	
	public int getLineCount(){
		return this.lineCount;
	}
	
	public int getColumnCount(){
		return this.columnCount;
	}
	
	public Tile getTile(int line, int column){
		return this.tiles[line][column];
	}
}
