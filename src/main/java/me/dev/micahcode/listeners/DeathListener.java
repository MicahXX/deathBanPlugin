package me.dev.micahcode.listeners;

import me.dev.micahcode.DeathBanPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.time.Duration;
import java.util.Date;

public class DeathListener implements Listener {

    private final DeathBanPlugin plugin;

    public DeathListener(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        // todo: there will be some checks like if there are excluded etc

        if (plugin.isAutoBanEnabled()) {
            player.ban("test", (Date) null, "console");
            Bukkit.broadcast(Component.text(player.getName() + " was banned."));
        }
    }
}