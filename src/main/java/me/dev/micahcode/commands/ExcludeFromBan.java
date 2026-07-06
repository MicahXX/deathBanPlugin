package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;

public class ExcludeFromBan implements BasicCommand {
    private final DeathBanPlugin plugin;

    public ExcludeFromBan(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length == 0) {
            source.getSender().sendRichMessage("<red>Usage: /excludefromban <ign>");
            return;
        }

        String playerName = args[0];
        plugin.setExcludedPlayer(playerName);
        source.getSender().sendRichMessage("<green>" + playerName + " will no longer be autobanned on death.");
    }
}
