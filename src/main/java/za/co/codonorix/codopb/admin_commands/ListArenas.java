package za.co.codonorix.codopb.admin_commands;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import za.co.codonorix.codopb.GameData;

public class ListArenas implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(!(sender instanceof Player))return false;
		Player player = (Player) sender;
		for(String key : GameData.arenas.keySet()) {
			player.sendMessage(key);
		}

		return false;
	}
}
