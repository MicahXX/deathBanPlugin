package me.dev.micahcode.listeners;

import me.dev.micahcode.DeathBanPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;

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

            Component styledReason = MiniMessage.miniMessage().deserialize(plugin.getBanmessage());
            String plainReason = PlainTextComponentSerializer.plainText().serialize(styledReason);

            player.ban(plainReason, duration, "console", false);

            player.kick(styledReason, PlayerKickEvent.Cause.BANNED);

            plugin.addBannedPlayer(player.getUniqueId());
        }
    }
}