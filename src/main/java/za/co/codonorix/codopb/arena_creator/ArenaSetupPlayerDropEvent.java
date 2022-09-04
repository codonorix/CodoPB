package za.co.codonorix.codopb.arena_creator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ArenaSetupPlayerDropEvent implements Listener {
	@EventHandler
	public void dropEvent(PlayerDropItemEvent event) {
		if(ArenaSetup.setupStage.containsKey(event.getPlayer()))
			event.setCancelled(true);
	}
}
