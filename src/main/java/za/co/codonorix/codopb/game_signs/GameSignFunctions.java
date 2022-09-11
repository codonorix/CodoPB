package za.co.codonorix.codopb.game_signs;

import org.bukkit.scheduler.BukkitRunnable;
import za.co.codonorix.codopb.CodoPB;

public class GameSignFunctions {
	public void loadSigns() {
		new BukkitRunnable() {
			@Override
			public void run() {

			}
		}.runTaskTimer(CodoPB.getInstance(), 20 * 5, 1);
	}
}