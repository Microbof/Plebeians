package engine.process;

import java.util.ArrayList;
import java.util.List;

import engine.Player;
import engine.building.Building;
import engine.building.City;
import engine.map.Map;
import engine.unit.Unit;

public class EntitiesManager {
	
	private Map map;
	
	private List<Player> players = new ArrayList<>();
	
	private List<Unit> units = new ArrayList<>();

	private List<Building> buildings = new ArrayList<>();

	private List<City> cities = new ArrayList<>();

	public EntitiesManager(Map map) {
		this.map = map;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	public List<Unit> getUnits() {
		return units;
	}
	
	public void addUnit(Unit unit) {
		units.add(unit);
	}

	public List<Building> getBuildings() {
		return buildings;
	}
	
	public void addBuilding(Building building) {
		buildings.add(building);
	}

	public List<City> getCities() {
		return cities;
	}
	
	public void addCity(City city) {
		cities.add(city);
	}

}
