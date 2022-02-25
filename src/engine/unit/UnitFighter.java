package engine.unit;

import engine.Player;
import engine.Position;

public class UnitFighter extends Unit {

	public UnitFighter(int hp, int hpMax, String description, Position position, Player player, int ap) {
		super(hp, hpMax, description, position, player, ap);
	}
}