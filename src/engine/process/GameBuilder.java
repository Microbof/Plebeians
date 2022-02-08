package engine.process;

import engine.unit.Unit;
import engine.map.Map;

import java.awt.Color;

import engine.Player;
import engine.Position;
import engine.building.City;

public class GameBuilder {
	
	private static Map map;
	
	public static Map buildMap() {
		map = new Map(100,100);
		return map;
	}
	
	public static UnitManager buildInitUnit(Map map) {
		UnitManager manager = new UnitManager(map);
		
		initializePlayer(manager);
		
		return manager;
	}
	
	private static void initializePlayer(UnitManager manager) {
		Player player = new Player();
		player.setColor(Color.blue);
		Position posCity = new Position(10,10);
		Position posUnit = new Position(9,10);
		player.getCities().add(new City(100,100,"The player's main city.",posCity,"le Bled"));
		player.getCities().get(0).addInfluence(player, map.getTile(9, 10));
		player.getCities().get(0).addInfluence(player, map.getTile(11, 10));
		player.getCities().get(0).addInfluence(player, map.getTile(10, 9));
		player.getCities().get(0).addInfluence(player, map.getTile(10, 11));
		player.getUnits().add(new Unit(20,20,"This is a unit.",posUnit));
		manager.set(player);
	}

}
