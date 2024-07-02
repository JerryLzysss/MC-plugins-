package second.secondplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Blocks implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e)
    {
        World world= Bukkit.getWorld("world");

        Player p=e.getPlayer();
        System.out.println(p.getLocation());
        Location q=p.getLocation();
        Block block=world.getBlockAt(q.getBlockX(),q.getBlockY()-1,q.getBlockZ());
        block.setType(Material.DIAMOND_BLOCK);
    }
}
