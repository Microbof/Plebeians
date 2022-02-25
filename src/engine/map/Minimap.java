package engine.map;

import javax.swing.JPanel;

import configuration.GameConfiguration;

public class Minimap {

	int firstGridXOfMap;
	private int firstGridYOfMap;
	private int gridMapWidth;
	private int gridMapHeight;

	public Minimap(JPanel minimapPanel) {

		int panelOffset = 10;
		int panelWidth = minimapPanel.getWidth();
		int panelHeight = minimapPanel.getHeight();
		int panelX = minimapPanel.getX() + panelWidth / 2;
		int panelY = minimapPanel.getY();

		firstGridXOfMap = panelX + panelOffset;
		firstGridYOfMap = panelY + panelOffset;
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
}
