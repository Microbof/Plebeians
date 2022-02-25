package engine.map;

import engine.Player;
import engine.building.Building;
import engine.map.ressource.Ressource;
import engine.unit.Unit;

public class Tile {

	private int line;

	private int column;

	private int id;

	private boolean solid;

	private Biome biome;

	private Building building;

	private Ressource ressource;

	private Unit unit;

	private Player owner = null;

	public Tile(int line, int column, Biome biome) {
		this.line = line;
		this.column = column;
		this.solid = false;
		this.biome = biome;
		this.building = null;
		this.ressource = null;
		this.unit = null;
	}

	public int getLine() {
		return this.line;
	}

	public int getColumn() {
		return this.column;
	}

	public int getId() {
		return this.id;
	}

	public boolean isSolid() {
		return this.solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public Biome getBiome() {
		return biome;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

}