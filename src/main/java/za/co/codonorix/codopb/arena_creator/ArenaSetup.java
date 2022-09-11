package za.co.codonorix.codopb.arena_creator;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import za.co.codonorix.codopb.CodoPB;
import za.co.codonorix.codopb.configs.AddArenaToConfig;
import za.co.codonorix.codopb.helper_classes.ItemCreator;
import za.co.codonorix.codopb.objects.ArenaObject;
import za.co.codonorix.codopb.objects.PlayerObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ArenaSetup {
	protected static HashMap<Player, Integer> setupStage = new HashMap<>();
	protected static HashMap<Player, ItemStack[]> playerInventories = new HashMap<>();
	protected static boolean firstRun = true;

	protected static Component name;
	protected static int maxPlayers;
	protected static int minPlayers;
	protected static Location waitingLobby;
	protected static Location spawnOne;
	protected static Location spawnTwo;

	public void arenaSetup(Player player) {
		TextComponent header = Component.text("═══════════════════════════════════").color(TextColor.color(177, 21, 194));

		if(setupStage.containsKey(player)) {
			player.sendMessage(Component.text("You are already in the arena setup! Enter -1 to leave.").color(TextColor.color(255, 0, 0)));
		}else{
			setupStage.put(player, 0);
			playerInventories.put(player, player.getInventory().getContents());
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				int stage = setupStage.get(player);
				ItemStack confirmItem = new ItemCreator().itemBuilder("Confirm", TextColor.color(0, 255, 7), Material.EMERALD_BLOCK, null, -1, "CONFIRM");
				ItemStack fillerItem = new ItemCreator().itemBuilder("", TextColor.color(0, 255, 7), Material.BLACK_STAINED_GLASS_PANE, null, -1, "DO_NOT_USE");

				if(firstRun) {
					switch (stage) {
						case 0:
							player.sendMessage(header);
							player.sendMessage(Component.text("Enter arena name: "));
							firstRun = false;
							break;
						case 1:
							player.sendMessage(header);
							player.sendMessage(Component.text("Enter the maximum number of players: "));
							firstRun = false;
							break;
						case 2:
							player.sendMessage(header);
							player.sendMessage(Component.text("Enter the minimum number of players: "));
							firstRun = false;
							break;
						case 3:
							player.sendMessage(header);
							player.sendMessage(Component.text("Select waiting lobby location: "));
							ItemStack waitingLobbyItem = new ItemCreator().itemBuilder("Set Waiting Lobby", TextColor.color(255, 166, 12), Material.CLOCK, null, -1, "SET_WAITING_LOBBY");

							for(int i = 0; i < 9; i++) {
								player.getInventory().setItem(i, fillerItem);
							}

							player.getInventory().setItem(0, waitingLobbyItem);
							player.getInventory().setItem(8, confirmItem);

							firstRun = false;
							break;
						case 4:
							player.sendMessage(header);
							player.sendMessage(Component.text("Select Team Spawn Locations: "));
							ItemStack teamOne = new ItemCreator().itemBuilder("Set Team One", TextColor.color(255, 166, 12), Material.STICK, null, -1, "SET_TEAM_ONE");
							ItemStack teamTwo = new ItemCreator().itemBuilder("Set Team Two", TextColor.color(255, 166, 12), Material.BAMBOO, null, -1, "SET_TEAM_TWO");

							for(int i = 0; i < 9; i++) {
								player.getInventory().setItem(i, fillerItem);
							}

							player.getInventory().setItem(0, teamOne);
							player.getInventory().setItem(1, teamTwo);
							player.getInventory().setItem(8, confirmItem);

							firstRun = false;
							break;
						case 5:
							setupStage.remove(player);

							player.getInventory().clear();
							player.getInventory().setContents(playerInventories.get(player));
							playerInventories.remove(player);

							ArenaObject arenaObject = new ArenaObject(name, minPlayers, maxPlayers, new ArrayList<PlayerObject>(), new ArrayList<PlayerObject>(), waitingLobby, spawnOne, spawnTwo, false);
							new AddArenaToConfig().createArena(arenaObject);
							firstRun = true;
							player.sendMessage("Arena created!");
							cancel();
							break;
						case -1:
							setupStage.remove(player);
							firstRun = true;
							player.sendMessage(Component.text("Exiting arena creator.").color(TextColor.color(255, 0, 255)));
							player.getInventory().clear();
							cancel();
							break;
					}
				}
			}
		}.runTaskTimer(CodoPB.getInstance(), 1,1);
	}
}
