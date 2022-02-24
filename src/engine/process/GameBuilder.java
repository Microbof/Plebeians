package engine.process;

import engine.unit.Unit;
import engine.unit.UnitBuilder;
import engine.unit.UnitFighter;
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
	
	public static void buildInitUnit(Map map, EntitiesManager manager) {
		initializePlayer(manager);

	}
	
	private static void initializePlayer(EntitiesManager manager) {
		Player player = new Player();
		player.setColor(Color.blue);
		manager.addPlayer(player);
		Position posCity = new Position(10,10);
		Position posBuilder = new Position(9,10);
		Position posFighter = new Position(11,10);
		City city = new City(100,100,"The player's main city.",posCity,"le Bled", player);
		city.addInfluence(player, map.getTile(9, 10));
		city.addInfluence(player, map.getTile(11, 10));
		city.addInfluence(player, map.getTile(10, 9));
		city.addInfluence(player, map.getTile(10, 11));
		manager.addCity(city);
		UnitBuilder builder = new UnitBuilder(20,20,"This is a builder.",posBuilder, player);
		UnitFighter fighter = new UnitFighter(20,20,"This is a fighter.",posFighter, player);
		manager.addUnit(builder);
		manager.addUnit(fighter);
	}

}
