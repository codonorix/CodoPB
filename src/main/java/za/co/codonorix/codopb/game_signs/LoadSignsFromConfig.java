package za.co.codonorix.codopb.game_signs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import za.co.codonorix.codopb.CodoPB;
import za.co.codonorix.codopb.GameData;
import za.co.codonorix.codopb.objects.ArenaObject;
import za.co.codonorix.codopb.objects.PlayerObject;
import za.co.codonorix.codopb.objects.SignObject;

import java.util.ArrayList;
import java.util.Map;

public class LoadSignsFromConfig {
	public void loadSignsFromConfig() {
		FileConfiguration config = CodoPB.getInstance().getConfig();

		if (config.getConfigurationSection("arenas.signs") == null) return;

		Map<String, Object> getConfig = config.getConfigurationSection("arenas.signs").getValues(false);

		Object[] arenaObj = getConfig.keySet().toArray();

		for (int i = 0; i < arenaObj.length; i++) {
			Map<String, Object> arenaData = config.getConfigurationSection("arenas.maps." + arenaObj[i]).getValues(true);
			Object[] signDataList = arenaData.values().toArray();

			SignObject signObject = (SignObject) signDataList[0];
			GameData.joinSigns.add(signObject);
		}
	}
}
