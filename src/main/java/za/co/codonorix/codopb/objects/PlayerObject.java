package za.co.codonorix.codopb.objects;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerObject {
	private Player player;
	private ArrayList<String> unlockedHats;
	private String selectedHat;
	private HashMap<String, Integer> perks;
	private ArrayList<String> unlockedKillsteaks;
	private String killPrefix;
	private ArrayList<String> unlockedKillPrefixs;
	private int totalcoins;
	private int totalKills;
	private int totalWins;

	public PlayerObject(Player player, ArrayList<String> unlockedHats, String selectedHat, HashMap<String, Integer> perks, ArrayList<String> unlockedKillsteaks, String killPrefix, ArrayList<String> unlockedKillPrefixs, int totalcoins, int totalKills, int totalWins) {
		this.player = player;
		this.unlockedHats = unlockedHats;
		this.selectedHat = selectedHat;
		this.perks = perks;
		this.unlockedKillsteaks = unlockedKillsteaks;
		this.killPrefix = killPrefix;
		this.unlockedKillPrefixs = unlockedKillPrefixs;
		this.totalcoins = totalcoins;
		this.totalKills = totalKills;
		this.totalWins = totalWins;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<String> getUnlockedHats() {
		return unlockedHats;
	}

	public void setUnlockedHats(ArrayList<String> unlockedHats) {
		this.unlockedHats = unlockedHats;
	}

	public String getSelectedHat() {
		return selectedHat;
	}

	public void setSelectedHat(String selectedHat) {
		this.selectedHat = selectedHat;
	}

	public HashMap<String, Integer> getPerks() {
		return perks;
	}

	public void setPerks(HashMap<String, Integer> perks) {
		this.perks = perks;
	}

	public ArrayList<String> getUnlockedKillsteaks() {
		return unlockedKillsteaks;
	}

	public void setUnlockedKillsteaks(ArrayList<String> unlockedKillsteaks) {
		this.unlockedKillsteaks = unlockedKillsteaks;
	}

	public String getKillPrefix() {
		return killPrefix;
	}

	public void setKillPrefix(String killPrefix) {
		this.killPrefix = killPrefix;
	}

	public ArrayList<String> getUnlockedKillPrefixs() {
		return unlockedKillPrefixs;
	}

	public void setUnlockedKillPrefixs(ArrayList<String> unlockedKillPrefixs) {
		this.unlockedKillPrefixs = unlockedKillPrefixs;
	}

	public int getTotalcoins() {
		return totalcoins;
	}

	public void setTotalcoins(int totalcoins) {
		this.totalcoins = totalcoins;
	}

	public int getTotalKills() {
		return totalKills;
	}

	public void setTotalKills(int totalKills) {
		this.totalKills = totalKills;
	}

	public int getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(int totalWins) {
		this.totalWins = totalWins;
	}
}
