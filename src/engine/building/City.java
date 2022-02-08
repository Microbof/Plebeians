package engine.building;

import java.util.ArrayList;
import java.util.List;

import engine.Player;
import engine.Position;
import engine.map.Tile;

public class City extends Building {
	
	private List<Tile> zoneInfluence = new ArrayList<Tile>();

	private String name;

	public City(int hp, int hpMax, String description, Position position, String name, Player player) {
		super(hp, hpMax, description, position, player);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addInfluence(Player player, Tile zone) {
		if (zone.getOwner() == null) {
			zone.setOwner(player);
			zoneInfluence.add(zone);
		}
	}

	public List<Tile> getZoneInfluence() {
		return zoneInfluence;
	}

}
