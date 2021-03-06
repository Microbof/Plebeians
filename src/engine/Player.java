package engine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import engine.building.Building;
import engine.building.City;
import engine.map.Tile;
import engine.unit.Unit;

public class Player {

	private int score;
	
	private String name;

	// tab influence peut etre plutot dans city ? ou les deux ?
	private List<Tile> influence = new ArrayList<>();

	private Color color;

	public Player() {
		score = 0;
	}
	
	public Player(String name) {
		this.setName(name);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// ajouter les fonction add

	public List<Tile> getInfluence() {
		return influence;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
