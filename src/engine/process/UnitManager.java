package engine.process;

import engine.Player;
import engine.map.Map;

public class UnitManager {
	private Map map;
	private Player player;
	
	public UnitManager(Map map) {
		this.map = map;
	}
	
	public void set(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}


}
