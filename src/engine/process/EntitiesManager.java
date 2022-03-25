package engine.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.Player;
import engine.Position;
import engine.building.Building;
import engine.building.City;
import engine.map.Map;
import engine.map.Tile;
import engine.unit.Unit;
import engine.unit.UnitBuilder;
import engine.unit.UnitFighter;

public class EntitiesManager {

	private Map map;

	private List<Player> players = new ArrayList<>();

	private List<Unit> units = new ArrayList<>();

	private List<UnitBuilder> builders = new ArrayList<>();

	private List<UnitFighter> fighters = new ArrayList<>();

	private List<Building> buildings = new ArrayList<>();

	private List<City> cities = new ArrayList<>();
	
	private List<Unit> removeListUnit = new ArrayList<>();

	private Unit selectedUnit = null;

	private City selectedCity = null;
	
	private Player currentPlayer;
	
	private boolean canConstruct = true;
	
	private int turn;

	public EntitiesManager(Map map) {
		this.map = map;
		turn = 1;
	}

	public void update() {

		for (Unit unit : units) {
			if(unit.getHp() <= 0) {
				removeListUnit.add(unit);
			}
			unit.update(map);
		}
		
		for(Unit unit : removeListUnit) {
			units.remove(unit);
			builders.remove(unit);
			fighters.remove(unit);
		}
		

		/*for (City city : cities) {
			// update
		}*/
	}
	
	public void nextTurn() {
		canConstruct = true;
		int i = players.indexOf(currentPlayer) + 1;
		if(i >= players.size()) {
			currentPlayer = players.get(0);
			turn = getTurn() + 1;
		}
		else {
			currentPlayer = players.get(i);
		}
		for (Unit unit : units) {
			if(unit.getPlayer() == currentPlayer) {
				unit.resetAp();
			}
		}
		
		getNextTile();
	}
	
	public Position getNearestTile(Position position) {
		int srcY = position.getY();
		int srcX = position.getX();
		Tile nearestTile = null;
		for(int i=1;i<99;i++) {
			int j = -i;
			for(int k=0; k<=i;k++) {
				if(((srcY+j>0 && srcY+j<100)&&(srcX+k>0 && srcX+k<100)) && nearestTile == null) {
					if(map.getTile(srcY+j, srcX+k).getUnit()==null) {
						nearestTile = map.getTile(srcY+j, srcX+k);
					} else if (map.getTile(srcY+k, srcX+j).getUnit()==null) {
						nearestTile = map.getTile(srcY+k, srcX+j);
					}
				}
				if(((srcY-j>0 && srcY-j<100)&&(srcX+k>0 && srcX+k<100)) && nearestTile == null) {
					if(map.getTile(srcY-j, srcX+k).getUnit()==null) {
						nearestTile = map.getTile(srcY-j, srcX+k);
					} else if (map.getTile(srcY+k, srcX-j).getUnit()==null) {
						nearestTile = map.getTile(srcY+k, srcX-j);
					}
				}
				if(((srcY+j>0 && srcY+j<100)&&(srcX-k>0 && srcX-k<100)) && nearestTile == null) {
					if(map.getTile(srcY+j, srcX-k).getUnit()==null) {
						nearestTile = map.getTile(srcY+j, srcX-k);
					} else if (map.getTile(srcY-k, srcX+j).getUnit()==null) {
						nearestTile = map.getTile(srcY-k, srcX+j);
					}
				}
				if(((srcY-j>0 && srcY-j<100)&&(srcX-k>0 && srcX-k<100)) && nearestTile == null) {
					if(map.getTile(srcY-j, srcX-k).getUnit()==null) {
						nearestTile = map.getTile(srcY-j, srcX-k);
					} else if (map.getTile(srcY-k, srcX-j).getUnit()==null) {
						nearestTile = map.getTile(srcY-k, srcX-j);
					}
				}
			}
		}
		Position nearestTilePos = new Position(nearestTile.getColumn(),nearestTile.getLine());
		return nearestTilePos;
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
				if(nextTile!=null) city.addInfluence(currentPlayer, nextTile);
			}
		}
	}
	
	public void produceFighter(Player player, Position posFighter) {
		if (canConstruct == true) {
			UnitFighter fighter = new UnitFighter(20,20,"Unité combattante",posFighter, player, 5);
			addUnit(fighter);
		}
		canConstruct = false;
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
	
	public List<UnitBuilder> getBuilders() {
		return builders;
	}

	public List<UnitFighter> getFighters() {
		return fighters;
	}

	public void addUnit(UnitFighter unit) {
		units.add(unit);
		fighters.add(unit);
		Position unitPos = unit.getPosition();
		Tile unitTile = map.getTile(unitPos.getY(), unitPos.getX());
		unitTile.setUnit(unit);
	}

	public void addUnit(UnitBuilder unit) {
		units.add(unit);
		builders.add(unit);
		Position unitPos = unit.getPosition();
		Tile unitTile = map.getTile(unitPos.getY(), unitPos.getX());
		unitTile.setUnit(unit);
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
