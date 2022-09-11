package za.co.codonorix.codopb.objects;

import org.bukkit.Location;

public class SignObject {
	private Location location;
	private ArenaObject arenaObject;

	public SignObject(Location location, ArenaObject arenaObject) {
		this.location = location;
		this.arenaObject = arenaObject;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArenaObject getArenaObject() {
		return arenaObject;
	}

	public void setArenaObject(ArenaObject arenaObject) {
		this.arenaObject = arenaObject;
	}
}
