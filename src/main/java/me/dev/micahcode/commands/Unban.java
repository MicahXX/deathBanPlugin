package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;

public class Unban implements BasicCommand {
    private final DeathBanPlugin plugin;

    public Unban(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        // todo: add method to unban all and specific persons
        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length == 0) {
            source.getSender().sendRichMessage("<green> Unbanned all players");
        }
    }
}
