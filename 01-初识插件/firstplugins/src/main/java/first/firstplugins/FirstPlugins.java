package first.firstplugins;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FirstPlugins extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        /*注册方法*/
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(),this);

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
