package me.dev.micahcode;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.dev.micahcode.commands.*;
import me.dev.micahcode.listeners.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class DeathBanPlugin extends JavaPlugin {
    private boolean autoBanEnabled; // default: on
    private String banmessage;
    private int bantime;
    private Set<String> excludedPlayers;
    private Set<String> onlyBanPlayers;
    private Set<UUID> bannedPlayers;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadAllFromConfig();

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("autoban", "Toggle autoban on/off", new Autoban(this));
            commands.register("banmessage", "Set the message of the ban", new BanMessage(this));
            commands.register("bantime", "Set the time of the ban", new BanTime(this));
            commands.register("excludefromban", "Exclude a player from being banned", new ExcludeFromBan(this));
            commands.register("removefromexclude", "Remove the player from the excluded list", new RemoveFromExclude(this));
            commands.register("onlyban", "Only ban named players", new OnlyBan(this));
            commands.register("removefromonlyban", "Remove the player from the only ban list", new RemoveFromOnlyBan(this));
            commands.register("reload", "Reload the config file", new Reload(this));
            commands.register("unban", "Unban all or a specific player", new Unban(this));
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
        excludedPlayers.add(playerName.toLowerCase());
        getConfig().set("excludefromban", new ArrayList<>(excludedPlayers));
        saveConfig();
    }

    public Set<String> getOnlyBanPlayers() {
        return onlyBanPlayers;
    }

    public void setOnlyBanPlayers(String playerName) {
        onlyBanPlayers.add(playerName.toLowerCase());
        getConfig().set("onlyban", new ArrayList<>(onlyBanPlayers));
        saveConfig();
    }

    public void removeExcludedPlayer(String playerName) {
        excludedPlayers.remove(playerName.toLowerCase());
        getConfig().set("excludefromban", new ArrayList<>(excludedPlayers));
        saveConfig();
    }

    public void removeOnlyBanPlayer(String playerName) {
        onlyBanPlayers.remove(playerName.toLowerCase());
        getConfig().set("onlyban", new ArrayList<>(onlyBanPlayers));
        saveConfig();
    }

    public Set<UUID> getBannedPlayers() {
        return bannedPlayers;
    }

    public void addBannedPlayer(UUID uuid) {
        bannedPlayers.add(uuid);
        saveBannedPlayers();
    }

    public void removeBannedPlayer(UUID uuid) {
        bannedPlayers.remove(uuid);
        saveBannedPlayers();
    }

    public void clearBannedPlayers() {
        bannedPlayers.clear();
        saveBannedPlayers();
    }

    private void saveBannedPlayers() {
        List<String> asStrings = bannedPlayers.stream().map(UUID::toString).toList();
        getConfig().set("bannedplayers", asStrings);
        saveConfig();
    }

    public void reloadAllFromConfig() {
        this.autoBanEnabled = getConfig().getBoolean("autoban", true);
        this.banmessage = getConfig().getString("banmessage");
        this.bantime = getConfig().getInt("bantime");
        this.excludedPlayers = new HashSet<>();
        for (String s : getConfig().getStringList("excludefromban")) {
            this.excludedPlayers.add(s.toLowerCase());
        }
        this.onlyBanPlayers = new HashSet<>();
        for (String s : getConfig().getStringList("onlyban")) {
            this.onlyBanPlayers.add(s.toLowerCase());
        }
        this.bannedPlayers = new HashSet<>();
        for (String s : getConfig().getStringList("bannedplayers")) {
            try {
                this.bannedPlayers.add(UUID.fromString(s));
            } catch (IllegalArgumentException ignored) {
                // to not crash
            }
        }
    }
}