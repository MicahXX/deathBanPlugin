package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.dev.micahcode.DeathBanPlugin;

public class Reload implements BasicCommand {
    private final DeathBanPlugin plugin;

    public Reload(DeathBanPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (!source.getSender().hasPermission("deathBanPlugin.admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length > 0) {
            source.getSender().sendRichMessage("<red>Usage: /reload");
            return;
        }

        plugin.reloadConfig();
        plugin.setAutoBanEnabled(plugin.getConfig().getBoolean("autoban", true));
        // add the other things to reload after, like ban message etc
        source.getSender().sendRichMessage("DeathBan configuration reloaded.");
    }
}
