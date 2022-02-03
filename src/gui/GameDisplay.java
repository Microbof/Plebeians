package gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import engine.Camera;
import engine.Mouse;
import engine.map.Map;

public class GameDisplay extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private PaintStrategy paintStrategy = null;
	
	private Map map;
	
	private Camera camera;
	
	private Mouse mouse;

	public GameDisplay(Camera camera, Mouse mouse) {
		this.map = new Map(100, 100);
		this.camera = camera;
		this.mouse = mouse;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.paintStrategy == null) {
			paintStrategy = new PaintStrategy();
		}
		paintStrategy.paint(map, camera, g);
	}
	
}
