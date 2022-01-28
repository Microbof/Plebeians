package engine;

public class Mouse {
	
	private Position position;
	
	public Mouse() {
		setPosition(new Position(0, 0));
	}
	
	public void setX(int x) {
		position.setX(x);
	}
	
	public void setY(int y) {
		position.setY(y);
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
