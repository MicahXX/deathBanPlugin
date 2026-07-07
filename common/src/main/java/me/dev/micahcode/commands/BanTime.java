package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;

public class BanTime implements BasicCommand {
    private final DeathBanPlugin plugin;

    public BanTime(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length == 0) {
            source.getSender().sendRichMessage("<red>Usage: /bantime <minutes>");
            return;
        }

        final int bantime;
        try {
            bantime = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            source.getSender().sendRichMessage("<red>That's not a valid number.");
            return;
        }

        if (bantime < 0) {
            source.getSender().sendRichMessage("<red>Ban time can't be negative.");
            return;
        }

        plugin.setBantime(bantime);
        if (bantime == 0) {
            source.getSender().sendRichMessage("<green>Ban time is permanent.");
        } else {
            source.getSender().sendRichMessage("<green>New ban time set to " + bantime + " minutes.");
        }
    }
}
