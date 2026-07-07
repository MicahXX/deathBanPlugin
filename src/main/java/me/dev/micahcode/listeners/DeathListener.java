package me.dev.micahcode.listeners;

import me.dev.micahcode.DeathBanPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.time.Duration;

public class DeathListener implements Listener {

    private final DeathBanPlugin plugin;

    public DeathListener(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        String name = player.getName().toLowerCase();

        boolean ban;

        if (!plugin.getOnlyBanPlayers().isEmpty()) {
            ban = plugin.getOnlyBanPlayers().contains(name); // has only ban entries
        } else {
            ban = plugin.isAutoBanEnabled() && !plugin.getExcludedPlayers().contains(name);
        }

        if (ban) {
            int minutes = plugin.getBantime();
            Duration duration = minutes > 0 ? Duration.ofMinutes(minutes) : null;
            player.ban(plugin.getBanmessage(), duration, "console");
            plugin.addBannedPlayer(player.getUniqueId()); // add to banned list
        }
    }
}