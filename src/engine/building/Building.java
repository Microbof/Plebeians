package engine.building;

import engine.Entity;
import engine.Player;
import engine.Position;

public abstract class Building extends Entity{
	
	private int hp;

	public Building(int hp, int hpMax, String description, Position position, Player player) {
		super(hp, hpMax, description, position, player);
	}
	
	public int getHealth() {
		return hp;
	}
}
