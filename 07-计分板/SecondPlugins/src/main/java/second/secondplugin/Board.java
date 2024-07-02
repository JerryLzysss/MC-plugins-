package second.secondplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Board implements Listener {
    @EventHandler
    public void setBoard(PlayerJoinEvent e)
    {
        Plugin plugin= second.secondplugin.SecondPlugin.getPlugin(second.secondplugin.SecondPlugin.class);
        Scoreboard board=plugin.getServer().getScoreboardManager().getMainScoreboard();
        Player p=e.getPlayer();
       Objective obj;
       obj=board.registerNewObjective("内部名","dummy","我是标题!");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score s=obj.getScore("test分数:");
        s.setScore(760);
        p.setScoreboard(board);
}   }
