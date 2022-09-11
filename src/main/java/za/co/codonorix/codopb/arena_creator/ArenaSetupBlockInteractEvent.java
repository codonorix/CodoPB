package za.co.codonorix.codopb.arena_creator;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import za.co.codonorix.codopb.CodoPB;

public class ArenaSetupBlockInteractEvent implements Listener {
	Location waitingLobbyLocation = null;
	Location waitingLobbyPlaceHolderLocation = null;
	Location teamOneLocation = null;
	Location teamTwoLocation = null;
	Location teamOnePlaceHolder = null;
	Location teamTwoPlaceHolder = null;

	@EventHandler
	public void onItemInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!(ArenaSetup.setupStage.containsKey(player))) return;
		if (event.getItem() == null) return;

		int stage = ArenaSetup.setupStage.get(player);
		PersistentDataContainer dataContainer = event.getItem().getItemMeta().getPersistentDataContainer();
		NamespacedKey confirmKey = new NamespacedKey(CodoPB.getInstance(), "CONFIRM");
		boolean completed = false;

		switch (stage) {
			case 3:
				if (event.getAction().isRightClick() && event.getItem().getItemMeta().getPersistentDataContainer().has(confirmKey)) {
					Location waitingLobbyConfirm = confirmWaitingLobby(event.getItem().getItemMeta().getPersistentDataContainer(), event.getPlayer());
					if(waitingLobbyConfirm != null) completed = true;
					else return;
				}

				if (!completed) {
					if (event.getClickedBlock() == null) {
						player.sendMessage(Component.text("Please look at a valid block.", TextColor.color(255, 0, 20)));
						return;
					}

					Location clickedBlockLocation = event.getClickedBlock().getLocation();
					setWaitingLobby(dataContainer, player, clickedBlockLocation);
				}else{
					ArenaSetup.waitingLobby = waitingLobbyPlaceHolderLocation;

					//Reseting the waiting lobby arena, it's set in the above line.
					waitingLobbyLocation = null;

					ArenaSetup.setupStage.replace(player, 4);
					ArenaSetup.firstRun = true;
				}
				break;
			case 4:
				if (event.getAction().isRightClick() && event.getItem().getItemMeta().getPersistentDataContainer().has(confirmKey)) {
					boolean teamLobbyConfirm = confirmTeamSpawns(event.getItem().getItemMeta().getPersistentDataContainer(), event.getPlayer());
					if(teamLobbyConfirm) completed = true;
					else return;
				}

				if (!completed) {
					if (event.getClickedBlock() == null) {
						player.sendMessage(Component.text("Please look at a valid block.", TextColor.color(255, 0, 20)));
						return;
					}

					Location clickedBlockLocation = event.getClickedBlock().getLocation();
					setTeamLobby(dataContainer, player, clickedBlockLocation);
				}else{
					ArenaSetup.spawnOne = teamOnePlaceHolder;
					ArenaSetup.spawnTwo = teamTwoPlaceHolder;

					//Reseting the waiting lobby arena, it's set in the above line.
					teamOnePlaceHolder = null;
					teamTwoPlaceHolder = null;

					ArenaSetup.setupStage.replace(player, 5);
					ArenaSetup.firstRun = true;
					return;
				}
				break;
		}
	}

	private Location setWaitingLobby(PersistentDataContainer dataContainer, Player player, Location clickedBlockLocation) {
		NamespacedKey waitingLobbyKey = new NamespacedKey(CodoPB.getInstance(), "SET_WAITING_LOBBY");

		if (dataContainer.has(waitingLobbyKey)) {
			waitingLobbyPlaceHolderLocation = clickedBlockLocation;
			player.sendMessage(Component.text("Waiting Lobby Position set!", TextColor.color(0, 255, 0)));

			return waitingLobbyLocation;
		}
		return null;
	}

	private Location confirmWaitingLobby(PersistentDataContainer dataContainer, Player player) {
		NamespacedKey confirmKey = new NamespacedKey(CodoPB.getInstance(), "CONFIRM");
		if (dataContainer.has(confirmKey)) {
			if (waitingLobbyPlaceHolderLocation == null) {
				player.sendMessage(Component.text("Please select a waiting lobby!", TextColor.color(255, 0, 0)));
				return null;
			}
			player.sendMessage(Component.text("Waiting Lobby set!", TextColor.color(0, 255, 0)));
			return waitingLobbyPlaceHolderLocation;
		}
		return null;
	}

	private Location setTeamLobby(PersistentDataContainer dataContainer, Player player, Location clickedBlockLocation) {
		NamespacedKey teamOneKey = new NamespacedKey(CodoPB.getInstance(), "SET_TEAM_ONE");
		NamespacedKey teamTwoKey = new NamespacedKey(CodoPB.getInstance(), "SET_TEAM_TWO");

		if (dataContainer.has(teamOneKey)) {
			teamOnePlaceHolder = clickedBlockLocation;
			player.sendMessage(Component.text("Team One Spawn Set", TextColor.color(0, 255, 0)));
			return teamOneLocation;
		}else if(dataContainer.has(teamTwoKey)){
			teamTwoPlaceHolder = clickedBlockLocation;
			player.sendMessage(Component.text("Team Two Spawn Set", TextColor.color(0, 255, 0)));
			return teamTwoLocation;
		}
		return null;
	}

	private boolean confirmTeamSpawns(PersistentDataContainer dataContainer, Player player) {
		NamespacedKey confirmKey = new NamespacedKey(CodoPB.getInstance(), "CONFIRM");
		if (dataContainer.has(confirmKey)) {
			if (teamOnePlaceHolder == null) {
				player.sendMessage(Component.text("Please select team ones spawn location!", TextColor.color(255, 0, 0)));
				return false;
			}else if (teamTwoPlaceHolder == null){
				player.sendMessage(Component.text("Please select team twos spawn location!", TextColor.color(255, 0, 0)));
				return false;
			}
			player.sendMessage(Component.text("Spawn locations set!", TextColor.color(0, 255, 0)));
			ArenaSetup.spawnOne = teamOnePlaceHolder;
			ArenaSetup.spawnTwo = teamTwoPlaceHolder;

			return true;
		}
		return false;
	}
}
