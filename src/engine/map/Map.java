package engine.map;

public class Map {

	private Tile[][] tiles;
	
	private int lineCount;
	
	private int columnCount;
	
	
	public Map(int line, int column, int id, String fileName){
		
		this.lineCount = line;
		this.columnCount = column;
		tiles = new Tile[line][column];
		
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