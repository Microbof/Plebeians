package engine;

import java.util.ArrayList;
import java.util.List;

import engine.building.Building;
import engine.building.City;
import engine.map.Tile;
import engine.unit.Unit;

public class Player {
	
	private int score;
	
	private List<Unit> units = new ArrayList<>();
	
	private List<Building> buildings = new ArrayList<>();
	
	private List<City> cities = new ArrayList<>();
	
	// tab influence peut etre plutot dans city ? ou les deux ?
	private List<Tile> influence = new ArrayList<>();

	public Player() {
		score = 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// ajouter les fonction add
	
	public List<Unit> getUnits() {
		return units;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public List<City> getCities() {
		return cities;
	}

	public List<Tile> getInfluence() {
		return influence;
	}
	
	
	
	
}
