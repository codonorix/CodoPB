package za.co.codonorix.codopb.game_signs;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import za.co.codonorix.codopb.CodoPB;
import za.co.codonorix.codopb.GameData;
import za.co.codonorix.codopb.objects.ArenaObject;

import java.util.Set;

public class DestroyGameSign implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		if (block.getState() instanceof Sign) {
			for(int i = 0; i < GameData.joinSigns.size(); i++) {
				if (block.getLocation() == GameData.joinSigns.get(i).getLocation()) {
					event.getPlayer().sendMessage("Sign destroyed");
					int id = i + 1;
					CodoPB.getInstance().getConfig().set("arenas.signs." + id, null);
					GameData.joinSigns.remove(i);

					CodoPB.getInstance().saveConfig();
					break;
				}
			}
		}
	}
}
