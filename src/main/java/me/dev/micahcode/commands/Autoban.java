package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class Autoban implements BasicCommand {

    private final DeathBanPlugin plugin;

    public Autoban(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        // todo: make it possible to see suggestion for on/off for user

        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length == 0) {
            source.getSender().sendRichMessage("<red>Usage: /autoban (on/off)");
            return;
        }

        if (args[0].equalsIgnoreCase("on")) {
            plugin.setAutoBanEnabled(true);
            source.getSender().sendRichMessage("Auto ban is now enabled!");
        }
        else if (args[0].equalsIgnoreCase("off")) {
            plugin.setAutoBanEnabled(false);
            source.getSender().sendRichMessage("Auto ban is now disabled!");
        } else {
            source.getSender().sendRichMessage("Usage: /autoban (on/off)");
        }
    }
}