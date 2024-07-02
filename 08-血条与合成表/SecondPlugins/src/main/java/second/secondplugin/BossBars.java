package second.secondplugin;



import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;

import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.boss.BossBar;
public class BossBars implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e)
    {
        Player p=e.getPlayer();
        Plugin plugin =second.secondplugin.SecondPlugin.getPlugin(second.secondplugin.SecondPlugin.class);
        BossBar bossBar= Bukkit.createBossBar(new NamespacedKey(plugin,"bossbar"),"BossBar",BarColor.PINK,BarStyle.SEGMENTED_6,BarFlag.DARKEN_SKY);
        bossBar.addPlayer(p);
        System.out.println(p.getName());

    }

}
