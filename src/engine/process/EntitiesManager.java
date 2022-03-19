package engine.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.Player;
import engine.building.Building;
import engine.building.City;
import engine.map.Map;
import engine.map.Tile;
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
	
	private int turn;

	public EntitiesManager(Map map) {
		this.map = map;
		turn = 1;
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
		turn = getTurn() + 1;
		getNextTile();
	}
	
	public void getNextTile() {
		List<Tile> nearTile1 = new ArrayList<>();
		List<Tile> nearTile2 = new ArrayList<>();
		List<Tile> nearTile3 = new ArrayList<>();
		List<Tile> nearTile4 = new ArrayList<>();
		for (City city : cities) {
			if(city.getPlayer() == currentPlayer) {
				Tile nextTile=null;
				int nearTileCount=0;
				for(int j=1;j<99;j++) {
					for(int k=1;k<99;k++) {
						nearTileCount=0;
						if(map.getTile(j, k).getOwner()==null) {
							if(map.getTile(j-1, k).getOwner()==currentPlayer) {
								nearTileCount++;
							} if(map.getTile(j+1, k).getOwner()==currentPlayer) {
								nearTileCount++;
							} if(map.getTile(j, k-1).getOwner()==currentPlayer) {
								nearTileCount++;
							} if(map.getTile(j, k+1).getOwner()==currentPlayer) {
								nearTileCount++;
							}
						}
						if(nearTileCount!=0) {
							System.out.println(nearTileCount + " voisins " + " coord : " + j + " " + k);
						}
						switch(nearTileCount) {
						case 1:
							nearTile1.add(map.getTile(j,k));
							break;
						case 2:
							nearTile2.add(map.getTile(j,k));
							break;
						case 3:
							nearTile3.add(map.getTile(j,k));
							break;
						case 4:
							nearTile4.add(map.getTile(j,k));
							break;
						}
					}
				}
				Random rand = new Random();
				int tileSource=rand.nextInt(9);
				int randomTile;
				if(!nearTile4.isEmpty() && (tileSource>6)) {
					randomTile=rand.nextInt(nearTile4.size());
					nextTile=nearTile4.get(randomTile);
				} else if(!nearTile3.isEmpty() && (tileSource>3)) {
					randomTile=rand.nextInt(nearTile3.size());
					nextTile=nearTile3.get(randomTile);
				} else if(!nearTile2.isEmpty() && (tileSource>1)) {
					randomTile=rand.nextInt(nearTile2.size());
					nextTile=nearTile2.get(randomTile);
				} else if(!nearTile1.isEmpty() && (tileSource>-1)) {
					randomTile=rand.nextInt(nearTile1.size());
					nextTile=nearTile1.get(randomTile);
				}
				System.out.println("tile choisi : " + nextTile.getColumn() + " " + nextTile.getLine());
				if(nextTile!=null) city.addInfluence(currentPlayer, nextTile);
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

	public int getTurn() {
		return turn;
	}
}
