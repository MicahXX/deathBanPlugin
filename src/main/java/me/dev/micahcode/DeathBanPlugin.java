package me.dev.micahcode;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.dev.micahcode.commands.*;
import me.dev.micahcode.listeners.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class DeathBanPlugin extends JavaPlugin {
    private boolean autoBanEnabled; // default: on
    private String banmessage;
    private int bantime;
    private Set<String> excludedPlayers;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.autoBanEnabled = getConfig().getBoolean("autoban", true);
        this.banmessage = getConfig().getString("banmessage");
        this.bantime = getConfig().getInt("bantime");
        this.excludedPlayers = new HashSet<>(getConfig().getStringList("excludefromban"));

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("autoban", "Toggle autoban on/off", new Autoban(this));
            commands.register("banmessage", "Set the message of the ban", new BanMessage(this));
            commands.register("bantime", "Set the time of the ban", new BanTime(this));
            commands.register("excludefromban", "Exclude a player from being banned", new ExcludeFromBan(this));
            commands.register("removefromexclude", "Remove the player from the excluded list", new RemoveFromExclude(this));
            commands.register("reload", "Reload the config file", new Reload(this));
            // todo: the other commands (same format)
        });

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

    public String getBanmessage() {
        return banmessage;
    }

    public void setBanmessage(String banmessage) {
        this.banmessage = banmessage;
        getConfig().set("banmessage", getBanmessage());
        saveConfig();
    }

    public int getBantime() {
        return bantime;
    }

    public void setBantime(int bantime) {
        this.bantime = bantime;
        getConfig().set("bantime", getBantime());
        saveConfig();
    }

    public Set<String> getExcludedPlayers() {
        return excludedPlayers;
    }

    public void setExcludedPlayer(String playerName) {
        excludedPlayers.add(playerName);
        getConfig().set("excludefromban", new ArrayList<>(excludedPlayers));
        saveConfig();
    }

    public void reloadExcludedPlayers() {
        this.excludedPlayers = new HashSet<>(getConfig().getStringList("excludefromban"));
    }

    public void reloadAllFromConfig() {
        this.autoBanEnabled = getConfig().getBoolean("autoban", true);
        this.banmessage = getConfig().getString("banmessage");
        this.bantime = getConfig().getInt("bantime");
        this.excludedPlayers = new HashSet<>(getConfig().getStringList("excludefromban"));
        // todo: add onlyBan later
    }
}