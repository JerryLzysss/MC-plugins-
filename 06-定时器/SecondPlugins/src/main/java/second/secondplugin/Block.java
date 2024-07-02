package second.secondplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Block extends BukkitRunnable{
    long s=0;
    @Override
    public void run() {
        if(s<=20)
        {
            s++;
            System.out.println(s);
        }
        else
        {
            cancel();
        }
    }

}
