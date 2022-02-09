package engine.unit;

import engine.Entity;
import engine.Player;
import engine.Position;
import engine.Speed;
import engine.map.Map;

public class Unit extends Entity {

	private Position destination = null;
	private Speed speed = new Speed(0, 0);

	public Unit(int hp, int hpMax, String description, Position position, Player player) {
		super(hp, hpMax, description, position, player);
	}

	public void update(Map map) {
		if (destination != null) {
			System.out.println("unit dest = " + destination.getX() + "," + destination.getY());
			System.out.println("unit pos = " + this.getPosition().getX() + "," + this.getPosition().getY());
			if (this.getPosition().equals(destination)) {
				destination = null;
				speed.reset();
			} else {
				calculateSpeed(destination);
			}
		}
		this.getPosition().setX(this.getPosition().getX() + (int) this.getSpeed().getVx());
		this.getPosition().setY(this.getPosition().getY() + (int) this.getSpeed().getVy());
	}

	public void move(float vx, float vy) {
		System.out.println("unit speed : " + vx + "," + vy);
		speed.setVx(vx);
		speed.setVy(vy);
	}

	public void calculateSpeed(Position p) {
		this.setDestination(new Position(p.getX(), p.getY()));
		Position pos = this.getPosition();
		float vx, vy;
		if(pos.getX() < p.getX()) {
			vx = 1;
		}else if(pos.getX() > p.getX()) {
			vx = -1;
		} else {
			vx = 0;
		}
		if(pos.getY() < p.getY()) {
			vy = 1;
		}else if(pos.getY() > p.getY()) {
			vy = -1;
		} else {
			vy = 0;
		}
		this.move(vx, vy);
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

}
