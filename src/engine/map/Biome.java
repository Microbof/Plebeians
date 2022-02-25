package engine.map;

public class Biome {
	
	private int production;
	
	private int food;
	
	private String description;
	
	private int id;

	private boolean accessible;

	public Biome(int id, String description, int production, int food, boolean accessible) {
		this.id = id;
		this.description = description;
		this.production = production;
		this.food = food;
		this.accessible = accessible;
	}

	public int getProduction() {
		return production;
	}

	public void setProduction(int production) {
		this.production = production;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public boolean isAccessible() {
		return accessible;
	}
	
	
	
	

}
