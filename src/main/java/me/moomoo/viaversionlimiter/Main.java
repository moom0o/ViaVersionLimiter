package me.moomoo.viaversionlimiter;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Enabled");
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent evt) {
        ViaAPI api = Via.getAPI();
        int version = api.getPlayerVersion(evt.getPlayer());
        if (!getConfig().getIntegerList("VersionsAllowed").contains(version) && !getConfig().getStringList("BypassPlayers").contains(evt.getPlayer().getName())) {
            evt.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', getConfig().getString("KickMessage")));
        }
    }
}
