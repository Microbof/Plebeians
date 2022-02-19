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

	// tab influence peut etre plutot dans city ? ou les deux ?
	private List<Tile> influence = new ArrayList<>();

	private Color color;

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

	public List<Tile> getInfluence() {
		return influence;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
