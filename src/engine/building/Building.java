package engine.building;

import engine.Entity;
import engine.Position;

public abstract class Building extends Entity{

	public Building(int hp, int hpMax, String description, Position position) {
		super(hp, hpMax, description, position);
	}

}
