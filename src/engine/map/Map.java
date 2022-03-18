package engine.map;

public class Map {

	private Tile[][] tiles;
	
	private int lineCount;
	
	private int columnCount;
	
	
	public Map(int line, int column){
		
		this.lineCount = line;
		this.columnCount = column;
		tiles = new Tile[line][column];
		
		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				Tile tile = new Tile(lineIndex, columnIndex, new Plain());
				tiles[lineIndex][columnIndex] = tile;
			}
		}
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