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

	public Map() {
		this.lineCount = 30;
		this.columnCount = 30;
		tiles = new Tile[lineCount][columnCount];
		//sea
		for (int lineIndex = 0 ; lineIndex < lineCount ; lineIndex++) {
			for (int columnIndex = 0 ; columnIndex < columnCount ; columnIndex++) {
				tiles[lineIndex][columnIndex] = new Tile(lineIndex, columnIndex, new Sea());
			}
		}
		//plain
		for (int lineIndex = 23 ; lineIndex < 26 ; lineIndex++) {
			tiles[lineIndex][2] = new Tile(lineIndex, 2, new Plain());
		}
		for (int lineIndex = 22 ; lineIndex < 27 ; lineIndex++) {
			tiles[lineIndex][3] = new Tile(lineIndex, 3, new Plain());
		}
		for (int lineIndex = 16 ; lineIndex < 28 ; lineIndex++) {
			tiles[lineIndex][4] = new Tile(lineIndex, 4, new Plain());
		}
		for (int lineIndex = 11 ; lineIndex < 28 ; lineIndex++) {
			tiles[lineIndex][5] = new Tile(lineIndex, 5, new Plain());
		}
		for (int lineIndex = 7 ; lineIndex < 28 ; lineIndex++) {
			tiles[lineIndex][6] = new Tile(lineIndex, 6, new Plain());
		}
		for (int lineIndex = 3 ; lineIndex < 28 ; lineIndex++) {
			tiles[lineIndex][7] = new Tile(lineIndex, 7, new Plain());
		}
		for (int lineIndex = 2 ; lineIndex < 28 ; lineIndex++) {
			for (int columnIndex = 8 ; columnIndex < 18 ; columnIndex++) {
				tiles[lineIndex][columnIndex] = new Tile(lineIndex, columnIndex, new Plain());
			}
		}
		for (int lineIndex = 3 ; lineIndex < 27 ; lineIndex++) {
			for (int columnIndex = 18 ; columnIndex < 21 ; columnIndex++) {
				tiles[lineIndex][columnIndex] = new Tile(lineIndex, columnIndex, new Plain());
			}
		}
		for (int lineIndex = 4 ; lineIndex < 25 ; lineIndex++) {
			tiles[lineIndex][21] = new Tile(lineIndex, 21, new Plain());
		}
		for (int lineIndex = 4 ; lineIndex < 24 ; lineIndex++) {
			tiles[lineIndex][22] = new Tile(lineIndex, 22, new Plain());
		}
		for (int lineIndex = 4 ; lineIndex < 15 ; lineIndex++) {
			tiles[lineIndex][23] = new Tile(lineIndex, 23, new Plain());
		}
		for (int lineIndex = 5 ; lineIndex < 12 ; lineIndex++) {
			tiles[lineIndex][24] = new Tile(lineIndex, 24, new Plain());
		}
		//lake
		for (int lineIndex = 19 ; lineIndex < 22 ; lineIndex++) {
			for (int columnIndex = 6 ; columnIndex < 11 ; columnIndex++) {
				tiles[lineIndex][columnIndex] = new Tile(lineIndex, columnIndex, new Sea());
			}
		}
		for (int columnIndex = 7 ; columnIndex < 10 ; columnIndex++) {
			tiles[18][columnIndex] = new Tile(18, columnIndex, new Sea());
		}
		for (int columnIndex = 7 ; columnIndex < 10 ; columnIndex++) {
			tiles[22][columnIndex] = new Tile(22, columnIndex, new Sea());
		}
		for (int lineIndex = 8 ; lineIndex < 11 ; lineIndex++) {
			for (int columnIndex = 17 ; columnIndex < 22 ; columnIndex++) {
				tiles[lineIndex][columnIndex] = new Tile(lineIndex, columnIndex, new Sea());
			}
		}
		for (int columnIndex = 18 ; columnIndex < 21 ; columnIndex++) {
			tiles[7][columnIndex] = new Tile(7, columnIndex, new Sea());
		}
		for (int columnIndex = 18 ; columnIndex < 21 ; columnIndex++) {
			tiles[11][columnIndex] = new Tile(11, columnIndex, new Sea());
		}
		//hill
		for (int columnIndex = 7 ; columnIndex < 9 ; columnIndex++) {
			tiles[12][columnIndex] = new Tile(12, columnIndex, new Hill());
			tiles[13][columnIndex] = new Tile(13, columnIndex, new Hill());
		}
		for (int columnIndex = 12 ; columnIndex < 14 ; columnIndex++) {
			tiles[16][columnIndex] = new Tile(16, columnIndex, new Hill());
			tiles[17][columnIndex] = new Tile(17, columnIndex, new Hill());
		}
		for (int columnIndex = 17 ; columnIndex < 19 ; columnIndex++) {
			tiles[15][columnIndex] = new Tile(15, columnIndex, new Hill());
			tiles[16][columnIndex] = new Tile(16, columnIndex, new Hill());
		}
		//mountain
		for (int columnIndex = 8 ; columnIndex < 11 ; columnIndex++) {
			tiles[7][columnIndex] = new Tile(7, columnIndex, new Mountain());
		}
		for (int columnIndex = 9 ; columnIndex < 12 ; columnIndex++) {
			tiles[8][columnIndex] = new Tile(8, columnIndex, new Mountain());
		}
		for (int columnIndex = 10 ; columnIndex < 14 ; columnIndex++) {
			tiles[9][columnIndex] = new Tile(9, columnIndex, new Mountain());
		}
		for (int columnIndex = 12 ; columnIndex < 14 ; columnIndex++) {
			tiles[10][columnIndex] = new Tile(10, columnIndex, new Mountain());
		}
		for (int columnIndex = 13 ; columnIndex < 15 ; columnIndex++) {
			tiles[11][columnIndex] = new Tile(11, columnIndex, new Mountain());
		}
		tiles[12][14] = new Tile(12, 14, new Mountain());
		for (int columnIndex = 13 ; columnIndex < 15 ; columnIndex++) {
			tiles[21][columnIndex] = new Tile(21, columnIndex, new Mountain());
		}
		for (int columnIndex = 18 ; columnIndex < 20 ; columnIndex++) {
			tiles[21][columnIndex] = new Tile(21, columnIndex, new Mountain());
		}
		for (int columnIndex = 14 ; columnIndex < 21 ; columnIndex++) {
			tiles[22][columnIndex] = new Tile(22, columnIndex, new Mountain());
		}
		for (int columnIndex = 15 ; columnIndex < 17 ; columnIndex++) {
			tiles[23][columnIndex] = new Tile(23, columnIndex, new Mountain());
		}
		//forest
		for (int columnIndex = 8 ; columnIndex < 10 ; columnIndex++) {
			tiles[4][columnIndex] = new Tile(4, columnIndex, new Forest());
			tiles[5][columnIndex] = new Tile(5, columnIndex, new Forest());
		}
		for (int columnIndex = 15 ; columnIndex < 17 ; columnIndex++) {
			tiles[3][columnIndex] = new Tile(3, columnIndex, new Forest());
			tiles[4][columnIndex] = new Tile(4, columnIndex, new Forest());
		}
		tiles[4][17] = new Tile(4, 17, new Forest());
		tiles[9][9] = new Tile(9, 9, new Forest());
		tiles[10][10] = new Tile(10, 10, new Forest());
		tiles[10][11] = new Tile(10, 11, new Forest());
		for (int columnIndex = 15 ; columnIndex < 18 ; columnIndex++) {
			tiles[21][columnIndex] = new Tile(21, columnIndex, new Forest());
		}
		tiles[23][14] = new Tile(23, 14, new Forest());
		for (int columnIndex = 14 ; columnIndex < 17 ; columnIndex++) {
			tiles[24][columnIndex] = new Tile(24, columnIndex, new Forest());
		}
		for (int columnIndex = 5 ; columnIndex < 7 ; columnIndex++) {
			tiles[25][columnIndex] = new Tile(25, columnIndex, new Forest());
		}
		for (int columnIndex = 5 ; columnIndex < 8 ; columnIndex++) {
			tiles[26][columnIndex] = new Tile(26, columnIndex, new Forest());
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