package aaproj.hardcorecraft;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Logger;

public class EventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent evt) {
        Bukkit.getServer().getLogger().info("JOINED");
        if(!Hardcorecraft.config.contains(evt.getPlayer().getUniqueId().toString())){
            Bukkit.getServer().getLogger().info("First Player");
            Hardcorecraft.config.set(evt.getPlayer().getUniqueId().toString(),2);
            Hardcorecraft.getPlugin(Hardcorecraft.class).saveConfig();
            Hardcorecraft.getPlugin(Hardcorecraft.class).reloadConfig();
        }
        int life = Hardcorecraft.config.getInt(evt.getPlayer().getUniqueId().toString());
        Bukkit.getServer(). broadcastMessage(evt.getPlayer().getName() + "のライフ残り" + (life));
    }

    @EventHandler
    public void onDead(PlayerDeathEvent evt) {
        String uuid = evt.getEntity().getUniqueId().toString();
        int life = Hardcorecraft.config.getInt(uuid);
        Bukkit.getServer().getLogger().info("life:" + life);
        Hardcorecraft.config.set(uuid,life-1);
        Hardcorecraft.getPlugin(Hardcorecraft.class).saveConfig();
        Hardcorecraft.getPlugin(Hardcorecraft.class).reloadConfig();
        if(life < 1) {
            Bukkit.getServer(). broadcastMessage(evt.getEntity().getPlayer().getName() + "はライフを使い果たしました。");
            evt.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);
        } else {
            Bukkit.getServer(). broadcastMessage(evt.getEntity().getPlayer().getName() + "のライフ残り" + (life - 1));
        }

        evt.getEntity().getPlayer().setDisplayName(evt.getEntity().getPlayer().getName() + ":" + life);
    }
}
