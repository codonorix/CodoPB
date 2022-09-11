package za.co.codonorix.codopb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import za.co.codonorix.codopb.admin_commands.ListArenas;
import za.co.codonorix.codopb.arena_creator.*;
import za.co.codonorix.codopb.commands.ArenaCreatorCommand;
import za.co.codonorix.codopb.configs.LoadArenas;
import za.co.codonorix.codopb.game_signs.CreateGameSign;
import za.co.codonorix.codopb.game_signs.DestroyGameSign;
import za.co.codonorix.codopb.game_signs.LoadSignsFromConfig;

import java.io.File;


public final class CodoPB extends JavaPlugin {
	private static CodoPB instance;

	@Override
	public void onEnable() {
		instance = this;
		// Plugin startup logic
		FileConfiguration config = this.getConfig();
		config.addDefault("arenas.maps", null);
		config.addDefault("arenas.signs", null);
		new LoadArenas().arenaCreatorLoader();
		new LoadSignsFromConfig().loadSignsFromConfig();

		System.out.println("===================");
		System.out.println(GameData.joinSigns);
		System.out.println("====================");

		this.getCommand("createArena").setExecutor(new ArenaCreatorCommand());
		this.getCommand("listarenas").setExecutor(new ListArenas());

		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupChatEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupBlockInteractEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupBlockPlaceEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupInventoryInteractEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArenaSetupPlayerDropEvent(), this);

		//signs
		Bukkit.getServer().getPluginManager().registerEvents(new CreateGameSign(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DestroyGameSign(), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static CodoPB getInstance(){
		return instance;
	}
}
