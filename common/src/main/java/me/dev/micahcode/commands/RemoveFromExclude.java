package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;

import java.util.Locale;

public class RemoveFromExclude implements BasicCommand {
    private final DeathBanPlugin plugin;

    public RemoveFromExclude(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length == 0) {
            source.getSender().sendRichMessage("<red>Usage: /removefromexclude <ign>");
            return;
        }

        String playerName = args[0].toLowerCase();
        if (plugin.getExcludedPlayers().contains(playerName)) {
            plugin.removeExcludedPlayer(playerName);
            source.getSender().sendRichMessage("<green>" + playerName + " will now be autobanned on death.");
        } else {
            source.getSender().sendRichMessage("Player is not in the excluded players");
        }
    }
}
