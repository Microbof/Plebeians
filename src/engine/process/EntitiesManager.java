package engine.process;

import engine.Player;
import engine.map.Map;

public class EntitiesManager {
	
	private Map map;
	
	private Player player;

	public EntitiesManager(Map map) {
		this.map = map;
	}

	public void set(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

}
