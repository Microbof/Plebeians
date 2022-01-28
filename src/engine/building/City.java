package engine.building;

import engine.Position;

public class City extends Building{
	
	private String name;

	public City(int hp, int hpMax, String description, Position position, String name) {
		super(hp, hpMax, description, position);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
