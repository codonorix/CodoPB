package za.co.codonorix.codopb.objects;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;

import java.util.ArrayList;

public class ArenaObject {
	private Component name;
	private int minPlayers;
	private int maxPlayers;
	private ArrayList<PlayerObject> teamOne;
	private ArrayList<PlayerObject> teamTwo;
	private Location waitingLobby;
	private Location spawnOne;
	private Location spawnTwo;
	private boolean running;

	public ArenaObject(Component name, int minPlayers, int maxPlayers, ArrayList<PlayerObject> teamOne, ArrayList<PlayerObject> teamTwo, Location waitingLobby, Location spawnOne, Location spawnTwo, boolean running) {
		this.name = name;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.waitingLobby = waitingLobby;
		this.spawnOne = spawnOne;
		this.spawnTwo = spawnTwo;
		this.running = running;
	}

	public Component getName() {
		return name;
	}

	public void setName(Component name) {
		this.name = name;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public ArrayList<PlayerObject> getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(ArrayList<PlayerObject> teamOne) {
		this.teamOne = teamOne;
	}

	public ArrayList<PlayerObject> getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(ArrayList<PlayerObject> teamTwo) {
		this.teamTwo = teamTwo;
	}

	public Location getWaitingLobby() {
		return waitingLobby;
	}

	public void setWaitingLobby(Location waitingLobby) {
		this.waitingLobby = waitingLobby;
	}

	public Location getSpawnOne() {
		return spawnOne;
	}

	public void setSpawnOne(Location spawnOne) {
		this.spawnOne = spawnOne;
	}

	public Location getSpawnTwo() {
		return spawnTwo;
	}

	public void setSpawnTwo(Location spawnTwo) {
		this.spawnTwo = spawnTwo;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
