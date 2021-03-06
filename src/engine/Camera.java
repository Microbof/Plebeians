package engine;

import configuration.GameConfiguration;

public class Camera {

	private int x;

	private int y;

	private int rectX;

	private int rectY;

	private int rectW;

	private int rectH;

	private Speed speed;

	private int screenWidth;

	private int screenHeight;

	public Camera(int width, int height) {
		this.x = 0;
		this.y = 0;
		this.speed = new Speed();
		rectX = 10;
		rectY = 10;
		rectW = width - rectX - 20;
		rectH = height - rectY - 40;
		this.screenWidth = width;
		this.screenHeight = height;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void update() {
		x += this.getSpeed().getVx();
		y += this.getSpeed().getVy();
		if (x < 0) {
			x = 0;
			speed.setVx(0);
		} else if (x > GameConfiguration.TILE_SIZE * GameConfiguration.COLUMN_COUNT - screenWidth) {
			x = GameConfiguration.TILE_SIZE * GameConfiguration.COLUMN_COUNT - screenWidth;
			speed.setVx(0);
		}

		if (y < 0) {
			y = 0;
			speed.setVy(0);
		} else if (y > GameConfiguration.TILE_SIZE * GameConfiguration.LINE_COUNT - screenHeight) {
			y = GameConfiguration.TILE_SIZE * GameConfiguration.LINE_COUNT - screenHeight;
			speed.setVy(0);
		}
	}

	public void move(float vx, float vy) {
		this.getSpeed().setVx(vx);
		this.getSpeed().setVy(vy);
	}

	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public void reset() {
		x = 0;
		y = 0;
		speed.reset();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRectX() {
		return rectX;
	}

	public void setRectX(int rectX) {
		this.rectX = rectX;
	}

	public int getRectY() {
		return rectY;
	}

	public void setRectY(int rectY) {
		this.rectY = rectY;
	}

	public int getRectW() {
		return rectW;
	}

	public void setRectW(int rectW) {
		this.rectW = rectW;
	}

	public int getRectH() {
		return rectH;
	}

	public void setRectH(int rectH) {
		this.rectH = rectH;
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}

	public int getScreenHeight() {
		return this.screenHeight;
	}
}