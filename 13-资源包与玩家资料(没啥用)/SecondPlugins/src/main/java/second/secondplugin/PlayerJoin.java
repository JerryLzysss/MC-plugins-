package second.secondplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import java.io.InputStream;

public class PlayerJoin implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e)
    {
        Player p=e.getPlayer();
        //评价是没啥用，还得在server.properties直接写入
        //p.setResourcePack("https://cdn.modrinth.com/data/FRSckbRo/versions/vO4rHY5Y/%C2%A76%C2%A7lClassic%203D%C2%A7c%20v2.0.0-A.2%C2%A7e%201.16.zip");
        //同理1.18.1+才能用.
        //Bukkit.getResourcePack();

    }
    @EventHandler
    public void download(PlayerResourcePackStatusEvent e)
    {
        System.out.println(e.getStatus());
    }
}
