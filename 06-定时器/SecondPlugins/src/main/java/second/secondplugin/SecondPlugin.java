package second.secondplugin;

import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

public final class SecondPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //BukkitTask Message = new Block().runTaskTimer(this,0,100);
        Bukkit.getPluginManager().registerEvents(new Players(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }






}
