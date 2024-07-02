package first.firstplugins;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvent implements Listener {
    /*@EventHandler 注解表示监听器注解*/
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player player=e.getPlayer();
        if(player.isOp())
        {

            e.setJoinMessage("欢迎管理员:"+"§6"+player.getName()+"§f进入服务器");
        }
        else
        {
            e.setJoinMessage("欢迎用户:"+"§4"+player.getName()+"§f进入服务器");
        }
    }




}
