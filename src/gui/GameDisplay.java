package gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import engine.Camera;
import engine.Mouse;
import engine.Player;
import engine.building.City;
import engine.map.Map;
import engine.process.EntitiesManager;
import engine.unit.Unit;

public class GameDisplay extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private PaintStrategy paintStrategy = new PaintStrategy();
	
	private EntitiesManager manager;

	
	private Map map;
	
	private Camera camera;
	
	private Mouse mouse;

	public GameDisplay(Map map, Camera camera, Mouse mouse, EntitiesManager manager) {
		this.map = map;
		this.camera = camera;
		this.mouse = mouse;
		this.manager = manager;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.paintStrategy == null) {
			paintStrategy = new PaintStrategy();
		}
		paintStrategy.paint(map, camera, g);
		
		Player player = manager.getPlayer();
		
		for (City city : player.getCities()) {
			paintStrategy.paint(city, camera, g);
		}
		for (Unit unit : player.getUnits()) {
			paintStrategy.paint(unit, camera, g);
		}
	}
	
}
