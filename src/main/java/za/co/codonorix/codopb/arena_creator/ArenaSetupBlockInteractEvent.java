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

import java.util.ArrayList;

public class ArenaSetupBlockInteractEvent implements Listener {
	Location waitingLobbyLocation = null;
	Location placeHolderLocation = null;

	@EventHandler
	public void onItemInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!(ArenaSetup.setupStage.containsKey(player))) return;
		if (event.getItem() == null) return;

		int stage = ArenaSetup.setupStage.get(player);
		PersistentDataContainer dataContainer = event.getItem().getItemMeta().getPersistentDataContainer();

		switch (stage) {
			case 3:
				if (event.getClickedBlock() == null) {
					player.sendMessage(Component.text("Please look at a valid block.", TextColor.color(255, 0, 20)));
					return;
				}
				Location clickedBlockLocation = event.getClickedBlock().getLocation();
				Location waitingLobbyLocation = waitingLobby(dataContainer, player, clickedBlockLocation);

				if (waitingLobbyLocation == null) {
					return;
				}

				ArenaSetup.waitingLobby = waitingLobbyLocation;
				waitingLobbyLocation = null;
				ArenaSetup.setupStage.replace(player, 4);

				break;
		}
	}

	private Location waitingLobby(PersistentDataContainer dataContainer, Player player, Location clickedBlockLocation) {
		NamespacedKey waitingLobbyKey = new NamespacedKey(CodoPB.getInstance(), "SET_WAITING_LOBBY");
		NamespacedKey confirmKey = new NamespacedKey(CodoPB.getInstance(), "CONFIRM");


		if (dataContainer.has(waitingLobbyKey)) {
			placeHolderLocation = clickedBlockLocation;
			player.sendMessage(Component.text("Waiting Lobby Position set!", TextColor.color(0, 255, 0)));

			return waitingLobbyLocation;
		}

		if (dataContainer.has(confirmKey)) {
			if (placeHolderLocation == null) {
				player.sendMessage(Component.text("Please select a waiting lobby!", TextColor.color(255, 0, 0)));
				return null;
			}
			player.sendMessage(Component.text("Waiting Lobby set!", TextColor.color(0, 255, 0)));
			return placeHolderLocation;
		}
		return null;
	}
}
