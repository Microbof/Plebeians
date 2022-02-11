package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import configuration.GameConfiguration;
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
	
	// game states
	private int state;
	private int oldState;
	
	//Panels of the game
	private JPanel titleScreenPanel;
	

	public GameDisplay(Map map, Camera camera, Mouse mouse, EntitiesManager manager) {
		this.map = map;
		this.camera = camera;
		this.mouse = mouse;
		this.manager = manager;
		
		this.state = GameConfiguration.IN_MENU;
		this.oldState = this.state;
		this.setLayout(new GridLayout(1,1));
		this.setOpaque(false);
				
		titleScreenPanel = createTitleScreenPanel();
		titleScreenPanel.setVisible(true);
		
		this.add(titleScreenPanel);
	}
	
	public JPanel getMainPanel() {
		return this;
	}
	
	public JPanel createTitleScreenPanel() {
		JPanel titleScreenPanel = new JPanel(new GridLayout(2,1));
		JPanel titleScreenButtonsPanel = new JPanel();
		titleScreenButtonsPanel.setLayout(new BoxLayout(titleScreenButtonsPanel, BoxLayout.PAGE_AXIS));

		titleScreenButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel gameTitle = new JLabel("<html><h1>Plebeians</h1></html>");
//		gameTitle.setFont(new Font("Sans Serif", Font.ROMAN_BASELINE, 20));
		gameTitle.setAlignmentX(SwingConstants.CENTER);
		gameTitle.setAlignmentY(SwingConstants.CENTER);
		
		gameTitle.setHorizontalAlignment(JLabel.CENTER);
		gameTitle.setVerticalAlignment(JLabel.CENTER);
		
		JButton continueButton = new JButton("Continuer");
		continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		continueButton.setMargin(new Insets(10, 20, 10, 20));
		
		JButton newPartyButton = new JButton("Nouvelle Partie");
		newPartyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newPartyButton.setMargin(new Insets(10, 20, 10, 20));
		
		JButton parametersButton = new JButton("Paramètres");
		parametersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		parametersButton.setMargin(new Insets(10, 20, 10, 20));
		
		JButton quitButton = new JButton("Quitter le jeu");
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setMargin(new Insets(10, 20, 10, 20));
	
		Dimension minSize = new Dimension(5, 10);
		Dimension prefSize = new Dimension(5, 20);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 20);
		

		titleScreenButtonsPanel.add(continueButton);
		titleScreenButtonsPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		titleScreenButtonsPanel.add(newPartyButton);
		titleScreenButtonsPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		titleScreenButtonsPanel.add(parametersButton);
		titleScreenButtonsPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		titleScreenButtonsPanel.add(quitButton);
		titleScreenButtonsPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		

		titleScreenPanel.add(gameTitle);
		titleScreenPanel.add(titleScreenButtonsPanel);
		
		return titleScreenPanel;
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
			if(unit.getPath() != null) {
				paintStrategy.paint(unit.getPath(), camera, g);
			}
		}
	}
	
}
