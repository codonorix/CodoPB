package za.co.codonorix.codopb.arena_creator;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;

public class ArenaSetupChatEvent implements Listener {
	@EventHandler
	public void onPlayerChat(AsyncChatEvent event) {
		if(!(ArenaSetup.setupStage.containsKey(event.getPlayer()))) return;
		event.setCancelled(true);

		Player player = event.getPlayer();
		int stage = ArenaSetup.setupStage.get(player);
		HashMap<Player, Integer> arenaStages = ArenaSetup.setupStage;

		TextComponent messageAsString = (TextComponent) event.message();

		if(messageAsString.content().equals("-1")) {
			ArenaSetup.setupStage.replace(player, -1);
			ArenaSetup.firstRun = true;
			return;
		}

		switch (stage) {
			case 0:
				ArenaSetup.name = event.message().asComponent();
				arenaStages.replace(player, 1);
				break;
			case 1:
				int maxPlayers = getMaxPlayers(player, event.message());

				if(maxPlayers == -1)
					return;

				ArenaSetup.maxPlayers = maxPlayers;
				arenaStages.replace(player, 2);
				break;
			case 2:
				int minPlayer = getMinPlayers(player, event.message());

				if(minPlayer == -1)
					return;

				ArenaSetup.minPlayers = minPlayer;
				arenaStages.replace(player, 3);
				break;
		}

		ArenaSetup.firstRun = true;
	}

	private int getMaxPlayers(Player player, Component message) {
		TextComponent maxPlayerTest = (TextComponent) message;
		int maxPlayers = -1;

		try {
			maxPlayers = Integer.parseInt(maxPlayerTest.content());

			if(maxPlayers < 2) {
				player.sendMessage(Component.text("A game requires at least a maximum of two players.").color(TextColor.color(255,0,0)));
				return -1;
			}

			if(maxPlayers % 2 != 0) {
				player.sendMessage(Component.text("A game requires an even amount of players.").color(TextColor.color(255,0,0)));
				return -1;
			}

		}catch (Exception ex) {
			player.sendMessage(Component.text("Please enter a valid number"));
			return -1;
		}
		return maxPlayers;
	}

	private int getMinPlayers(Player player, Component message) {
		TextComponent maxPlayerTest = (TextComponent) message;
		int minPlayers = -1;

		try {
			minPlayers = Integer.parseInt(maxPlayerTest.content());

			if(minPlayers < 2) {
				player.sendMessage(Component.text("A game requires at least two players.").color(TextColor.color(255,0,0)));
				return -1;
			}

			if(minPlayers % 2 != 0) {
				player.sendMessage(Component.text("A game requires an even amount of players.").color(TextColor.color(255,0,0)));
				return -1;
			}

			if(minPlayers > ArenaSetup.maxPlayers) {
				player.sendMessage(Component.text("You can't have more than the max number of players!").color(TextColor.color(255,0,0)));
				return -1;
			}

		}catch (Exception ex) {
			player.sendMessage(Component.text("Please enter a valid number"));
			return -1;
		}
		return minPlayers;
	}
}
