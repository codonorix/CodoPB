package za.co.codonorix.codopb.arena_creator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ArenaSetupBlockPlaceEvent implements Listener {
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(ArenaSetup.setupStage.containsKey(event.getPlayer()))
			event.setCancelled(true);
	}
}
