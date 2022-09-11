package za.co.codonorix.codopb.game_signs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import za.co.codonorix.codopb.GameData;
import za.co.codonorix.codopb.objects.ArenaObject;
import za.co.codonorix.codopb.objects.SignObject;

public class CreateGameSign implements Listener {
	@EventHandler
	public void signPlaceEvent(SignChangeEvent event) {
		Block block = event.getBlock();
		TextComponent signTitle = (TextComponent) event.line(0);
		TextComponent arenaName = (TextComponent) event.line(1);

		if (block.getState() instanceof Sign && signTitle.content().equalsIgnoreCase("[pb]")) {
			if (GameData.arenas.containsKey(arenaName.content())) {
				ArenaObject arena = GameData.arenas.get(arenaName.content());

				event.line(0, Component.text("[JOIN]", TextColor.color(0, 255, 0)));
				event.line(1, Component.text(arenaName.content(), TextColor.color(0, 0, 0)));
				event.line(2, Component.text(arena.getCurrentPlayers() + "/" + arena.getMaxPlayers()));

				SignObject signObject = new SignObject(block.getLocation(), arena);

				GameData.joinSigns.add(signObject);
				new AddSignToConfig().addSignToConfig(signObject);
			} else {
				event.line(0, Component.text("[ERROR]", TextColor.color(255, 0, 0)));
				event.line(1, Component.text("Arena not found", TextColor.color(0, 0, 0)));
				event.line(2, Component.text("use /listarenas to see", TextColor.color(0, 0, 0)));
				event.line(3, Component.text("a full list of arenas.", TextColor.color(0, 0, 0)));
			}
		}
	}
}
