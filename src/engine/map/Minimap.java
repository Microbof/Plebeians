package engine.map;

import engine.Camera;
import engine.process.EntitiesManager;
import gui.PaintStrategy;

import javax.swing.*;
import java.awt.*;

public class Minimap extends JPanel{
	private PaintStrategy paintStrategy;
	private Map map;
	private EntitiesManager manager;
	private Camera camera;

	public Minimap(Map map, EntitiesManager manager, Camera camera) {
		this.map = map;
		this.manager = manager;
		this.camera = camera;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(paintStrategy == null){
			paintStrategy = new PaintStrategy();
		}
		paintStrategy.paint(this, map, manager, camera, g);
	}
}
