package gui;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	private JPanel gamePanel;

	public GameDisplay(Map map, Camera camera, Mouse mouse, EntitiesManager manager) {
		this.map = map;
		this.camera = camera;
		this.mouse = mouse;
		this.manager = manager;
		this.setLayout(new GridLayout(1,1));
		this.setOpaque(false);
		gamePanel = createGamePanel();
		gamePanel.setVisible(true);
		this.add(gamePanel);
	}
	
	private JPanel createGamePanel() {
		GridLayout gridLayout = new GridLayout(4,3);
		JPanel panel = new JPanel(gridLayout);
		panel.setOpaque(false);
		int gridPlacement = gridLayout.getColumns() * gridLayout.getRows();
		for(int i = 0; i < gridPlacement; i++) {
			if(i == gridPlacement-1) {
				panel.add(new JButton(new NextTurnButton("next turn")));
			} else{
				JLabel label = new JLabel();
				panel.add(label);
			}
		}
		
		return panel;
	}
	
	private class NextTurnButton extends AbstractAction{

		private static final long serialVersionUID = 1L;
		
		public NextTurnButton(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.nextTurn();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.paintStrategy == null) {
			paintStrategy = new PaintStrategy();
		}
		paintStrategy.paint(map, camera, g);
		
		for (City city : manager.getCities()) {
			paintStrategy.paint(city, camera, g);
		}
		for (Unit unit : manager.getUnits()) {
			paintStrategy.paint(unit, camera, g);
			if(unit.getPath() != null && unit.getPlayer().equals(manager.getCurrentPlayer())) {
				paintStrategy.paint(manager.getUnits(), manager.getCities(), g);
				paintStrategy.paint(unit.getPath(), camera, g);
			}
		}
	}
	
}
