package second.secondplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Players implements Listener {
    Plugin plugins= second.secondplugin.SecondPlugin.getPlugin(second.secondplugin.SecondPlugin.class);
    long s=0;
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e)
    {

        new BukkitRunnable(){
            @Override
            public void run(){
                if(s<=20)
                {
                    System.out.println(s);
                    s++;

                }
                else{
                    cancel();
                }
            }
        }.runTaskTimer(plugins,0,10L);
    }
}
