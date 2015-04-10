package me.Speedyspro.RandomLocation;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {

	private static Main plugin = (Main) Bukkit.getPluginManager().getPlugin(
			"RandomLocationTP");

	public int getRandomLoc(int range) {
		Random random = new Random();
		return random.nextInt(range);
	}

	// Gestore Sign
	@EventHandler
	public void eventSignChanged(SignChangeEvent event) {
		Player player = event.getPlayer();
		if (player.isOp() == true || player.hasPermission("randomlocation.create")) {
			if (event.getLine(0).equals("[Random]")) {
				if (Bukkit.getWorld(event.getLine(1)) != null) {
					event.setLine(0, "§4[§cRandom§4]");					
				} else {
					event.getBlock().breakNaturally();
					player.sendMessage("§4[§cRandomLocation§4] §eThe world §4"	+ event.getLine(1) + " §edoesn't exist.");
				}
			}
		}

	}

	// Click del Sign
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) event.getClickedBlock().getState();

				if (sign.getLine(0).equals("§4[§cRandom§4]")) {

					Player player = event.getPlayer();

					if (player.hasPermission("randomlocation.use")) {

						World w = Bukkit.getWorld(sign.getLine(1));
						double x = getRandomLoc(50000);
						double z = getRandomLoc(50000);

						int x1 = (int) x;
						int z1 = (int) z;

						double y = (double) w.getHighestBlockYAt(x1, z1);
						Location loc = new Location(w, x, y, z);
						player.sendMessage("§4[§cRandomLocation§4] §e" + plugin.getConfig().getString("teleport-message"));
						player.teleport(loc);

					}
				}

			}
		}
	}

}
