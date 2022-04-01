package engine.unit;

import java.util.ArrayList;
import java.util.List;

import engine.Entity;
import engine.Player;
import engine.Position;
import engine.Speed;
import engine.building.City;
import engine.map.Map;

public class Unit extends Entity {

	private Position destination = null;
	private Speed speed = new Speed(0, 0);
	private List<Position> path = new ArrayList<>();
	private int ap = 5;
	private int maxAp = 5;
	private boolean pendingAction = false;
	private int attack = 20;
	private int defense = 10;

	public Unit(int hp, int hpMax, String description, Position position, Player player, int maxAp) {
		super(hp, hpMax, description, position, player);
		this.maxAp = maxAp;
		ap = maxAp;
	}

	public void update(Map map) {

		if (ap > 0) {
			if (destination != null) {
				// System.out.println("unit dest = " + destination.getX() + "," +
				// destination.getY());
				// System.out.println("unit pos = " + this.getPosition().getX() + "," +
				// this.getPosition().getY());
				System.out.println("ap = " + ap);
				if (this.getPosition().equals(destination)) {
					if (path.isEmpty() == false) {
						destination = path.get(0);
						path.remove(0);
					} else {
						destination = null;
						pendingAction = false;
					}
					ap--;
					speed.reset();
				} else if (ap > 0) {
					calculateSpeed(destination);
				}
			} else if (!path.isEmpty() && pendingAction) {
				destination = path.get(0);
				path.remove(0);
			}
		}

		this.getPosition().setX(this.getPosition().getX() + (int) this.getSpeed().getVx());
		this.getPosition().setY(this.getPosition().getY() + (int) this.getSpeed().getVy());
	}

	public void move(float vx, float vy) {
		// System.out.println("unit speed : " + vx + "," + vy);
		speed.setVx(vx);
		speed.setVy(vy);
	}
	
	public void attack(Unit enemy) {
		this.setHp(this.getHp() - enemy.defense);
		enemy.setHp(enemy.getHp() - this.attack);
		System.out.println("attack !");
		this.ap -= 1;
	}
	
	public void attack(City enemy) {
		this.setHp(this.getHp() - enemy.getDefense());
		enemy.setHp(enemy.getHp() - this.attack);
		System.out.println("attack !");
		this.ap -= 1;
	}

	public void calculateSpeed(Position p) {
		this.setDestination(new Position(p.getX(), p.getY()));
		Position pos = this.getPosition();
		float vx, vy;
		if (pos.getX() < p.getX()) {
			vx = 1;
		} else if (pos.getX() > p.getX()) {
			vx = -1;
		} else {
			vx = 0;
		}
		if (pos.getY() < p.getY()) {
			vy = 1;
		} else if (pos.getY() > p.getY()) {
			vy = -1;
		} else {
			vy = 0;
		}
		this.move(vx, vy);
	}

	public void resetAp() {
		ap = maxAp;
	}

	public Position getDestination() {
		return destination;
	}

	public void setDestination(Position destination) {
		this.destination = destination;
	}

	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public List<Position> getPath() {
		return path;
	}

	public void addPath(Position position) {
		path.add(position);
	}

	public void removeLastPath() {
		path.remove(path.size() - 1);
	}

	public int getAp() {
		return ap;
	}

	public void removeAp(int i) {
		ap = ap - i;
	}

	public int getMaxAp() {
		return maxAp;
	}

	public boolean isPendingAction() {
		return pendingAction;
	}

	public void setPendingAction(boolean pendingAction) {
		this.pendingAction = pendingAction;
	}

}
