package engine;


public class Entity {

	private int hp;

	private int hpMax;

	private String description;

	private Position position;

	private boolean selected;
	
	private Player player;

	public Entity(int hp, int hpMax, String description, Position position, Player player) {
		this.hp = hp;
		this.hpMax = hpMax;
		this.description = description;
		this.position = position;
		this.selected = false;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Player getPlayer() {
		return player;
	}

}