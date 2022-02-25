package engine.unit;

import engine.Player;
import engine.Position;

public class UnitBuilder extends Unit {

	public UnitBuilder(int hp, int hpMax, String description, Position position, Player player, int ap) {
		super(hp, hpMax, description, position, player, ap);
	}
}
