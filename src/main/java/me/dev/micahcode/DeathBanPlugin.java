package me.dev.micahcode;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.dev.micahcode.commands.Autoban;
import me.dev.micahcode.commands.Reload;
import me.dev.micahcode.listeners.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathBanPlugin extends JavaPlugin {

    // also in .yml
    // /autoban off/on (on is default) --> all players (can also be set in config)
    // /onlyban <playername> --> only his player (if autoban off make it on)
    // /excludefromban <playername> --> dont ban this player
    // /banmessage <message> OR in config

    // not in .yml
    // /unban <player/all>

    private boolean autoBanEnabled; // default: on

    // not needed for now
    // private final Set<String> onlyBanPlayers = new HashSet<>();
    // private final Set<String> excludedPlayers = new HashSet<>();
    // private String banMessage = "Test";

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.autoBanEnabled = getConfig().getBoolean("autoban", true);

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("autoban", "Toggle autoban on/off", new Autoban(this));
            commands.register("reload", "Reload the config file", new Reload(this));
            // todo: the other commands (same format)
        });

        // only Listener needed
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }

    public boolean isAutoBanEnabled() {
        return autoBanEnabled;
    }

    public void setAutoBanEnabled(boolean autoBanEnabled) {
        this.autoBanEnabled = autoBanEnabled;
        getConfig().set("autoban", autoBanEnabled);
        saveConfig();
    }
}