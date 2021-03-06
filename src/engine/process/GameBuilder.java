package engine.process;

import engine.unit.UnitBuilder;
import engine.unit.UnitFighter;
import engine.map.Map;

import configuration.GameConfiguration;

import engine.Player;
import engine.Position;
import engine.building.City;

public class GameBuilder {
	
	private static Map map;
	
	public static Map buildMap() {
		//map = new Map(100,100);
		map = new Map();
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
		Position posCity = new Position(12,4);
		Position posBuilder = new Position(posCity.getX()-1, posCity.getY());
		Position posFighter = new Position(posCity.getX()+1,posCity.getY());
		City city = new City(1,100,"The player's main city.",posCity,"le Bled Bleu", player);
		city.addInfluence(player, map.getTile(posCity.getY(), posCity.getX()));
		city.addInfluence(player, map.getTile(posCity.getY()-1, posCity.getX()));
		city.addInfluence(player, map.getTile(posCity.getY()+1, posCity.getX()));
		city.addInfluence(player, map.getTile(posCity.getY(), posCity.getX()-1));
		city.addInfluence(player, map.getTile(posCity.getY(), posCity.getX()+1));
		manager.addCity(city);
		UnitBuilder builder = new UnitBuilder(20,20,"Ouvriers",posBuilder, player, 5);
		UnitFighter fighter = new UnitFighter(20,20,"Unité combattante",posFighter, player, 5);
		manager.addUnit(builder);
		manager.addUnit(fighter);
	}
	
	private static void initializePlayer2(EntitiesManager manager) {
		Player player = new Player();
		player.setName("Joueur 2");
		player.setColor(GameConfiguration.PLAYER2COLOR);
		manager.addPlayer(player);
		Position posCity = new Position(10,25);
		Position posBuilder = new Position(posCity.getX()-1, posCity.getY());
		Position posFighter = new Position(posCity.getX()+1,posCity.getY());
		City city = new City(1,100,"The player's main city.",posCity,"le Bled Rouge", player);
		city.addInfluence(player, map.getTile(posCity.getY(), posCity.getX()));
		city.addInfluence(player, map.getTile(posCity.getY()-1, posCity.getX()));
		city.addInfluence(player, map.getTile(posCity.getY()+1, posCity.getX()));
		city.addInfluence(player, map.getTile(posCity.getY(), posCity.getX()-1));
		city.addInfluence(player, map.getTile(posCity.getY(), posCity.getX()+1));
		manager.addCity(city);
		UnitBuilder builder = new UnitBuilder(20,20,"Ouvriers",posBuilder, player, 5);
		UnitFighter fighter = new UnitFighter(20,20,"Unité combattante",posFighter, player, 5);
		manager.addUnit(builder);
		manager.addUnit(fighter);
	}

}
