package za.co.codonorix.codopb.arena_creator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArenaSetupInventoryInteractEvent implements Listener {
	@EventHandler
	public void onInvClickEvent(InventoryClickEvent event) {
		if(ArenaSetup.setupStage.containsKey((Player) event.getWhoClicked()))
			event.setCancelled(true);
	}
}
