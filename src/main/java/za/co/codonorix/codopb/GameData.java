package za.co.codonorix.codopb;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import za.co.codonorix.codopb.objects.ArenaObject;
import za.co.codonorix.codopb.objects.PlayerObject;
import za.co.codonorix.codopb.objects.SignObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameData {
	public static HashMap<String, ArenaObject> arenas = new HashMap<>();
	public static HashMap<Player, PlayerObject> playerData = new HashMap<>();
	public static ArrayList<SignObject> joinSigns = new ArrayList<>();
}
