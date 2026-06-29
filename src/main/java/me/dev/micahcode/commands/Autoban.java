package me.dev.micahcode.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jspecify.annotations.NullMarked;

import java.time.Duration;


@NullMarked
public class Autoban implements BasicCommand {

    boolean autoBan = false;

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (!source.getExecutor().hasPermission("admin")) {
            source.getSender().sendRichMessage("<red>You dont have the permissions to do this.");
            return;
        }

        if (args.length == 0) {
            source.getSender().sendRichMessage("<red>You cannot send an empty broadcast!");
            return;
        }

        
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        Player player = playerDeathEvent.getPlayer();

        // should be if statement for if exclude or only is on
        player.ban("gg", Duration.ofDays(2), "Console", true);
    }
}