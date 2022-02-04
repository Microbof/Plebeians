package engine.process;

import engine.map.Tile;
import engine.unit.Unit;
import engine.map.Map;
import engine.Player;
import engine.Position;
import engine.building.City;

public class GameBuilder {
	
	public static Map buildMap() {
		return new Map(100,100);
	}
	
	public static UnitManager buildInitUnit(Map map) {
		UnitManager manager = new UnitManager(map);
		
		initializePlayer(manager);
		
		return manager;
	}
	
	private static void initializePlayer(UnitManager manager) {
		Player player = new Player();
		Position posCity = new Position(10,10);
		Position posUnit = new Position(9,10);
		player.getCities().add(new City(100,100,"The player's main city.",posCity,"le Bled"));
		player.getUnits().add(new Unit(20,20,"This is a unit.",posUnit));
		manager.set(player);
	}

}
