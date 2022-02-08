package engine.unit;

import engine.Entity;
import engine.Player;
import engine.Position;

public class Unit extends Entity{

	public Unit(int hp, int hpMax, String description, Position position, Player player) {
		super(hp, hpMax, description, position, player);
	}

}
