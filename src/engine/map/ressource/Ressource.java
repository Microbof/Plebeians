package engine.map.ressource;

public abstract class Ressource {

	private int production;
	
	private int food;
	
	private String description;
	
	private int id;

	public Ressource(int id, String description, int production, int food) {
		this.id = id;
		this.description = description;
		this.production = production;
		this.food = food;
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
	
	
	
}
