package me.Speedyspro.RandomLocation;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalListener implements Listener {
	
	private static Main plugin = (Main) Bukkit.getPluginManager().getPlugin(
			"RandomLocationTP");

	public int getRandomLoc(int range) {
		Random random = new Random();
		return random.nextInt(range);
	}
	
    @EventHandler
	public void onPortalEnter(PlayerPortalEvent e) {

		Player player = e.getPlayer();
		World w = Bukkit.getWorld("world");
		double x = getRandomLoc(50000);
		double z = getRandomLoc(50000);

		int x1 = (int) x;
		int z1 = (int) z;

		double y = (double) w.getHighestBlockYAt(x1, z1);
		Location loc = new Location(w, x, y, z);
		player.sendMessage("§4[§cRandomLocation§4] §e"
				+ plugin.getConfig().getString("teleport-message"));
		player.teleport(loc);

		e.getPlayer().sendMessage(e.getFrom() + "");
		e.setCancelled(true);

	}

}
