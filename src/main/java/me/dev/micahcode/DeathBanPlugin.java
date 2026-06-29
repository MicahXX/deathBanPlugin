package me.dev.micahcode;

import io.papermc.paper.command.brigadier.BasicCommand;
import me.dev.micahcode.commands.Banmessage;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathBanPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // also in .yml
        // /autoban off/on (on is default) --> all players (can also be set in config)
        // /onlyban <playername> --> only his player (if autoban off make it on)
        // /excludefromban <playername> --> dont ban this player
        // /banmessage <message> OR in config

        // not in .yml
        // /unban <player/all>

        // trying out commands
        BasicCommand banMessage = new Banmessage();
        registerCommand("banMessage", banMessage);
    }
}
