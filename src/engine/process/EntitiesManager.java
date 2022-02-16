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

	private Unit selectedUnit = null;

	private City selectedCity = null;
	
	private Player currentPlayer;

	public EntitiesManager(Map map) {
		this.map = map;
	}

	public void update() {

		for (Unit unit : units) {
			unit.update(map);
		}

		/*for (City city : cities) {
			// update
		}*/
	}
	
	public void nextTurn() {
		int i = players.indexOf(currentPlayer) + 1;
		if(i >= players.size()) {
			currentPlayer = players.get(0);
		}
		else {
			currentPlayer = players.get(i);
		}
		for (Unit unit : units) {
			if(unit.getPlayer() == currentPlayer) {
				unit.resetAp();
			}
		}
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

	public Unit getSelectedUnit() {
		return selectedUnit;
	}

	public void selectUnit(Unit unit) {
		selectedUnit = unit;
		unit.setSelected(true);
	}

	public void unselectUnit() {
		if (selectedUnit != null) {
			selectedUnit.setSelected(false);
			selectedUnit = null;
		}
	}

	public City getSelectedCity() {
		return selectedCity;
	}

	public void selectCity(City city) {
		selectedCity = city;
		city.setSelected(true);
	}

	public void unselectCity() {
		if (selectedCity != null) {
			selectedCity.setSelected(false);
			selectedCity = null;
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
