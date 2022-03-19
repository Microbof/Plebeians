package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import configuration.GameConfiguration;
import engine.Camera;
import engine.Mouse;
import engine.building.City;
import engine.map.Map;
import engine.map.Minimap;
import engine.process.EntitiesManager;
import engine.unit.Unit;

public class GameDisplay extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private PaintStrategy paintStrategy = new PaintStrategy();
	
	private EntitiesManager manager;
	
	private Map map;
	
	private Minimap miniMap;
	
	private Camera camera;
	
	private Mouse mouse;
	

	// game states
	private int state;
	private int oldState;
	
	//Panels of the game
	private JPanel titleScreenPanel;
	private JPanel gamePanel;

	public GameDisplay(Map map, Camera camera, Mouse mouse, EntitiesManager manager) {
		this.map = map;
		this.camera = camera;
		this.mouse = mouse;
		this.manager = manager;
		
		this.state = GameConfiguration.IN_MENU;
		this.oldState = this.state;
		this.setLayout(new GridLayout(1,1));
		this.setOpaque(false);
				
		gamePanel = createGamePanel();
		gamePanel.setVisible(false);
		
		titleScreenPanel = createTitleScreenPanel();
		titleScreenPanel.setVisible(true);
		
		getMainPanel().add(titleScreenPanel);
		
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
		
		JButton newPartyButton = new JButton(new LaunchGame("Nouvelle Partie"));
		newPartyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newPartyButton.setMargin(new Insets(10, 20, 10, 20));
		
		JButton quitButton = new JButton(new ExitGameButton("Quitter le jeu"));
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setMargin(new Insets(10, 20, 10, 20));
	
		Dimension minSize = new Dimension(5, 10);
		Dimension prefSize = new Dimension(5, 20);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 20);
		

		titleScreenButtonsPanel.add(newPartyButton);
		titleScreenButtonsPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		titleScreenButtonsPanel.add(quitButton);
		titleScreenButtonsPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		

		titleScreenPanel.add(gameTitle);
		titleScreenPanel.add(titleScreenButtonsPanel);
		
		return titleScreenPanel;
	}
	
	
	private JPanel createGamePanel() {
//		GridLayout gridLayout = new GridLayout(4,3);
//		JPanel panel = new JPanel(gridLayout);
//		panel.setOpaque(false);
//		int gridPlacement = gridLayout.getColumns() * gridLayout.getRows();
//		for(int i = 0; i < gridPlacement; i++) {
//			if(i == gridPlacement-1) {
//				panel.add(new JButton(new NextTurnButton("next turn")));
//			} else{
//				JLabel label = new JLabel();
//				panel.add(label);
//			}
//		}
//		
		GridBagLayout gameLayout = new GridBagLayout();
		JPanel panel = new JPanel();
		panel.setLayout(gameLayout);
		GridBagConstraints c = new GridBagConstraints();
		JButton nexTurnButton = new JButton(new NextTurnButton("next turn"));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		panel.add(nexTurnButton, c);

		miniMap = new Minimap(map, manager);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(miniMap, c);
		
		JLabel DescriptionLabel = new JLabel("Description");
		c.gridx = 1;
		c.gridwidth = 3;
		c.gridy = 2;
		panel.add(DescriptionLabel);
		
		JLabel RessourcesLabel = new JLabel("Ressources");
		c.gridx = 1;
		c.gridwidth = 3;
		c.gridy = 0;
		panel.add(RessourcesLabel);
		
		JLabel nombreDeToursLabel = new JLabel("Nombre de tours");
		c.gridx = 1;
		c.gridwidth = 3;
		c.gridy = 0;
		panel.add(nombreDeToursLabel);
		
		JButton menuButton = new JButton("Menu");
		c.gridx = 2;
		c.gridwidth = 3;
		c.gridy = 0;
		panel.add(menuButton);
		
		panel.setOpaque(false);
		
		return panel;
	}
	
	private void manageState() {
		switch(state)
		{
			case GameConfiguration.IN_MENU:
				if(oldState == GameConfiguration.IN_OPTION)
				{
//					optionPanel.setVisible(false);
//					getMainPanel().remove(optionPanel);
				}
				else if(oldState == GameConfiguration.IN_PAUSE_MENU)
				{
//					pauseMenuPanel.setVisible(false);
//					getMainPanel().remove(pauseMenuPanel);
//					manager.clean();
//					camera.reset();
				}
				
				titleScreenPanel.setVisible(true);
				getMainPanel().add(titleScreenPanel);
				break;
				
			case GameConfiguration.IN_GAME:
				if(oldState == GameConfiguration.IN_PAUSE_MENU)
				{
//					pauseMenuPanel.setVisible(false);
//					getMainPanel().remove(pauseMenuPanel);
				}
				else if(oldState == GameConfiguration.IN_MENU)
				{
					titleScreenPanel.setVisible(false);
					getMainPanel().remove(titleScreenPanel);
				}
				gamePanel.setVisible(true);
				getMainPanel().add(gamePanel);
				break;
				
			case GameConfiguration.IN_OPTION:
				if(oldState == GameConfiguration.IN_PAUSE_MENU)
				{
//					gamePanel.setVisible(false);
//					pauseMenuPanel.setVisible(false);
//					getMainPanel().remove(gamePanel);
//					getMainPanel().remove(pauseMenuPanel);
				}
				else if(oldState == GameConfiguration.IN_MENU)
				{
					titleScreenPanel.setVisible(false);
					getMainPanel().remove(titleScreenPanel);
				}
//				optionPanel.setVisible(true);
//				getMainPanel().add(optionPanel);
				break;
				
			case GameConfiguration.IN_PAUSE_MENU:
				if(oldState == GameConfiguration.IN_OPTION)
				{
//					optionPanel.setVisible(false);
//					getMainPanel().remove(optionPanel);
				}
				else if(oldState == GameConfiguration.IN_GAME)
				{
//					gamePanel.setVisible(false);
//					getMainPanel().remove(gamePanel);
				}
//				pauseMenuPanel.setVisible(true);
//				getMainPanel().add(pauseMenuPanel);
				break;
			
			
			default:
				break;
		}
		getMainPanel().validate();
	}
	
	private class LaunchGame extends AbstractAction {

		private static final long serialVersionUID = 1L;
		
		public LaunchGame(String name) {
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {	
//			map = GameBuilder.buildMap(selectedMap, graphicsManager, manager);
//			
//			GameBuilder.buildFaction(manager, boxPlayer1.getSelectedIndex() + 1, boxPlayer2.getSelectedIndex() + 1, map, maxPopulation, startingMoney);
//			manager.setMap(map);
			
			gamePanel = createGamePanel();
			gamePanel.setVisible(false);
			
			oldState = state;
			state = GameConfiguration.IN_GAME;
			
			manageState();
		}
	}
	
	private class ExitGameButton extends AbstractAction {

		private static final long serialVersionUID = 1L;
		
		public ExitGameButton(String name) {
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
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
				paintStrategy.paint(unit.getPath(), camera, g);
			}
		}
		miniMap.repaint();
	}
	
}
