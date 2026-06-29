package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;

public class Banmessage implements BasicCommand {
    // for testing
    @Override
    public void execute(CommandSourceStack source, String[] args) {
        final String message = String.join(" ", args);
        final Component name = source.getExecutor() != null
                ? source.getExecutor().name()
                : source.getSender().name();

        final Component broadcastMessage = MiniMessage.miniMessage().deserialize(
                "<red><bold>BROADCAST</red> <name> <dark_gray>»</dark_gray> <message>",
                Placeholder.component("name", name),
                Placeholder.unparsed("message", message)
        );

        Bukkit.broadcast(broadcastMessage);
    }
}
