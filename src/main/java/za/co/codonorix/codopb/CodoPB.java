package za.co.codonorix.codopb;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import za.co.codonorix.codopb.arena_creator.*;
import za.co.codonorix.codopb.commands.ArenaCreatorCommand;

public final class CodoPB extends JavaPlugin {
	private static CodoPB instance;


	@Override
	public void onEnable() {
		instance = this;
		// Plugin startup logic
		this.getCommand("createArena").setExecutor(new ArenaCreatorCommand());

		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupChatEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupBlockInteractEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupBlockPlaceEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupInventoryInteractEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupPlayerDropEvent(), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static CodoPB getInstance(){
		return instance;
	}
}
