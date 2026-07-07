package me.dev.micahcode.commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Unban implements BasicCommand {
    private final DeathBanPlugin plugin;

    public Unban(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        BanList<PlayerProfile> banList = Bukkit.getBanList(BanList.Type.PROFILE);

        if (args.length == 0) {
            Set<UUID> tracked = new HashSet<>(plugin.getBannedPlayers());

            for (UUID uuid : tracked) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
                banList.pardon(target.getPlayerProfile());
            }

            plugin.clearBannedPlayers();
            source.getSender().sendRichMessage("<green>Unbanned all players (" + tracked.size() + " in total)");
            return;
        }

        String targetName = args[0];
        UUID matched = null;

        for (UUID uuid : plugin.getBannedPlayers()) {
            OfflinePlayer candidate = Bukkit.getOfflinePlayer(uuid);
            if (targetName.equalsIgnoreCase(candidate.getName())) {
                matched = uuid;
                break;
            }
        }

        if (matched == null) {
            source.getSender().sendRichMessage("<red>" + targetName + " is not tracked as banned by this plugin.");
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(matched);
        banList.pardon(target.getPlayerProfile());
        plugin.removeBannedPlayer(matched);
        source.getSender().sendRichMessage("<green>Unbanned " + targetName);
    }
}