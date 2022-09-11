package za.co.codonorix.codopb.configs;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import za.co.codonorix.codopb.CodoPB;
import za.co.codonorix.codopb.objects.ArenaObject;

public class AddArenaToConfig {
	public void createArena(ArenaObject arenaObject) {
		FileConfiguration config = CodoPB.getInstance().getConfig();

		TextComponent textComponent = (TextComponent) arenaObject.getName();
		String name = textComponent.content();
		String ymlListName = "arenas.maps." + name;

		config.set(ymlListName + ".name", name);
		config.set(ymlListName + ".minPlayers", arenaObject.getMinPlayers());
		config.set(ymlListName + ".maxPlayers", arenaObject.getMaxPlayers());
		config.set(ymlListName + ".teamOne", arenaObject.getTeamOne());
		config.set(ymlListName + ".teamTwo", arenaObject.getTeamTwo());
		config.set(ymlListName + ".waitingLobby", arenaObject.getWaitingLobby());
		config.set(ymlListName + ".spawnOne", arenaObject.getSpawnOne());
		config.set(ymlListName + ".spawnTwo", arenaObject.getSpawnTwo());
		config.set(ymlListName + ".running", arenaObject.isRunning());

		CodoPB.getInstance().saveConfig();

	}
}
