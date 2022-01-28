package engine.unit;

import engine.Entity;
import engine.Position;

public class Unit extends Entity{

	public Unit(int hp, int hpMax, String description, Position position) {
		super(hp, hpMax, description, position);
	}

}
