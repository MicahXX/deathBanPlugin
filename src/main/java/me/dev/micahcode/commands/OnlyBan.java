package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;

public class OnlyBan implements BasicCommand {
    private final DeathBanPlugin plugin;

    public OnlyBan(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length == 0) {
            source.getSender().sendRichMessage("<red>Usage: /onlyban <ign>");
            return;
        }

        String playerName = args[0];
        plugin.setOnlyBanPlayers(playerName);
        source.getSender().sendRichMessage("<red>" + playerName + " will be the only one autobanned. (remove all igns to have autoban again)");
    }
}
