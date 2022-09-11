package za.co.codonorix.codopb;

import org.bukkit.entity.Player;
import za.co.codonorix.codopb.objects.ArenaObject;
import za.co.codonorix.codopb.objects.PlayerObject;

import java.util.HashMap;

public class GameData {
	public static HashMap<String, ArenaObject> arenas = new HashMap<>();
	public static HashMap<Player, PlayerObject> playerData = new HashMap<>();
}
