package me.Speedyspro.RandomLocation;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("[RandomLocation] Enabled!");
		getServer().getPluginManager().registerEvents(new SignListener(), this);
		getServer().getPluginManager().registerEvents(new PortalListener(), this);


		File file = new File(getDataFolder() + File.separator + "config.yml");

		if(!file.exists()){
			getLogger().info("[RandomLocation] Generating config file!");
			this.getConfig().set("range", "50000");
			this.getConfig().set("teleport-message", "You have been teleported to a random location.");
			this.getConfig().options().copyDefaults(true);
			saveConfig();
			getLogger().info("[RandomLocation] Done. You are ready!");
		}
		else {
			getLogger().info("[RandomLocation] Found 'config.yml' and loaded!");
			this.getConfig().options().copyDefaults(true);
			saveConfig();
		}
		
		
		try {
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	        getLogger().info("[RandomLocation] Metrics enabled!");
	    } catch (IOException e) {
	    	getLogger().info("[RandomLocation] Failed to submit the stats.!"); 
	    }
		
	}

	@Override
	public void onDisable() {
		getLogger().info("[RandomLocation] Disabled. See you soon!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		ItemStack wand = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = wand.getItemMeta();
		meta.setDisplayName("§4§lRL Wand");
		player.getInventory().addItem(wand);
		player.sendMessage("§4[§cRandomLocation§4] §eYou have been given the wand.");
		player.sendMessage("§4[§cRandomLocation§4] §eSelect the two points of the portal.");

		return false;

	}

}
