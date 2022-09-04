package za.co.codonorix.codopb.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import za.co.codonorix.codopb.arena_creator.ArenaSetup;

public class ArenaCreatorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(!(sender instanceof Player))return false;

		ArenaSetup arenaSetup = new ArenaSetup();

		arenaSetup.arenaSetup((Player) sender);

		return false;
	}
}
