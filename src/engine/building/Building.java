package engine.building;

import engine.Entity;
import engine.Position;

public abstract class Building extends Entity{
	
	//private Position position;
	
	private int hp;

	public Building(int hp, int hpMax, String description, Position position) {
		super(hp, hpMax, description, position);
	}

	//public Position getPosition() {
	//	return position;
	//}
	
	public int getHealth() {
		return hp;
	}
}
