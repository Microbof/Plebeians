package engine.process;

import java.util.List;

import engine.Player;
import engine.map.Map;
import engine.unit.Unit;

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
