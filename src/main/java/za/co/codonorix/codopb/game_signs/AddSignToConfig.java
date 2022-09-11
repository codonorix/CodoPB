package za.co.codonorix.codopb.game_signs;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import za.co.codonorix.codopb.CodoPB;
import za.co.codonorix.codopb.GameData;
import za.co.codonorix.codopb.objects.ArenaObject;
import za.co.codonorix.codopb.objects.SignObject;

public class AddSignToConfig {
	public void addSignToConfig(SignObject signObject){
		FileConfiguration config = CodoPB.getInstance().getConfig();

		int id = GameData.joinSigns.size();
		String ymlListName = "arenas.signs." + id;

		config.set(ymlListName + ".signObjectLocation", signObject.getLocation());
		config.set(ymlListName + ".signObjectArena", signObject.getArenaObject());

		CodoPB.getInstance().saveConfig();
	}
}
