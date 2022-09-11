package za.co.codonorix.codopb.configs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import za.co.codonorix.codopb.CodoPB;
import za.co.codonorix.codopb.GameData;
import za.co.codonorix.codopb.objects.ArenaObject;
import za.co.codonorix.codopb.objects.PlayerObject;

import java.util.ArrayList;
import java.util.Map;

public class LoadArenas {
	public void arenaCreatorLoader() {
		FileConfiguration config = CodoPB.getInstance().getConfig();

		if(config.getConfigurationSection("arenas.maps") == null) return;

		Map<String, Object> getConfig = config.getConfigurationSection("arenas.maps").getValues(false);

		Object[] arenaObj = getConfig.keySet().toArray();
		for(int i = 0; i < arenaObj.length; i++) {
			Map<String, Object> arenaData = config.getConfigurationSection("arenas.maps." + arenaObj[i]).getValues(true);
			Object[] arenaDataList = arenaData.values().toArray();

			Component name = Component.text((String) arenaDataList[0]);
			int minPlayers = (int) arenaDataList[1];
			int maxPlayers = (int) arenaDataList[2];
			ArrayList<PlayerObject> teamOne = (ArrayList<PlayerObject>) arenaDataList[3];
			ArrayList<PlayerObject> teamTwo = (ArrayList<PlayerObject>) arenaDataList[4];
			Location waitingLobby = (Location) arenaDataList[5];
			Location spawn1 = (Location) arenaDataList[6];
			Location spawn2 = (Location) arenaDataList[7];
			boolean running = (Boolean) arenaDataList[8];

			ArenaObject newArena = new ArenaObject(name, minPlayers, maxPlayers, teamOne, teamTwo, waitingLobby, spawn1, spawn2, running, 0);

			TextComponent arenaName = (TextComponent) name;
			GameData.arenas.put(arenaName.content(), newArena);

		}
	}
}
