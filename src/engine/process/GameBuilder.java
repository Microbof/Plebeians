package engine.process;

import engine.unit.Unit;
import engine.unit.UnitBuilder;
import engine.unit.UnitFighter;
import engine.map.Map;

import java.awt.Color;

import configuration.GameConfiguration;

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
		initializePlayer2(manager);
		manager.setCurrentPlayer(manager.getPlayers().get(0));

	}
	
	private static void initializePlayer(EntitiesManager manager) {
		Player player = new Player();
		player.setName("Joueur 1");
		player.setColor(GameConfiguration.PLAYER1COLOR);
		manager.addPlayer(player);
		Position posCity = new Position(10,10);
		Position posBuilder = new Position(9,10);
		Position posFighter = new Position(11,10);
		City city = new City(100,100,"The player's main city.",posCity,"le Bled Bleu", player);
		city.addInfluence(player, map.getTile(10, 10));
		city.addInfluence(player, map.getTile(9, 10));
		city.addInfluence(player, map.getTile(11, 10));
		city.addInfluence(player, map.getTile(10, 9));
		city.addInfluence(player, map.getTile(10, 11));
		manager.addCity(city);
		UnitBuilder builder = new UnitBuilder(20,20,"This is a builder.",posBuilder, player, 5);
		UnitFighter fighter = new UnitFighter(20,20,"This is a fighter.",posFighter, player, 5);
		manager.addUnit(builder);
		manager.addUnit(fighter);
	}
	
	private static void initializePlayer2(EntitiesManager manager) {
		Player player = new Player();
		player.setName("Joueur 2");
		player.setColor(GameConfiguration.PLAYER2COLOR);
		manager.addPlayer(player);
		Position posCity = new Position(5,5);
		Position posBuilder = new Position(4,5);
		Position posFighter = new Position(6,5);
		City city = new City(100,100,"The player's main city.",posCity,"le Bled Rouge", player);
		city.addInfluence(player, map.getTile(5, 5));
		city.addInfluence(player, map.getTile(4, 5));
		city.addInfluence(player, map.getTile(6, 5));
		city.addInfluence(player, map.getTile(5, 4));
		city.addInfluence(player, map.getTile(5, 6));
		manager.addCity(city);
		UnitBuilder builder = new UnitBuilder(20,20,"This is a builder.",posBuilder, player, 5);
		UnitFighter fighter = new UnitFighter(20,20,"This is a fighter.",posFighter, player, 5);
		manager.addUnit(builder);
		manager.addUnit(fighter);
	}

}
